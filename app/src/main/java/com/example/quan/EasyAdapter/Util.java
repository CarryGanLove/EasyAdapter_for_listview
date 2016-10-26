package com.example.quan.EasyAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static <T> T getInstance(Class<T> clazz) {
        return getInstance(clazz, null, null);
    }

    public static <T> T getInstance(Class<T> clazz, Class<?>[] parameterTypes, Object[] arguments) {
        T instance = null;
        Constructor<T> c;
        try {
            if (null == parameterTypes) {
                c = clazz.getDeclaredConstructor((Class[]) null);
                c.setAccessible(true);
                instance = c.newInstance();
            } else {
                c = clazz.getDeclaredConstructor(parameterTypes);
                c.setAccessible(true);
                instance = c.newInstance(arguments);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (null == instance) {
            throw new RuntimeException("can't instantiate " + clazz + "whether it is not static");
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String fullName) {
        Class<T> clazz = null;
        try {
            clazz = (Class<T>) Class.forName(fullName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("class not found " + clazz);
        }
        return getInstance(clazz);
    }
    public static List<ItemBean> createList() {
        int[] colors = {R.color.color_0, R.color.color_1, R.color.color_2, R.color.color_3,
                R.color.color_4, R.color.color_5, R.color.color_6, R.color.color_7,
                R.color.color_8, R.color.color_9,};
        List<ItemBean> list = new ArrayList<>();

        for (int i = 0; i < colors.length * 3; i++) {
            list.add(new ItemBean("index" + i, colors[i % colors.length]));

        }
        return list;
    }
}

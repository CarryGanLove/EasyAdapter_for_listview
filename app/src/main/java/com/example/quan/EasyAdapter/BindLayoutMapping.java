package com.example.quan.EasyAdapter;

/**
 * Created by GanQuan on 16/3/26.
 */
public class BindLayoutMapping {
    public static int getLayoutId(Class<?> clazz) {
        BindLayout m = clazz.getAnnotation(BindLayout.class);
        if (m != null) {
            return m.id();
        }
        return 0;

    }
}

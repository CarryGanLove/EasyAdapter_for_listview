package com.example.quan.EasyAdapter;

import android.content.Context;

/**
 * Created by GanQuan on 16/10/27.
 */
public class BaseViewHolder<T> {

    public BaseViewHolder() {

    }
    public Class getClassTag() {
        return this.getClass();
    }

    public void setView(T bean, int position, Context context) {
    }
}

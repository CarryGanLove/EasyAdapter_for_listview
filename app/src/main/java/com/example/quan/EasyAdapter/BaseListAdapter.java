package com.example.quan.EasyAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GanQuan on 2015/5/7.
 */

/**
 * @param <T> list<T>
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    private Context mContext;
    /**
     * mlist
     */
    private List<T> list = new ArrayList<>();

    public Context getContext() {
        return mContext;
    }

    //    public BaseListAdapter() {
//        super();
//    }
    private InnerAdapter<T> adapter;

    public BaseListAdapter(Context context) {
        this.mContext = context;

        adapter = new InnerAdapter<T>(context, list);
        adapter.listener = new InnerAdapter.CatchTypesListener<T>() {
            @Override
            public void onBindViewHolder(List<Class<?>> list) {
                BaseListAdapter.this.onBindViewHolder(list);
            }

        };
    }


    /**
     * 初始化list
     *
     * @param list
     */
    public void initList(List<T> list) {
        if (this.list.size() > 0) {
            this.list.clear();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * add the list ,but no clear
     *
     * @param list
     */
    public void addList(List<T> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * clear all list
     */
    public void clearList() {
        this.list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return adapter.getCount();
    }

    @Override
    public T getItem(int position) {
        return adapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return adapter.getViewBundles().size();
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        return adapter.getView(position, convertView, parent);
    }


//    private Class findViewHolderClazz(int viewType) {
//        return mViewBundles.get(viewType).vHClazz;
//    }


    /**
     * adding viewBundle to list,index as viewTypeId,
     *
     * @param list the list has been instantiated,so it is no need to instantiate
     *             list in the function
     */
    protected abstract void onBindViewHolder(List<Class<?>> list);


}

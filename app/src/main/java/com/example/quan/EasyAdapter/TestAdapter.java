package com.example.quan.EasyAdapter;

import android.content.Context;


import java.util.List;

/**
 * Created by GanQuan on 16/10/26.
 */
class TestAdapter extends BaseListAdapter<ItemBean> {

    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindViewHolder(List<Class<?>> list) {
        list.add(FirstViewType.class);
        list.add(SecondViewType.class);
        list.add(ThridViewType.class);
    }


    /**
     * adapter has various types,override this method
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }


}

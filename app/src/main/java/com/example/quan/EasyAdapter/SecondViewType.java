package com.example.quan.EasyAdapter;

import android.content.Context;
import android.widget.TextView;

import butterknife.Bind;

/**
 * Created by GanQuan on 16/10/26.
 */
@BindLayout(id=R.layout.list_item_1)
class SecondViewType extends InnerAdapter.BaseViewHolder<ItemBean> {
    @Bind(R.id.text)
    TextView textView;

    @Override
    public void setView(ItemBean bean, int position, Context context) {
        textView.setText(bean.name);
        textView.setBackgroundResource(bean.color);
    }
}

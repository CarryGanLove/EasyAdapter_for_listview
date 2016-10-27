package com.example.quan.EasyAdapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import butterknife.Bind;

/**
 * Created by GanQuan on 16/10/26.
 */
@BindLayout(id=R.layout.list_item_2)
class ThridViewType extends BaseViewHolder<ItemBean> {

    @Bind(R.id.left_tx)
    TextView left;
    @Bind(R.id.center_tx)
    TextView center;
    @Bind(R.id.right_tx)
    TextView rigth;

    @Override
    public void setView(ItemBean bean, int position, Context context) {
        left.setText(bean.name);
        ((View) left.getParent()).setBackgroundResource(bean.color);
        center.setText(bean.name);
        rigth.setText(bean.name);
    }
}

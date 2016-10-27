package com.example.quan.EasyAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;

/**
 * Created by GanQuan on 16/10/26.
 */
@BindLayout(id=R.layout.list_item_0)
class FirstViewType extends BaseViewHolder<ItemBean> {
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.text)
    TextView textView;

    @Override
    public void setView(ItemBean bean, int position, Context context) {
        img.setImageResource(R.drawable.ic_launcher);
        textView.setText(bean.name);
        ((View) img.getParent()).setBackgroundResource(bean.color);
    }
}

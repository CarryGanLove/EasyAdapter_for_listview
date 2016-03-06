package com.example.quan.EasyAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @Bind(R.id.listview)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Adapter adapter = new Adapter(this);
        mListView.setAdapter(adapter);

        adapter.addList(createList());

    }

    private List<ItemBean> createList() {
        List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < colors.length * 3; i++) {
            list.add(new ItemBean("index" + i, colors[i % colors.length]));

        }
        return list;
    }

    private int[] colors = {R.color.color_0, R.color.color_1, R.color.color_2, R.color.color_3,
            R.color.color_4, R.color.color_5, R.color.color_6, R.color.color_7,
            R.color.color_8, R.color.color_9,};

    class Adapter extends BaseListAdapter<ItemBean> {

        public Adapter(Context context) {
            super(context);
        }

        @Override
        protected void onBindViewHolder(List<ViewBundle> list) {
            list.add(new ViewBundle(R.layout.list_item_0, VHtype1.class));
            list.add(new ViewBundle(R.layout.list_item_1, VHtype2.class));
            list.add(new ViewBundle(R.layout.list_item_2, VHtype3.class));
        }

        @Override
        public int getItemViewType(int position) {
            return position % 3;
        }
    }

    static class VHtype1 extends BaseListAdapter.BaseViewHolder<ItemBean> {
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

    static class VHtype2 extends BaseListAdapter.BaseViewHolder<ItemBean> {
        @Bind(R.id.text)
        TextView textView;

        @Override
        public void setView(ItemBean bean, int position, Context context) {
            textView.setText(bean.name);
            textView.setBackgroundResource(bean.color);
        }
    }

    static class VHtype3 extends BaseListAdapter.BaseViewHolder<ItemBean> {

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

}

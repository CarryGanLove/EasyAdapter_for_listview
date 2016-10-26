package com.example.quan.EasyAdapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by GanQuan on 16/10/26.
 */
public class InnerAdapter<T> implements Adapter {
    /**
     * viewHolder and layout bundle list
     */
    private List<Class<?>> mViewBundles = new ArrayList<>();
    BaseViewHolder viewHolder = null;
    private Context mContext;
    private List<T> mList;

    public InnerAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    /**
     * set data for viewHolder by viewType(through by getItemViewType)
     *
     * @param position
     * @param bean
     * @param baseViewHolder
     */
    private void onSetViewHolder(int position, T bean, BaseViewHolder baseViewHolder) {
        baseViewHolder.setView(bean, position, mContext);
    }

    private View createView(int position, BaseViewHolder viewHolder) {
        View view = View.inflate(mContext, getBindItemViewResId(position), null);
        ButterKnife.bind(viewHolder, view);
        // if want to set extra obj
        handleViewHolder(viewHolder);
        view.setTag(viewHolder);
        return view;
    }

    protected BaseViewHolder onCreateViewHolder(int pos, List<Class<?>> ViewBundles) {
        Class clazz = ViewBundles.get(getItemViewType(pos));
        return (BaseViewHolder) Util.getInstance(clazz);
    }

    /**
     * 重写该方法进行对holder的设置其他非view缓存
     *
     * @param baseViewHolder
     */
    public void handleViewHolder(BaseViewHolder baseViewHolder, T... t) {
        // todo
    }

    /**
     * 根据pos传入相应的 layout.xml
     *
     * @return
     */
    protected int getBindItemViewResId(int position) {
        return BindLayoutMapping.getLayoutId(getViewBundles().get(getItemViewType(position)));
//        return getViewBundles().get(getItemViewType(position)).layoutId;
    }

    public static class BaseViewHolder<T> {

        public BaseViewHolder() {

        }

        public Class getClassTag() {
            return this.getClass();
        }

        public void setView(T bean, int position, Context context) {
        }
    }

    public static class ViewBundle {
        public ViewBundle(Class<? extends BaseViewHolder> clazz) {
            this.vHClazz = clazz;
        }

        public Class<? extends BaseViewHolder> vHClazz;
    }


    /**
     * get bundle list contains viewHolder and layoutId
     *
     * @return
     */
    public List<Class<?>> getViewBundles() {
        return getBindViewHolderList(mViewBundles);
    }

    private List<Class<?>> getBindViewHolderList(List<Class<?>> list) {
        if (list.size() > 0) {
        } else {
            if (listener != null) {
                listener.onBindViewHolder(list);
            }
        }
        return list;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        if (mList.size() > position) {
            return mList.get(position);
        } else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = onCreateViewHolder(position, getViewBundles());
            convertView = createView(position, viewHolder);
        } else {
            viewHolder = (BaseViewHolder) convertView.getTag();
        }
        if (convertView == null || convertView.getTag() == null)
            throw new NullPointerException(" creatview fails");
        onSetViewHolder(position, getItem(position), viewHolder
        );
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public CatchTypesListener listener;

    interface CatchTypesListener<T> {
        void onBindViewHolder(List<Class<?>> list);
    }
}

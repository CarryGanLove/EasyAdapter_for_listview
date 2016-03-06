package com.example.quan.EasyAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

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
    /**
     * viewHolder and layout bundle list
     */
    private List<ViewBundle> viewBundles = new ArrayList<>();

    public Context getContext() {
        return mContext;
    }

//    public BaseListAdapter() {
//        super();
//    }

    public BaseListAdapter(Context context) {
        this.mContext = context;

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
        return list.size();
    }

    @Override
    public T getItem(int position) {
        if (list.size() > position) {
            return list.get(position);
        } else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (getViewBundles() == null)
            return 1;

        int count = getViewBundles().size();
        return count > 1 ? count : 1;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = onCreateViewHolder(position, getViewBundles());
            convertView = createView(position, viewHolder);
        } else {
            viewHolder = (BaseViewHolder) convertView.getTag();

        }
        if (convertView == null || convertView.getTag() == null)
            throw new NullPointerException(" creatview fails");
        onSetViewHolder(position, getItem(position), viewHolder);
        return convertView;
    }

    private View createView(int position, BaseViewHolder viewHolder) {
        View view = View.inflate(mContext, getBindItemViewResId(position), null);
        ButterKnife.bind(viewHolder, view);
        // if want to set extra obj
        handleViewHolder(viewHolder);
        view.setTag(viewHolder);
        return view;
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
        return getViewBundles().get(getItemViewType(position)).layoutId;
    }

    /**
     * get bundle list contains viewHolder and layoutId
     *
     * @return
     */
    public List<ViewBundle> getViewBundles() {
        return getBindViewHolderList(viewBundles);
    }

    private List<ViewBundle> getBindViewHolderList(List<ViewBundle> list) {
        if (list.size() > 0) {

        } else {
            onBindViewHolder(list);
        }
        return list;
    }

    /**
     * add viewBundle to list,index as viewTypeId,
     *
     * @param list the list has been instantiated,so it is no need to instantiate
     *             list in the function
     */
    protected abstract void onBindViewHolder(List<ViewBundle> list);

    protected BaseViewHolder onCreateViewHolder(int pos, List<ViewBundle> ViewBundles) {
        Class<? extends BaseViewHolder> clazz = ViewBundles.get(getItemViewType(pos)).vHClazz;
        return Util.getInstance(clazz);
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
        public ViewBundle(int layoutId, Class<? extends BaseViewHolder> clazz) {
            this.layoutId = layoutId;
            this.vHClazz = clazz;
        }

        public Class<? extends BaseViewHolder> vHClazz;
        public int layoutId;
    }


}

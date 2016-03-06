# EasyAdapter_for_listview
a simple adapter for listview,you just write some simple code witch can create a very powerful adapter to contain data

if you want to use the baseListAdapter,you need to implement the method onBindViewHolder.The method allows you bind a layoutId with a relative viewHolder.Just like this:
```java
@Override
    protected void onBindViewHolder(List<ViewBundle> list) {
        list.add(new ViewBundle(R.layout.list_item_0, VHtype1.class));
    }
```
if your listview contains more than one view type,you can add more item to list;
```java
@Override
    protected void onBindViewHolder(List<ViewBundle> list) {
        list.add(new ViewBundle(R.layout.list_item_0, VHtype1.class));
        list.add(new ViewBundle(R.layout.list_item_1, VHtype2.class));
        list.add(new ViewBundle(R.layout.list_item_2, VHtype3.class));
    }
```
you can bind view in viewHolder by the butterKnife,and set the data in method setView():
```java
 static class VHtype1 extends BaseListAdapter.BaseViewHolder<ItemBean> {
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.text)
    TextView textView;
    @Override
    public void setView(ItemBean bean, int position, Context context) {
      //to set data
        textView.setText(bean.name);
    }
}
```
now,everything is OK!running on set it to absListview by the method setAdapter!
package com.example.quan.EasyAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

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
        TestAdapter adapter = new TestAdapter(this);
        mListView.setAdapter(adapter);

        adapter.addList(Util.createList());

    }


}

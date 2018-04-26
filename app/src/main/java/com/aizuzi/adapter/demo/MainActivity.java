package com.aizuzi.adapter.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.aizuzi.adapter.FastAdapter;
import com.aizuzi.adapter.demo.viewholder.EmptyHolder;
import com.aizuzi.adapter.demo.viewholder.ImageViewHolder;
import com.aizuzi.adapter.demo.viewholder.TextViewHolder;

public class MainActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mRecyclerView = findViewById(R.id.recycler_view);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.addItemDecoration(new ColorItemDecoration());

    setAdapter();
  }

  private void setAdapter(){
    FastAdapter fastAdapter = new FastAdapter(this);

    for (int i = 0; i < 10; i++) {
      fastAdapter.addItem(TextViewHolder.class,"Text ViewHolder："+i);
      fastAdapter.addItem(ImageViewHolder.class,"your image path");
      fastAdapter.addItem(EmptyHolder.class);
    }

    mRecyclerView.setAdapter(fastAdapter);
  }


}

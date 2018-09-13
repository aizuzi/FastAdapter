package com.aizuzi.adapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.aizuzi.adapter.demo.viewholder.ItemViewHolder_;
import com.aizuzi.adapter.demo.viewholder.TopViewHolder_;
import com.zuzi.adapter.FastAdapter;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  private static final String[] images = new String[] {
      "https://ww1.sinaimg.cn/large/0065oQSqly1ftf1snjrjuj30se10r1kx.jpg",
      "https://ww1.sinaimg.cn/large/0065oQSqly1ftdtot8zd3j30ju0pt137.jpg",
      "http://ww1.sinaimg.cn/large/0073sXn7ly1ft82s05kpaj30j50pjq9v.jpg",
      "https://ww1.sinaimg.cn/large/0065oQSqly1ft5q7ys128j30sg10gnk5.jpg",
      "http://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg",
      "http://ww1.sinaimg.cn/large/0065oQSqly1fsysqszneoj30hi0pvqb7.jpg",
      "http://ww1.sinaimg.cn/large/0065oQSqly1fswhaqvnobj30sg14hka0.jpg",
      "http://ww1.sinaimg.cn/large/0065oQSqly1fsvb1xduvaj30u013175p.jpg",
      "http://ww1.sinaimg.cn/large/0065oQSqly1fsq9iq8ttrj30k80q9wi4.jpg",
      "http://ww1.sinaimg.cn/large/0065oQSqly1fsfq1k9cb5j30sg0y7q61.jpg"
  };

  @BindView(R.id.recycler_view)
  RecyclerView mRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.addItemDecoration(new ColorItemDecoration());

    setAdapter();
  }

  private void setAdapter() {
    FastAdapter fastAdapter = new FastAdapter(this);
    fastAdapter.addItem(new TopViewHolder_());

    for (int i = 0; i < images.length * 10; i++) {
      fastAdapter.addItem(new ItemViewHolder_()
          .setTitle("ViewHolder：" + i)
          .setIcon(R.mipmap.ic_launcher)
          .setImage(images[new Random().nextInt(images.length)]));
    }

    mRecyclerView.setAdapter(fastAdapter);
  }
}

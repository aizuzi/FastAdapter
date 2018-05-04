

FastAdapter
==========================
[![GitHub release](https://img.shields.io/badge/gradle-0.1.5-blue.svg)](https://github.com/aizuzi/FastAdapter/releases) 

使用FastAdapter，可以更加快速简单的使用RecylerView。
建立一个ViewHolder，然后使用fastAdapter.addItem即可使用，无需创建复杂的RecylerView Adapter。

## 如何使用它？
1.在项目的build.gradle文件添加
``` groovy
compile 'com.zuzi:fastadapter:0.1.5'
```

2.创建ViewHolder
``` java
  @RecyclerItemLayoutId(R.layout.item_text)
  public class TextViewHolder extends BaseViewHolder<String> {
    private TextView mTextView;
    public TextViewHolder(View itemView) {
      super(itemView);
    }
    @Override protected void onCreate() {
      super.onCreate();
      mTextView = findViewById(R.id.text_view);
    }

    @Override public void refreshItem(String bean) {
      mTextView.setText(bean);
    }
  }   
```

3.使用
``` java
 FastAdapter fastAdapter = new FastAdapter(this);
 fastAdapter.addItem(TextViewHolder1.class,"Text ViewHolder");
```



## License

[The MIT License](LICENSE).

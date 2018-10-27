

FastAdapter
==========================
[![Build Status](https://api.travis-ci.org/aizuzi/FastAdapter.svg?branch=master)](https://github.com/aizuzi/FastAdapter)
[![GitHub release](https://img.shields.io/badge/gradle-0.4.7-blue.svg)](https://github.com/aizuzi/FastAdapter/releases)

使用FastAdapter，可以更加快速简单的使用RecylerView。
建立一个ViewHolder，然后使用fastAdapter.addItem即可使用，无需创建复杂的RecylerView Adapter和ViewHolder。

## 如何使用它？
1.在项目的build.gradle文件添加
``` groovy
implementation 'com.github.aizuzi:fastadapter:0.4.7'
annotationProcessor 'com.github.aizuzi:fastadapter_processor:0.4.7'
```

2.创建ViewHolder
``` java
  @RecyclerItemLayoutId(R.layout.item_text)
  public abstract class ItemViewHolder extends FastBaseHolder {
    @FastAttribute(bindViewId = R.id.title_tv)
    String title;

    @FastAttribute(bindViewId = R.id.icon_view)
    int icon;

    @FastAttribute(bindViewId = R.id.image_view)
    String image;
  }   
```

3.使用
``` java
 FastAdapter fastAdapter = new FastAdapter(this);
 fastAdapter.addItem(new ItemViewHolder_().setTitle("ViewHolder：" + i);
```

## fastadapter交流平台
* QQ群：780275143

## License

[The MIT License](LICENSE).

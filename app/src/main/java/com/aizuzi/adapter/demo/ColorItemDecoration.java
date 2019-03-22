package com.aizuzi.adapter.demo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 *  @author liyi
 *  create at 2018/4/26
 **/
public class ColorItemDecoration extends  RecyclerView.ItemDecoration{
  private int mDividerHeight = 1;
  private int mDividerColor = 0xff323232;
  private int mMarginLeft = 0;
  private Paint mPaint;

  ColorItemDecoration() {
    initPaint();
  }

  private void initPaint() {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(mDividerColor);
    mPaint.setStyle(Paint.Style.FILL);
  }

  @Override
  public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
    super.getItemOffsets(outRect, itemPosition, parent);
    outRect.set(0, 0, 0, mDividerHeight);
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);
    drawHorizontal(c, parent);
  }

  private void drawHorizontal(Canvas canvas, RecyclerView parent) {
    final int left = parent.getPaddingLeft();
    final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
    int childSize = parent.getChildCount();
    for (int i = 0; i < childSize; i++) {
      final View child = parent.getChildAt(i);
      RecyclerView.LayoutParams layoutParams =
          (RecyclerView.LayoutParams) child.getLayoutParams();
      final int top = child.getBottom() + layoutParams.bottomMargin;
      final int bottom = top + mDividerHeight;
      if (mPaint != null) {
        canvas.drawRect(left + mMarginLeft, top, right, bottom, mPaint);
      }
    }
  }
}

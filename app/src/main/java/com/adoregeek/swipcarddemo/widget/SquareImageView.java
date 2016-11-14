package com.adoregeek.swipcarddemo.widget;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class SquareImageView extends ImageView {

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
        int childWidthSpec=getMeasuredWidth();
        heightMeasureSpec=widthMeasureSpec=MeasureSpec.makeMeasureSpec(childWidthSpec, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

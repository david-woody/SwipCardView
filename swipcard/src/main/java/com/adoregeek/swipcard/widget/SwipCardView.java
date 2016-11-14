package com.adoregeek.swipcard.widget;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class SwipCardView extends FrameLayout {
    public static final String TAG = SwipCardView.class.getSimpleName();

    enum TYPE {
        //自动滑动
        AUTOSLIDING,
        //手动拖动
        DRAGGING;
    }

    private ViewDragHelper mViewDragHelper;

    public SwipCardView(Context context) {
        super(context);
        init(context);
    }

    public SwipCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public SwipCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                mViewDragHelper.settleCapturedViewAt(originLeft, originTop);
                invalidate();
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    int originLeft;
    int originTop;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        originLeft=getChildAt(0).getLeft();
        originTop = getChildAt(0).getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}

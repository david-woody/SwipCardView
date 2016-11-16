package com.adoregeek.swipcard.widget;

import android.content.Context;
import android.support.v4.os.TraceCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private int state;
    private boolean isReleased = true;
    private ViewDragHelper mViewDragHelper;
    private Adapter mAdapter;
    private int mItemCount;
    private int mOffsetY = 40;

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
                return isReleased;
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

            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }


            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                if (state == mViewDragHelper.STATE_SETTLING) {
                    isReleased = false;
                } else if (state == mViewDragHelper.STATE_DRAGGING) {
                    isReleased = false;
                } else if (state == mViewDragHelper.STATE_IDLE) {
                    isReleased = true;
                }
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
        originLeft = getChildAt(0).getLeft();
        originTop = getChildAt(0).getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;
        mItemCount = mAdapter.getItemCount();
        layoutSwipcards();
    }

    public Adapter getAdapter() {
        return mAdapter;
    }

    private void layoutSwipcards() {
        for (int i = 0; i < mItemCount; i++) {
            addSwipcards(i);
        }
    }

    private void addSwipcards(int position) {
//        View postCard = mAdapter.getView(position);
//        if (position == 0) {
//            postCard.setScaleX(1);
//            postCard.setScaleY(1);
//            postCard.setTranslationY(0);
//        } else if (position == 1) {
//            postCard.setScaleX(0.95f);
//            postCard.setScaleY(0.95f);
//            postCard.setTranslationY(mOffsetY);
//        } else {
//            postCard.setScaleX(0.5f);
//            postCard.setScaleY(0.5f);
//            postCard.setTranslationY(2 * mOffsetY);
//            postCard.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).start();
//        }
//        addView(postCard, 0);
//        mCurrentPosition++;
    }


    public static abstract class Adapter<VH extends ViewHolder> {
        List<View> mViews = new ArrayList<>();

        public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

        public abstract void onBindViewHolder(VH holder, int position);

        public final VH createViewHolder(ViewGroup parent, int viewType) {
            final VH holder = onCreateViewHolder(parent, viewType);
            return holder;
        }

        public abstract int getItemCount();
    }

    public static abstract class ViewHolder {
        public final View itemView;
        long mItemId = NO_ID;

        public ViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }
    }
}

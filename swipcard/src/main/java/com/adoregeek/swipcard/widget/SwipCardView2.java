package com.adoregeek.swipcard.widget;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class SwipCardView2 extends RecyclerView {
    public static final String TAG = SwipCardView2.class.getSimpleName();

    private boolean isReleased = true;
    private ViewDragHelper mViewDragHelper;
    private int mMinDistance = 200;
    private int mDropDirection;
    public static final int DIRECTION_LEFT = 1 << 1;
    public static final int DIRECTION_RIGHT = 1 << 2;
    private OnPostcardDismissListener mOnPostcardDismissListener;
    private float ROTATION_DEGREES = 2f;
    
    public SwipCardView2(Context context) {
        super(context);
        init(context);
    }

    public SwipCardView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public SwipCardView2(Context context, AttributeSet attrs, int defStyleAttr) {
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
                float releasedLeft = releasedChild.getX();
                float releasedTop = releasedChild.getY();
                if (Math.abs(releasedLeft - originLeft) > mMinDistance) {
                    if (releasedLeft > originLeft)
                        mDropDirection = DIRECTION_RIGHT;
                    else
                        mDropDirection = DIRECTION_LEFT;
                    dropSwipCard(mDropDirection);
                } else {
                    mViewDragHelper.settleCapturedViewAt(originLeft, originTop);
                    invalidate();
                }
            }


            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                if (isReleased) {
                    originLeft = capturedChild.getLeft();
                    originTop = capturedChild.getTop();
                    System.out.println("originLeft=" + originLeft + "originTop=" + originTop);
                }
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

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                System.out.println("lef=" + left + "top=" + top + "dx=" + dx + "dy=" + dy);
                final float dxx = originLeft - left;
                final float dyy = originTop - top;

                adjustChildrenUnderTop();
            }
        });
    }

    private void adjustChildrenUnderTop() {

    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }

    private void dropSwipCard(int mDropDirection) {
        if (mOnPostcardDismissListener != null) {
            mOnPostcardDismissListener.onPostcardDismiss(mDropDirection);
        }
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
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public interface OnPostcardDismissListener {
        void onPostcardDismiss(int direction);
    }

    public void setOnPostcardDismissListener(OnPostcardDismissListener onPostcardDismissListener) {
        mOnPostcardDismissListener = onPostcardDismissListener;
    }
}

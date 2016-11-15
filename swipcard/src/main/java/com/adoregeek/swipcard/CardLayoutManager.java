package com.adoregeek.swipcard;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/15 0015.
 */

public class CardLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    private final int mOffsetY = 40;

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);
        for (int i = getItemCount() - 1; i > -1; i--) {
            View child = recycler.getViewForPosition(i);
            measureChildWithMargins(child, 0, 0);
            addView(child);
            int width = getDecoratedMeasuredWidth(child);
            int height = getDecoratedMeasuredHeight(child);
            layoutDecorated(child, (getWidth() - width) / 2, mOffsetY, (getWidth() - width) / 2 + width,mOffsetY+ height);
            if (i == 0) {
                child.setScaleX(1);
                child.setScaleY(1);
                child.setTranslationY(0);
            } else if (i == 1) {
                child.setScaleX(0.95f);
                child.setScaleY(0.95f);
                child.setTranslationY(mOffsetY);
            } else {
                child.setScaleX(0.5f);
                child.setScaleY(0.5f);
                child.setTranslationY(2 * mOffsetY);
                child.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).start();
            }
        }
//        for (int i = 0; i < getItemCount(); i++) {
//            View view = recycler.getViewForPosition(i);
//            measureChildWithMargins(view, 0, 0);
//            int width = getDecoratedMeasuredWidth(view);
//            int height = getDecoratedMeasuredHeight(view);
//            if (i == 0) {
//                layoutDecorated(view, 0, 0, width, height);
//                view.setScaleX(1);
//                view.setScaleY(1);
//                view.setTranslationY(0);
//            } else if (i == 1) {
//                layoutDecorated(view,  0, 0, width, height);
//                view.setScaleX(0.95f);
//                view.setScaleY(0.95f);
//                view.setTranslationY(mOffsetY);
//            } else {
//                layoutDecorated(view, 0, 0, width, height);
//                view.setScaleX(0.5f);
//                view.setScaleY(0.5f);
//                view.setTranslationY(2 * mOffsetY);
//                view.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).start();
//            }
//        }


    }
}

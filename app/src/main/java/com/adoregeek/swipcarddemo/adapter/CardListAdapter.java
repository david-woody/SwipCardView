package com.adoregeek.swipcarddemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.adoregeek.swipcard.adapter.BaseListAdapter;
import com.adoregeek.swipcarddemo.R;
import com.adoregeek.swipcarddemo.bean.CardBean;
import com.adoregeek.swipcarddemo.widget.SquareImageView;
import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/11/15 0015.
 */

public class CardListAdapter extends BaseListAdapter<CardBean, CardListAdapter.ViewHolder> {

    private Context mContext;

    public CardListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_card, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, CardBean bean) {
        Glide.with(mContext)
                .load(bean.url)
                .placeholder(R.mipmap.commactivity_imgbg)
                .error(R.mipmap.commactivity_imgbg)
                .centerCrop()
                .into(holder.ivPhoto);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        SquareImageView ivPhoto;
        Button btClick;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ivPhoto = (SquareImageView) rootView.findViewById(R.id.ivPhoto);
            btClick = (Button) rootView.findViewById(R.id.btClick);
        }
    }
}

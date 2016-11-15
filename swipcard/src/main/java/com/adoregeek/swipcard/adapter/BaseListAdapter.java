package com.adoregeek.swipcard.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */

public abstract class BaseListAdapter<T, HV extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<HV> {

    List<T> list = new ArrayList<>();
    OnItemClickListener<T, HV> listener;

    @Override
    public void onBindViewHolder(HV holder, int position) {
        final T bean = list.get(position);
        onBindViewHolder(holder, bean);
    }

    public abstract void onBindViewHolder(HV holder, T bean);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(Collection<T> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }

    public void add(int position, T t) {
        list.add(position, t);
        notifyDataSetChanged();
    }


    public void remove(T t) {
        list.remove(t);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }


    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public static interface OnItemClickListener<B, HV extends RecyclerView.ViewHolder> {
        void onItemListener(HV holder, B bean);
    }

    public void setListener(OnItemClickListener<T, HV> listener) {
        this.listener = listener;
    }


}

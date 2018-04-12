package com.zonsim.examhelper.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ^-^
 * Created by tang-jw on 11/6.
 */

public class VH extends RecyclerView.ViewHolder {
    
    private final View mItemView;
    private SparseArray<View> mViews;
    
    private VH(View itemView) {
        super(itemView);
        mItemView = itemView;
        mViews = new SparseArray<>();
    }
    
    
    public static VH createViewHolder(View itemView) {
        return new VH(itemView);
    }
    
    public static VH createViewHolder(ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        return new VH(itemView);
    }
    
    public View getItemView() {
        return mItemView;
    }
    
    @SuppressWarnings("all")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    
}

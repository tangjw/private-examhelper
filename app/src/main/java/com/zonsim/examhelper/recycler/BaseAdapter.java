package com.zonsim.examhelper.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * ^-^
 * Created by tang-jw on 11/19.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    protected List<T> mList;
    private String TAG = "BaseAdapter";
    private OnItemClickListener mOnItemClickListener;
    
    public BaseAdapter(@NonNull List<T> list) {
        mList = list;
    }
    
    public List<T> getList() {
        return mList;
    }
    
    public void replaceData(@NonNull List<T> list) {
        if (list.size() > 0) {
    
            mList = list;
    
            notifyDataSetChanged();
        }
    }
    
    public void addData(@NonNull List<T> list) {
        if (list.size() > 0) {
        
            mList.addAll(list);
            //notifyDataSetChanged();
            //notifyItemInserted(getItemCount());
            //notifyItemRangeChanged(getItemCount());
            notifyItemRangeInserted(mList.size() - list.size(), mList.size());
        }
    }
    
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VH holder = VH.createViewHolder(parent, getResLayout());
        setItemClick(holder);
        return holder;
    }
    
    protected abstract int getResLayout();
    
    protected abstract void onBaseBindViewHolder(VH holder, int position);
    
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBaseBindViewHolder((VH) holder, position);
    }
    
    @Override
    public int getItemCount() {
        return mList.size();
    }
    
    protected void setItemClick(final VH viewHolder) {
        viewHolder.getItemView()
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            int position = viewHolder.getAdapterPosition();
                            mOnItemClickListener.onItemClick(v, position);
                        }
                    }
                });
        
    }
    
    protected void loadImage(ImageView imageView, String url) {
        
      /*  GlideApp.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.default_img)
                .error(R.drawable.default_img)
                .into(imageView);*/
    }
    
    protected void setTextString(TextView textView, String text) {
        textView.setText(text);
    }
    
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    
}

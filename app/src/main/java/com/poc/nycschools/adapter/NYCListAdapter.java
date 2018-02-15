package com.poc.nycschools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poc.nycschools.R;
import com.poc.nycschools.model.NYCInfo;

import java.util.List;

public class NYCListAdapter extends RecyclerView.Adapter<NYCListAdapter.ViewHolder> {

    private List<NYCInfo> list;
    private OnOptionClickListner onOptionClickListner;

    public NYCListAdapter(List<NYCInfo> list, OnOptionClickListner onOptionClickListner) {
        this.list = list;
        setOnOptionClickListner(onOptionClickListner);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int arg) {
        bindRowUi(holder, list.get(arg));
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        View itemLayoutView = getRowItemLayout(arg0.getContext(), arg0);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, false);
        return viewHolder;
    }


    private void bindRowUi(final ViewHolder holder, final NYCInfo info) {
        holder.schoolNameView.setText(info.getName());
        holder.schoolNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnOptionClickListner().onOption(info, "details",holder.itemView);
            }
        });
    }

    private View getRowItemLayout(Context context, ViewGroup arg0) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_view, arg0, false);
        return view;
    }

    public OnOptionClickListner getOnOptionClickListner() {
        return onOptionClickListner;
    }

    public void setOnOptionClickListner(OnOptionClickListner onOptionClickListner) {
        this.onOptionClickListner = onOptionClickListner;
    }

    /**
     * view holder to reuse list item views.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView schoolNameView;
        public View itemView;

        public ViewHolder(View itemView, boolean b) {
            super(itemView);
            this.itemView = itemView;
            schoolNameView = (TextView) itemView.findViewById(R.id.txv_school_name);
        }
    }

    public interface OnOptionClickListner {
        void onOption(NYCInfo info, String option, View view);
    }
}

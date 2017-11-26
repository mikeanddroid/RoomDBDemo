package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.R;

import java.util.List;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils
 * Created by GiveMeWingzzz on 11/23/2017.
 */

public class DialogRecyclerViewAdapter extends RecyclerView.Adapter<DialogRecyclerViewAdapter.ColorShadeViewHolder> {

    List<Integer> colorsList;
    Context mContext;
    protected DialogItemListener mListener;

    public DialogRecyclerViewAdapter(Context context, List<Integer> values, DialogItemListener dialogItemListener) {

        colorsList = values;
        mContext = context;
        mListener = dialogItemListener;
    }

    static class ColorShadeViewHolder extends RecyclerView.ViewHolder {

        public TextView dialogtextView;
        public ImageView dialogimageView;
        public RelativeLayout dialogrelativeLayout;

        public ColorShadeViewHolder(View v) {
            super(v);

            dialogtextView = (TextView) v.findViewById(R.id.dialogColorText);
            dialogimageView = (ImageView) v.findViewById(R.id.dialogColorImage);
            dialogrelativeLayout = (RelativeLayout) v.findViewById(R.id.dialogColorRelativeLayout);

        }

    }

    @Override
    public ColorShadeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_grid_color_shade_item, parent, false);

        return new ColorShadeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorShadeViewHolder colorViewHolder, final int position) {

        final int color = colorsList.get(position);

        colorViewHolder.dialogtextView.setText("" + color);
        colorViewHolder.dialogimageView.setBackground(mContext.getDrawable(R.drawable.circle_shape_outline));
        colorViewHolder.dialogimageView.setBackgroundColor(color);
        colorViewHolder.dialogrelativeLayout.setBackgroundColor(color);

        colorViewHolder.dialogtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(color, position);
                }
            }
        });

        colorViewHolder.dialogimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(color, position);
                }
            }
        });

        colorViewHolder.dialogrelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(color, position);
                }
            }
        });

    }

    public void swap(List<Integer> newData) {
        if (newData == null || newData.size() == 0)
            return;
        if (colorsList != null && colorsList.size() > 0)
            colorsList.clear();
        colorsList.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return colorsList.size();
    }

    public interface DialogItemListener {
        void onItemClick(int colorsGen, int position);
    }
}
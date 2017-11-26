package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ColorViewHolder> {

    List<Integer> colorsList;
    Context mContext;
    protected ItemListener mListener;

    public RecyclerViewAdapter(Context context, List<Integer> values, ItemListener itemListener) {

        colorsList = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public ColorViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);

        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorViewHolder colorViewHolder, final int position) {

        final int color = colorsList.get(position);

        colorViewHolder.textView.setText("COLOR VALUE : \n" + color + "\nPosition : " + position);
        colorViewHolder.imageView.setBackgroundColor(color);
        colorViewHolder.relativeLayout.setBackgroundColor(color);

        colorViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(color, position);
                }
            }
        });

        colorViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(color, position);
                }
            }
        });

        colorViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(color, position);
                }
            }
        });

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        colorViewHolder.relativeLayout.startAnimation(alphaAnimation);

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

    public interface ItemListener {
        void onItemClick(int colorsGen, int position);
    }
}
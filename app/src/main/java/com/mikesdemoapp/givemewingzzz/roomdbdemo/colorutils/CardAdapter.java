package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.R;
import com.mikesdemoapp.givemewingzzz.roomdbdemo.RoomDbDemoApp;

import java.util.List;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils
 * Created by GiveMeWingzzz on 11/25/2017.
 */

public class CardAdapter extends BaseAdapter {

    List<Integer> colorShadeList;
    Context context;
    LayoutInflater inflater;
    ViewHolder holder = null;
    FileLog fileLog;
    ColorsUtils colorsUtils;
    private static final String TAG = CardAdapter.class.getSimpleName();

    public CardAdapter(Context context, List<Integer> colorShadeList) {
        this.context = context;
        this.colorShadeList = colorShadeList;
        this.inflater = LayoutInflater.from(context);
        fileLog = RoomDbDemoApp.get().getFileLog();
        colorsUtils = new ColorsUtils();
    }

    @Override
    public int getCount() {
        return colorShadeList.size();
    }

    @Override
    public Integer getItem(int pos) {
        return colorShadeList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = inflater.inflate(R.layout.shades_layout_row, parent, false);
            holder = new ViewHolder();
            holder.cardImage = (ImageView) view.findViewById(R.id.stackShadeView);
            holder.cardValue = (TextView) view.findViewById(R.id.stackShadeTextView);
            holder.cardColorValue = (TextView) view.findViewById(R.id.stackShadeValueTextView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        int colorValue = getItem(position);
        int colorValueNext = getItem(2);

        fileLog.d(TAG, " getView : " + " colorValue = " + colorValue);

        ColorModel rgb = colorsUtils.getRGBFromHex(Long.valueOf(String.valueOf(colorValue)));

        holder.cardValue.setTextColor(colorValueNext);
        holder.cardValue.setText("COLOR VALUE");
        holder.cardColorValue.setText("" + colorValue + "\n\nR [ " + rgb.getR() + " ] " + "\nG [ " + rgb.getG() + " ] " + "\nB [ " + rgb.getB() + " ] ");
        holder.cardImage.setBackgroundColor(colorValue);

        return view;

    }

    static class ViewHolder {
        ImageView cardImage;
        TextView cardValue;
        TextView cardColorValue;
    }

}

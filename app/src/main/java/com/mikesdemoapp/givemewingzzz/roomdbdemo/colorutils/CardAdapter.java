package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.content.Context;
import android.graphics.Color;
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

    String testC;

    List<Integer> colorShadeList;
    Context context;
    LayoutInflater inflater;
    ViewHolder holder = null;
    FileLog fileLog;
    ColorsUtils colorsUtils;

    private CardStackListener cardStackListener;
    private static final String TAG = CardAdapter.class.getSimpleName();

    public CardAdapter(Context context, List<Integer> colorShadeList) {
        this.context = context;
        this.colorShadeList = colorShadeList;
        this.inflater = LayoutInflater.from(context);
        fileLog = RoomDbDemoApp.get().getFileLog();
        colorsUtils = new ColorsUtils();
    }

    public void setCardStackListener(CardStackListener cardStackListener) {
        this.cardStackListener = cardStackListener;
    }

    public void swap(List<Integer> datas) {
        if (datas == null || datas.size() == 0)
            return;
        if (colorShadeList != null && colorShadeList.size() > 0)
            colorShadeList.clear();
        colorShadeList.addAll(datas);
        notifyDataSetChanged();

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
        int colorValueNext;

        int[] mainRGB = ColorsUtils.getRGB(colorShadeList.get(3));

        String hex = String.format("#77%02x%02x%02x", mainRGB[0], mainRGB[1], mainRGB[2]);

        if (getCount() > 2) {
            colorValueNext = getItem(2);
        } else {
            colorValueNext = Color.DKGRAY;
        }

        int[] rgbArray = ColorsUtils.getRGB(colorShadeList.get(position));

        String colorDetails = colorValue + " ( " + rgbArray[0] + ", " + rgbArray[1] + ", " + rgbArray[2] + " ) ";

        fileLog.d(TAG, " getView : " + " colorValue = " + colorValue);
        fileLog.d(TAG, " getView : " + " colorValue Details = " + colorDetails);

        ColorModel rgb = colorsUtils.getRGBFromHex(Long.valueOf(String.valueOf(colorValue)));

        holder.cardValue.setTextColor(getItem(0));
        holder.cardValue.setText("COLOR VALUE");
        holder.cardColorValue.setTextColor(Color.WHITE);
        holder.cardColorValue.setText(colorDetails);
        holder.cardImage.setBackgroundColor(colorValue);


//        int lastCardPosition = colorShadeList.size() - 1;

        cardStackListener.onPositionChanged(position);


        return view;

    }

    public interface CardStackListener {

        void onPositionChanged(final int position);

    }

    static class ViewHolder {
        ImageView cardImage;
        TextView cardValue;
        TextView cardColorValue;
    }

}

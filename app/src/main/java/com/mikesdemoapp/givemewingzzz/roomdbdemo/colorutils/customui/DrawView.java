package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.customui
 * Created by GiveMeWingzzz on 11/30/2017.
 */

public class DrawView extends View {
    Paint paint = new Paint();
    float[][] points;

    public DrawView(Context context, float[][] points2, int k) {
        super(context);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        this.points = points2;
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (int i = 0; i < points.length; i++) {
            canvas.drawLine(points[i][0], points[i][1], points[i][2], points[i][3], paint);
        }
    }
}
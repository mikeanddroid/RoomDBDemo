package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.graphics.Color;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.RoomDbDemoApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils
 * Created by GiveMeWingzzz on 11/25/2017.
 */

public class ColorsUtils {

    private static final String TAG = ColorsUtils.class.getSimpleName();
    FileLog fileLog;

    public ColorsUtils() {
        fileLog = RoomDbDemoApp.get().getFileLog();
    }

    /*****************************************************************************************/

    public List<Integer> getColorBands(ColorModel colorsGen, int bands) {

        List<Integer> colorBands = new ArrayList<>(bands);
        for (int index = 0; index < bands; index++) {
            colorBands.add(darken(colorsGen, (double) index / (double) bands));
        }

        return colorBands;

    }

    public int darken(ColorModel colorModel, double fraction) {

        int red = (int) Math.round(Math.max(0, colorModel.getR() - 255 * fraction));
        int green = (int) Math.round(Math.max(0, colorModel.getG() - 255 * fraction));
        int blue = (int) Math.round(Math.max(0, colorModel.getB() - 255 * fraction));

//        int alpha = colorModel.getAlpha();

        Color color = new Color();

//        ColorSpace sRgb = ColorSpace.get(ColorSpace.Named.SRGB);

//        // Convert from CIE L*a*b* (color model Lab) to Rec.709 (color model RGB)
//        ColorSpace.Connector connector = ColorSpace.connect(
//                ColorSpace.get(ColorSpace.Named.CIE_LAB),
//                ColorSpace.get(ColorSpace.Named.BT709));

//        color.convert(sRgb);

        return Color.argb(255, red, green, blue);

    }

    /*****************************************************************************************/

    public ColorModel getRGBFromHex(Long colorValue) {

        // edited to support big numbers bigger than 0x80000000
        int color = Integer.valueOf(String.valueOf(colorValue));

        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;

        ColorModel colorModel = new ColorModel(r, g, b);

        fileLog.d(TAG, "colorValue = [ " + colorValue + " ] : " + " R = [ " + colorModel.getR() + " ] " + " G = [ " + colorModel.getG() + " ] " + " B = [ " + colorModel.getB() + " ] ");

        return colorModel;
    }

}

package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.os.AsyncTask;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.RoomDbDemoApp;

import java.util.List;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils
 * Created by GiveMeWingzzz on 11/25/2017.
 */

public class ColorsShadesTask extends AsyncTask<Long, Integer, List<Integer>> {

    int desiredShades;
    ColorsShadesResult shadesResult;

    private FileLog fileLog;
    private static final String TAG = ColorsShadesTask.class.getSimpleName();

    public ColorsShadesTask(ColorsShadesResult shadesResult, int desiredShades) {
        this.shadesResult = shadesResult;
        this.desiredShades = desiredShades;

        fileLog = RoomDbDemoApp.get().getFileLog();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        shadesResult.onColorShadesPreExecute();
    }

    @Override
    protected List<Integer> doInBackground(Long... colorValueParams) {

        ColorsUtils colorsUtils = new ColorsUtils();

        ColorModel colorModel = colorsUtils.getRGBFromHex(colorValueParams[0]);

        List<Integer> colorsShadesList = colorsUtils.getColorBands(colorModel, desiredShades);

        fileLog.d(TAG, " Color Shades List Size --> " + colorsShadesList.size());

        return colorsShadesList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        shadesResult.onColorShadesProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Integer> shadesList) {
        super.onPostExecute(shadesList);
        shadesResult.onColorShadesPostExecute(shadesList);
    }

    public interface ColorsShadesResult {

        void onColorShadesPreExecute();

        void onColorShadesProgressUpdate(Integer... values);

        void onColorShadesPostExecute(List<Integer> colorShadesList);

    }

}

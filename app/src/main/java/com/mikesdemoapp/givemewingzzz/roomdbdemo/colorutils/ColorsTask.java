package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.graphics.Color;
import android.os.AsyncTask;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.RoomDbDemoApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils
 * Created by GiveMeWingzzz on 11/23/2017.
 */

public class ColorsTask extends AsyncTask<Object, Object, Map<Long, Integer>> {

    private static final String TAG = ColorsTask.class.getSimpleName();

    private ColorsResult colorsResult;
    private List<ColorModel> colorModelList;

    private List<Integer> colorList;

    private Map<Long, Integer> colorsIntMap;
    private Map<Long, ColorModel> colorsModelMap;

    private final long RED_RANGE = (256 * 256 * 256);
    private final long COLOR_GEN_COUNT = 1000;

    private FileLog fileLog;

    public ColorsTask(ColorsResult colorsResult) {
        this.colorsResult = colorsResult;
        colorModelList = new ArrayList<>();
        colorList = new ArrayList<>();

        colorsIntMap = new HashMap<>();
        colorsModelMap = new HashMap<>();

        fileLog = RoomDbDemoApp.get().getFileLog();

    }

    @Override
    protected Map<Long, Integer> doInBackground(Object... rgbValue) {

        Map<Long, ColorModel> colorsModelMap = getRGBColorMap();
        Map<Long, Integer> colorsIntMap = getRandomColorsMap();

        fileLog.v(TAG, "Colors Map Size --> " + colorsIntMap.size());

        return colorsIntMap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        colorsResult.onPreExecute();
    }


    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        colorsResult.onProgressUpdate();
    }

    @Override
    protected void onPostExecute(Map<Long, Integer> colorValue) {
        super.onPostExecute(colorValue);

        Map<Long, Integer> tempColorValue = sortHashMapByValues(colorValue);
        colorsResult.onPostExecute(tempColorValue);
    }

    public interface ColorsResult {

        void onPreExecute();

        void onProgressUpdate(Integer... values);

        void onPostExecute(Map<Long, Integer> colorsList);

        void onColorIntMapReady(Map<Long, Integer> colorsIntMap);

    }

    /**
     * Sort a hashmap by values.
     *
     * @param passedMap Map passed with params Long, Integer
     * @return LinkedHashMap Long, Integer;
     */
    public LinkedHashMap<Long, Integer> sortHashMapByValues(
            Map<Long, Integer> passedMap) {

        List<Long> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());

        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<Long, Integer> sortedMap =
                new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();

        while (valueIt.hasNext()) {
            int val = valueIt.next();
            Iterator<Long> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Long key = keyIt.next();
                int comp1 = passedMap.get(key);
                int comp2 = val;

                if (comp1 == comp2) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    /**
     * COLOR UTILS
     */

    public int buildRandomColor() {

        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);

        return Color.argb(255, R, G, B);

    }

    private int buildColor(int R, int G, int B) {

        if (R > 256) {
            return -1;
        }

        if (G > 256) {
            return -1;
        }

        if (B > 256) {
            return -1;
        }

        return Color.argb(255, R, G, B);
    }

    // Colors List Util

    class RModel {

        private int R;

        public RModel(int r) {
            R = r;
        }

        public int getR() {
            return R;
        }

        public void setR(int r) {
            R = r;
        }
    }

    List<RModel> R_COLOR_LIST = new ArrayList<>();

    class GModel {

        private int G;

        public GModel(int g) {
            G = g;
        }

        public int getG() {
            return G;
        }

        public void setG(int g) {
            G = g;
        }
    }

    List<GModel> G_COLOR_LIST = new ArrayList<>();

    class BModel {

        private int B;

        public BModel(int b) {
            B = b;
        }

        public int getB() {
            return B;
        }

        public void setB(int b) {
            B = b;
        }
    }

    List<BModel> B_COLOR_LIST = new ArrayList<>();

    int R_CONST = 256;
    int G_CONST = 256;
    int B_CONST = 256;

    private List<RModel> getR_COLOR_LIST() {

        for (int R = 0; R < R_CONST; R++) {

            R_COLOR_LIST.add(new RModel(R));

        }

        return R_COLOR_LIST;

    }

    private List<GModel> getG_COLOR_LIST() {

        for (int G = 0; G < G_CONST; G++) {

            G_COLOR_LIST.add(new GModel(G));

        }

        return G_COLOR_LIST;

    }

    private List<BModel> getB_COLOR_LIST() {

        for (int B = 0; B < B_CONST; B++) {

            B_COLOR_LIST.add(new BModel(B));

        }

        return B_COLOR_LIST;

    }


    private List<Integer> buildColorsList() {

        int R = 0;
        int G = 0;
        int B = 0;

        List<RModel> rModelList = getR_COLOR_LIST();
        List<GModel> gModelList = getG_COLOR_LIST();
        List<BModel> bModelList = getB_COLOR_LIST();

        final int R_MODEL_LIST_SIZE = rModelList.size();
        final int G_MODEL_LIST_SIZE = gModelList.size();
        final int B_MODEL_LIST_SIZE = bModelList.size();

        long R_COUNTER = -1;
        long G_COUNTER = -1;
        long B_COUNTER = -1;

//        while (R_COUNTER < R_MODEL_LIST_SIZE && G_COUNTER < G_MODEL_LIST_SIZE && B_COUNTER < B_MODEL_LIST_SIZE) {}

        for (int i = R; i <= R_MODEL_LIST_SIZE - 1; i++) {

            String resultMessage = "Main R Loop --> " + "R = [ " + i + " ] + G [ " + G + " ] B [ " + B + " ] ";

            fileLog.v(TAG, resultMessage);

            System.out.println(resultMessage);

            int R_COLOR = buildColor(i, 0, 0);

            colorList.add(R_COLOR);

            for (int x = 0; x < G_CONST; x++) {

                fileLog.v(TAG, "Inner First X Loop --> " + "R = [ " + 255 + " ] + G [ " + x + " ] B [ " + 0 + " ] ");

                int x_index = buildColor(255, x, x);

                colorList.add(x_index);

            }

            for (int x = 0; x < G_CONST; x++) {

                fileLog.v(TAG, "Inner Second X Loop --> " + "R = [ " + 255 + " ] + G [ " + 0 + " ] B [ " + x + " ] ");

                int x_index = buildColor(255, x, x);

                colorList.add(x_index);

            }

        }

        for (int i = 1; i <= G_MODEL_LIST_SIZE - 1; i++) {

            fileLog.v(TAG, "Main G Loop --> " + "R = [ " + R + " ] + G [ " + i + " ] B [ " + B + " ] ");

//            System.out.println("G Loop --> " + "R = [ " + R + " ] + G [ " + i + " ] B [ " + B + " ] ");

            int G_COLOR = buildColor(0, i, 0);

            colorList.add(G_COLOR);

            for (int x = 0; x < G_CONST; x++) {

                fileLog.v(TAG, "Inner First X Loop --> " + "R = [ " + x + " ] + G [ " + 255 + " ] B [ " + x + " ] ");

//                System.out.println("Inner X Loop --> " + "R = [ " + x + " ] + G [ " + 255 + " ] B [ " + x + " ] ");

                int x_index = buildColor(x, 255, x);

                colorList.add(x_index);

            }

        }

        for (int i = 1; i <= B_MODEL_LIST_SIZE - 1; i++) {

            fileLog.v(TAG, "Main B Loop --> " + "R = [ " + R + " ] + G [ " + G + " ] B [ " + i + " ] ");

//            System.out.println("B Loop --> " + "R = [ " + R + " ] + G [ " + G + " ] B [ " + i + " ] ");

            int B_COLOR = buildColor(0, 0, i);

            colorList.add(B_COLOR);

            for (int x = 0; x < G_CONST; x++) {

                fileLog.v(TAG, "Inner X Loop --> " + "R = [ " + x + " ] + G [ " + x + " ] B [ " + 255 + " ] ");

//                System.out.println("Inner X Loop --> " + "R = [ " + x + " ] + G [ " + x + " ] B [ " + 255 + " ] ");

                int x_index = buildColor(x, x, 255);

                colorList.add(x_index);

            }

        }

        for (int a = 0; a < R_CONST; a++) {

            fileLog.v(TAG, "Outer A Loop --> " + "R = [ " + 255 + " ] + G [ " + a + " ] B [ " + 255 + " ] ");

//            System.out.println("Outer A Loop --> " + "R = [ " + 255 + " ] + G [ " + a + " ] B [ " + 255 + " ] ");

            int a_index = buildColor(a, 255, 255);
            colorList.add(a_index);

        }

        for (int b = 0; b < G_CONST; b++) {

            fileLog.v(TAG, "Outer B Loop --> " + "R = [ " + 255 + " ] + G [ " + b + " ] B [ " + 255 + " ] ");

//            System.out.println("Outer B Loop --> " + "R = [ " + 255 + " ] + G [ " + b + " ] B [ " + 255 + " ] ");

            int b_index = buildColor(255, b, 255);
            colorList.add(b_index);

        }

        for (int c = 0; c < B_CONST; c++) {

            fileLog.v(TAG, "Outer C Loop --> " + "R = [ " + 255 + " ] + G [ " + 255 + " ] B [ " + c + " ] ");

//            System.out.println("Outer C Loop --> " + "R = [ " + 255 + " ] + G [ " + 255 + " ] B [ " + c + " ] ");

            int c_index = buildColor(255, 255, c);
            colorList.add(c_index);

        }

//        if (G == 0) {
//
//            for (int j = G; j < G_MODEL_LIST_SIZE; j += 2) {
//
////                    fileLog.v(TAG, "R J Loop --> " + "R = [ " + 255 + " ] + G [ " + j + " ] B [ " + B + " ] ");
//
//                System.out.println("R J Loop --> " + "R = [ " + 255 + " ] + G [ " + j + " ] B [ " + B + " ] ");
//
//                int G_COLOR = buildColor(255, j, B);
//                colorList.add(G_COLOR);
//
//            }
//
//        }
//
//        if (B == 0) {
//
//            for (int k = B; k < B_MODEL_LIST_SIZE; k += 2) {
//
////                    fileLog.v(TAG, "R K Loop --> " + "R = [ " + 255 + " ] + G [ " + 255 + " ] B [ " + k + " ] ");
//
//                System.out.println("R K Loop --> " + "R = [ " + 255 + " ] + G [ " + 255 + " ] B [ " + k + " ] ");
//
//                int B_COLOR = buildColor(255, 255, k);
//                colorList.add(B_COLOR);
//
//            }
//
//        }

        return colorList;

    }

    private List<Integer> buildRandomColors() {

        final int colorGenSize = getRGBList().size();

        for (int i = 0; i < colorGenSize; i++) {
            colorList.add(buildRandomColor());
        }
        return colorList;
    }

    private Map<Long, Integer> getRandomColorsMap() {

        for (int i = 0; i < 1000; i++) {

            int randomColor = buildRandomColor();

            colorList.add(randomColor);

            Long key = Long.valueOf(String.valueOf(i));

            fileLog.v(TAG, "Color Key = " + key + " Value : [ " + randomColor + " ] ");

            colorsIntMap.put(key, randomColor);

        }

        fileLog.v(TAG, "Colors List Size --> " + colorList.size());
        fileLog.v(TAG, "Colors Random Map Size --> " + colorsIntMap.size());
        return colorsIntMap;
    }

    // Todo Make loops based on the RGB
    private Map<Long, Integer> getColorsMap() {

        List<Integer> colorsList = buildColorsList();

        System.out.println("Colors List (MAP) Size --> " + colorsList.size());

        fileLog.v(TAG, "Colors List (MAP) Size :" + colorsIntMap.size());

        for (int i = 0; i < colorsList.size(); i++) {

            Long key = Long.valueOf(String.valueOf(i));

            colorsIntMap.put(key, colorsList.get(i));
        }

        return colorsIntMap;
    }


    private Map<Long, ColorModel> getRGBColorMap() {

        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);

        for (int i = 0; i < COLOR_GEN_COUNT; i++) {

            Long key = Long.valueOf(String.valueOf(i));

            ColorModel colorModel = new ColorModel(R, G, B);
            colorModelList.add(colorModel);

            colorsModelMap.put(key, colorModel);

        }

        return colorsModelMap;

    }

    private List<ColorModel> getRGB(int R, int G, int B) {

        for (int i = 0; i < COLOR_GEN_COUNT; i++) {

            ColorModel colorModel = new ColorModel(R, G, B);
            colorModelList.add(colorModel);

        }

        return colorModelList;

    }

    private List<ColorModel> getRGBList() {

        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);

        int maxRange = 256 * 256 * 256;

        for (int i = 0; i < 100; i++) {

            ColorModel colorModel = new ColorModel(R, G, B);
            colorModelList.add(colorModel);

        }

        return colorModelList;

    }

}

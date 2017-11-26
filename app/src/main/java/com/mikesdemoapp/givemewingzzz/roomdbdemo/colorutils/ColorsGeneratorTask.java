package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils
 * Created by GiveMeWingzzz on 11/22/2017.
 */

public class ColorsGeneratorTask extends AsyncTask<Long, Integer, List<Color>> {

    private int red = 0;
    private int green = 0;
    private int blue = 0;

    private int redCounter = -1;
    private int greenCounter = -1;
    private int blueCounter = -1;

    private int[] redArray = new int[256];
    private int[] greenArray = new int[256];
    private int[] blueArray = new int[256];

    private List<ColorsGen> colorsGenList = new ArrayList<>();

    private int redBuildCounter = 0;
    private int greenBuildCounter = 0;
    private int blueBuildCounter = 0;

    Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public ColorsGeneratorTask(Context context) {
        this.context = context;
    }

    List<ColorsGen> colorsGenListMain = new ArrayList<>();
    int color;
    long longColor;
    Color colorObj;

    public List<ColorsGen> getColorsGenList(int red, int green, int blue) {

        while (red < 255) {
            red++;
            color = Color.argb(255, red, green, blue);
        }

        while (red < 255 && green < 255 && blue < 255) {

            red++;

        }

        return new ArrayList<>();
    }

    @Override
    protected List<Color> doInBackground(Long... longs) {

        int[] red = buildRed();
        int[] green = buildGreen();
        int[] blue = buildBlue();
//
//
        System.out.println("Red Length = " + red.length);
        System.out.println("Green Length = " + green.length);
        System.out.println("Blue Length = " + blue.length);
//
//        List<ColorsGen> colorsGenList = buildColorsList(red, green, blue);

//        int rgb = getIntFromColor(240, 50, 70);
//
//        System.out.println("rgb = " + rgb);

//        System.out.println("colorsGenList Size = " + colorsGenList.size());

//        printRGBValuesInArray(colorsGenList);
//
//        for (ColorsGen colorsGen : colorsGenList) {
//
//            int red_ = colorsGen.getRed();
//            int green_ = colorsGen.getGreen();
//            int blue_ = colorsGen.getBlue();
//
//            int rgb = getIntFromColor(240, 50, 70);
//
//            System.out.println("rgb = " + rgb);
//
//        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Color> colors) {
        super.onPostExecute(colors);
    }

    public int getIntFromColor(int Red, int Green, int Blue) {
        Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        Blue = Blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }

    private List<ColorsGen> buildColorsList(int[] red, int[] green, int[] blue) {

        while (red[redBuildCounter] <= 255 && green[greenBuildCounter] <= 255 && blue[blueBuildCounter] <= 255) {

            if (red[redBuildCounter] == 0 && green[greenBuildCounter] == 255 && blue[blueBuildCounter] == 255) {

                redBuildCounter++;
                int redColor = red[buildColorsListFromRGB(red[redBuildCounter], green[greenBuildCounter], blue[blueBuildCounter])];
                int greenColor = green[buildColorsListFromRGB(red[redBuildCounter], green[greenBuildCounter], blue[redBuildCounter])];
                int blueColor = blue[buildColorsListFromRGB(red[redBuildCounter], green[greenBuildCounter], blue[redBuildCounter])];

                ColorsGen colorsGen = new ColorsGen(redColor, greenColor, blueColor);
                colorsGenList.add(colorsGen);

                buildColorsList(red, green, blue);

            } else if (red[redBuildCounter] == 0 && green[greenBuildCounter] == 0 && blue[blueBuildCounter] == 255) {

                redBuildCounter++;
                greenBuildCounter++;
                buildColorsList(red, green, blue);

            } else if (red[redBuildCounter] == 0 && green[greenBuildCounter] == 0 && blue[blueBuildCounter] == 0) {

                redBuildCounter++;
                greenBuildCounter++;
                blueBuildCounter++;
                buildColorsList(red, green, blue);

            } else if (red[redBuildCounter] == 255 && green[greenBuildCounter] == 0 && blue[blueBuildCounter] == 255) {

                greenBuildCounter++;
                buildColorsList(red, green, blue);

            } else if (red[redBuildCounter] == 0 && green[greenBuildCounter] == 255 && blue[blueBuildCounter] == 0) {

                redBuildCounter++;
                blueBuildCounter++;
                buildColorsList(red, green, blue);

            } else if (red[redBuildCounter] == 255 && green[greenBuildCounter] == 0 && blue[blueBuildCounter] == 0) {

                greenBuildCounter++;
                blueBuildCounter++;
                buildColorsList(red, green, blue);

            } else if (red[redBuildCounter] == 255 && green[greenBuildCounter] == 255 && blue[blueBuildCounter] == 0) {

                blueBuildCounter++;
                buildColorsList(red, green, blue);

            }

        }

        return colorsGenList;

    }

    private int buildColorsListFromRGB(int red, int green, int blue) {

        int[] minRGB = new int[]{0, 0, 0};
        int[] maxRGB = new int[]{255, 255, 255};

        while (red <= 255 && green <= 255 && blue <= 255) {

            if (redCounter == 0 && greenCounter == 255 && blueCounter == 255) {
                red++;
                buildColorsListFromRGB(red, green, blue);
            } else if (redCounter == 0 && greenCounter == 0 && blueCounter == 255) {
                red++;
                green++;
                buildColorsListFromRGB(red, green, blue);
            } else if (redCounter == 0 && greenCounter == 0 && blueCounter == 0) {
                red++;
                green++;
                blue++;
                buildColorsListFromRGB(red, green, blue);
            } else if (redCounter == 255 && greenCounter == 0 && blueCounter == 255) {
                green++;
                buildColorsListFromRGB(red, green, blue);
            } else if (redCounter == 0 && greenCounter == 255 && blueCounter == 0) {
                red++;
                green++;
                buildColorsListFromRGB(red, green, blue);
            } else if (redCounter == 255 && greenCounter == 0 && blueCounter == 0) {
                green++;
                blue++;
                buildColorsListFromRGB(red, green, blue);
            } else if (redCounter == 255 && greenCounter == 255 && blueCounter == 0) {
                blue++;
                buildColorsListFromRGB(red, green, blue);
            }

        }

        return -1;

    }

    private int[] buildRed() {

        printValue(red);

        if (red == 255) {
            return redArray;
        } else {
            red++;
            redArray[red] = red;
            buildRed();
        }

        return redArray;
    }

    private int[] buildGreen() {

        printValue(green);

        if (green == 255) {
            return greenArray;
        } else {
            green++;
            greenArray[green] = green;
            buildGreen();
        }

        return greenArray;
    }

    private int[] buildBlue() {

        printValue(blue);

        if (blue == 255) {
            return blueArray;
        } else {
            blue++;
            blueArray[blue] = blue;
            buildBlue();
        }

        return blueArray;
    }

    private static void printValuesInArray(int[] array) {

        final int arrayLength = array.length;

        for (int i = 0; i <= (arrayLength - 1) + 10; i++) {
            System.out.print("-");
        }

        System.out.println("\n#####################################");

        for (int i = 0; i <= arrayLength - 1; i++) {

            System.out.println("array = [" + i + "]" + " --> " + " [ " + array[i] + " ]");

        }

        System.out.println("\n#####################################");

        for (int i = 0; i <= (arrayLength - 1) + 10; i++) {
            System.out.print("-");
        }

    }

    private void printRGBValuesInArray(List<ColorsGen> array) {

        final int arrayLength = array.size();

        System.out.println("\n#####################################");

        for (int i = 0; i <= arrayLength - 1; i++) {

            System.out.println("array = [" + i + "]" + " --> "
                    + "[R]" + " [ " + array.get(i).getRed() + " ]"
                    + "[G]" + " [ " + array.get(i).getGreen() + " ]"
                    + "[B]" + " [ " + array.get(i).getBlue() + " ]");

        }

        System.out.println("\n#####################################");

    }

    private void printValue(int value) {

        for (int i = 0; i <= 10; i++) {
            System.out.print("-");
        }

        System.out.println("\n");
        System.out.println("array = [" + value + "]" + " --> " + " [ " + value + " ]");

        for (int i = 0; i <= 10; i++) {
            System.out.print("-");
        }

        System.out.println("\n");

    }

}

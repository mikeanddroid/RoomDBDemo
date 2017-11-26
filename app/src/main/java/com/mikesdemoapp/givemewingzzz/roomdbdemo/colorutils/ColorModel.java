package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils
 * Created by GiveMeWingzzz on 11/23/2017.
 */

public class ColorModel {

    public int R;
    public int G;
    public int B;

    public ColorModel(int r, int g, int b) {
        R = r;
        G = g;
        B = b;
    }

    public ColorModel() {
        R = 0;
        G = 0;
        B = 0;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

}

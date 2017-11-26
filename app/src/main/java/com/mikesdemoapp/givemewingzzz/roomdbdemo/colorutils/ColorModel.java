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

    public float _R;
    public float _G;
    public float _B;

    public ColorModel(float _R, float _G, float _B) {
        this._R = _R;
        this._G = _G;
        this._B = _B;
    }

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

    public float get_R() {
        return _R;
    }

    public void set_R(float _R) {
        this._R = _R;
    }

    public float get_G() {
        return _G;
    }

    public void set_G(float _G) {
        this._G = _G;
    }

    public float get_B() {
        return _B;
    }

    public void set_B(float _B) {
        this._B = _B;
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

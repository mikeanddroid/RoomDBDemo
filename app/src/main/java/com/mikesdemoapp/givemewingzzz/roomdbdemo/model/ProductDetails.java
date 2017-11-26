package com.mikesdemoapp.givemewingzzz.roomdbdemo.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils.RoomDBPro;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.model
 * Created by GiveMeWingzzz on 11/18/2017.
 */
@RoomDBPro(isInject = true)
@Entity(tableName = "PRODUCT_DETAILS")
public class ProductDetails {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "price")
    private int price;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}

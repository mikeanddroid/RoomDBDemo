package com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils
 * Created by GiveMeWingzzz on 11/18/2017.
 */

public class DateTypeConverter {

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }

}

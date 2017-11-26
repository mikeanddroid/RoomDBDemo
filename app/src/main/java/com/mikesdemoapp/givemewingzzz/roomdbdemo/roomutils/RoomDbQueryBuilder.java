package com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils
 * Created by GiveMeWingzzz on 11/18/2017.
 */

public class RoomDbQueryBuilder<T> extends AbsRoomDbQueryBuilder {

    AbsRoomDbQueryBuilder<T> roomDbQueryBuilder;

    public static RoomDbQueryBuilder newInstance() {
        return new RoomDbQueryBuilder();
    }

    public RoomDbQueryBuilder<T> buildSelectAll() {
        return this;
    }

    public String buildSelectAllQuery(Class className) {
        return buildSelectAllQuery(className);
    }

}

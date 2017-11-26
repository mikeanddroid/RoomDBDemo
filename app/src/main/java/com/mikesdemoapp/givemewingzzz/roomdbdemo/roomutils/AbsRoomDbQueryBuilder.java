package com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils;

import java.lang.annotation.Annotation;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils
 * Created by GiveMeWingzzz on 11/18/2017.
 */

public class AbsRoomDbQueryBuilder<T> {

    public static String SPACE = RoomDbUtils.Constants.SPACE;

    public static final String SELECT = "SELECT" + SPACE;
    public static final String WHERE = "WHERE" + SPACE;
    public static final String FROM = "FROM" + SPACE;
    public static final String ALL = "*" + SPACE;

    public static String test = "";

    private T roomDBBuilder;

    public AbsRoomDbQueryBuilder(T roomDBBuilder) {
        this.roomDBBuilder = roomDBBuilder;
    }

    public AbsRoomDbQueryBuilder() {

    }

    public T getRoomDBBuilder() {
        return roomDBBuilder;
    }

    private String select() {
        return SELECT;
    }

    private String from() {
        return FROM;
    }

    private String where() {
        return WHERE;
    }

    private boolean isValidClass(Class className) {

        Class clazz = className.getClass();
        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof RoomDBPro) {
                RoomDBPro roomDBPro = (RoomDBPro) annotation;
                return roomDBPro.isInject();
            }
        }

        return false;
    }

    private String getClassName(Class className) {

        String clsName = null;

        if (isValidClass(className)) {
            clsName = className.getClass().getName();
        } else {
            new Exception(clsName + " Not implementing the correct annotation!!!");
        }

        return clsName;
    }

    public String buildSelectAllQuery(Class className) {

        StringBuilder selectAll = new StringBuilder();
        selectAll.append(select());
        selectAll.append(ALL);
        selectAll.append(from());
        selectAll.append(getClassName(className));

        return selectAll.toString();

    }

}

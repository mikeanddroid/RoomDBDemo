package com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils
 * Created by GiveMeWingzzz on 11/18/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RoomDBPro {
    boolean isInject();
}

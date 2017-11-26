package com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.model.Product;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils
 * Created by GiveMeWingzzz on 11/18/2017.
 */
@Database(entities = {Product.class}, version = 2)
@TypeConverters({DateTypeConverter.class})
public abstract class RoomDBBuilder extends RoomDatabase {

    public abstract ProductDao productDao();

    /**
     * When the database version number is changed to 2, executes this migration: add a column named price with INTEGER type in table product
     */
//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE product "
//                    + " ADD COLUMN" + RoomDbUtils.Constants.SPACE + RoomDbUtils.Constants.DBTableConst.PRICE + RoomDbUtils.Constants.SPACE + "INTEGER");
//
//            // enable flag to force update products
//            RoomDbDemoApp.get().setForceUpdate(true);
//        }
//    };

}

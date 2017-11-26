package com.mikesdemoapp.givemewingzzz.roomdbdemo;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.FileLog;
import com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils.RoomDBBuilder;

import java.io.File;
import java.io.IOException;

import static com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils.RoomDbUtils.Constants.DATABASE_NAME;
import static com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils.RoomDbUtils.Constants.KEY_FORCE_UPDATE;
import static com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils.RoomDbUtils.Constants.PREFERENCES;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo
 * Created by GiveMeWingzzz on 11/18/2017.
 */

public class RoomDbDemoApp extends Application {

    public static RoomDbDemoApp INSTANCE;

    private RoomDBBuilder database;

    public static RoomDbDemoApp get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // create database
        database = Room.databaseBuilder(getApplicationContext(), RoomDBBuilder.class, DATABASE_NAME)
//                .addMigrations(RoomDBBuilder.MIGRATION_1_2)
                .build();

        INSTANCE = this;

    }

    public FileLog getFileLog() {
        FileLog fileLog = new FileLog("sdcard/RoomDbDemoLogs_log.txt", Log.VERBOSE, (1000000 * 5));
        fileLog.open();
        return fileLog;
    }

    public RoomDBBuilder getDB() {
        return database;
    }

    public boolean isForceUpdate() {
        return getSP().getBoolean(KEY_FORCE_UPDATE, true);
    }

    public void setForceUpdate(boolean force) {
        SharedPreferences.Editor edit = getSP().edit();
        edit.putBoolean(KEY_FORCE_UPDATE, force);
        edit.apply();
    }

    private SharedPreferences getSP() {
        return getSharedPreferences(PREFERENCES, MODE_PRIVATE);
    }

    private void createLogsDirectory() {

        if (isExternalStorageWritable()) {

            File appDirectory = new File(Environment.getExternalStorageDirectory() + "/RoomDbDemoAppAppFolder");
            File logDirectory = new File(appDirectory + "/log");
            File logFile = new File(logDirectory, "logcat" + System.currentTimeMillis() + ".txt");

            // create app folder
            if (!appDirectory.exists()) {
                appDirectory.mkdir();
            }

            // create log folder
            if (!logDirectory.exists()) {
                logDirectory.mkdir();
            }

            // clear the previous logcat and then write the new one to the file
            try {
                Process process = Runtime.getRuntime().exec("logcat -c");
                process = Runtime.getRuntime().exec("logcat -f " + logFile + " *:S RoomDbDemoApp:D RoomDbDemoApp:D");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (isExternalStorageReadable()) {
            // only readable
        } else {
            // not accessible
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}

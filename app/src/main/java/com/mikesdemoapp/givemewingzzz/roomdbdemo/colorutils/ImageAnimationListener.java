package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.view.animation.Animation;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils
 * Created by GiveMeWingzzz on 11/26/2017.
 */

public interface ImageAnimationListener {

    void onAnimationStart(Animation animation);

    void onAnimationEnd(Animation animation, List<TextView> textViews, List<CircularImageView> imageViews);

    void onAnimationRepeat(Animation animation);

}

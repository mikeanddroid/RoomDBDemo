package com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.RoomDbDemoApp;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils
 * Created by GiveMeWingzzz on 11/25/2017.
 */

public class ColorsUtils {

    private static final String TAG = ColorsUtils.class.getSimpleName();
    FileLog fileLog;

    public ColorsUtils() {
        fileLog = RoomDbDemoApp.get().getFileLog();
    }

    public static int[] getRGB(final int hex) {
        int r = (hex & 0xFF0000) >> 16;
        int g = (hex & 0xFF00) >> 8;
        int b = (hex & 0xFF);
        return new int[]{r, g, b};
    }

    public static int[] getRGB(final String rgb) {
        int r = Integer.parseInt(rgb.substring(0, 2), 16); // 16 for hex
        int g = Integer.parseInt(rgb.substring(2, 4), 16); // 16 for hex
        int b = Integer.parseInt(rgb.substring(4, 6), 16); // 16 for hex
        return new int[]{r, g, b};
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public ObjectAnimator fadeView(View v, float startScale, float endScale) {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", .3f, 1f);
        fadeIn.setDuration(1000);
        return fadeIn;
    }

    public void fadeViews(List<TextView> textViews) {

        for (View view : textViews) {
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", .3f, 1f);
            fadeIn.setDuration(1000);
            fadeIn.start();
        }

    }

    public void scaleViews(List<View> views, float startScale, float endScale) {

        Animation anim = new ScaleAnimation(
                0f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(500);

        for (int i = 0; i < views.size(); i++) {
            views.get(i).startAnimation(anim);
        }

    }

    public void scaleImageViews(List<CircularImageView> views, float startScale, float endScale) {

        Animation anim = new ScaleAnimation(
                0f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(500);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        for (int i = 0; i < views.size(); i++) {

            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(views.get(i), "alpha", .3f, 1f);
            fadeIn.setDuration(1500);

            mAnimationSet.play(fadeIn);

            views.get(i).startAnimation(anim);

            mAnimationSet.start();

        }

    }

    public void scaleTextViews(final List<TextView> views, final float startScale, final float endScale) {

        final Animation anim = new ScaleAnimation(
                0f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling

        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(500);

        for (int i = 0; i < views.size(); i++) {
            views.get(i).startAnimation(anim);
        }

    }

    /*****************************************************************************************/

    public List<Integer> getColorBands(ColorModel colorsGen, int bands) {

        List<Integer> colorBands = new ArrayList<>(bands);
        for (int index = 0; index < bands; index++) {
            colorBands.add(darken(colorsGen, (double) index / (double) bands));
        }

        return colorBands;

    }

    public int darken(ColorModel colorModel, double fraction) {

        int red = (int) Math.round(Math.max(0, colorModel.getR() - 255 * fraction));
        int green = (int) Math.round(Math.max(0, colorModel.getG() - 255 * fraction));
        int blue = (int) Math.round(Math.max(0, colorModel.getB() - 255 * fraction));

//        int alpha = colorModel.getAlpha();

        Color color = new Color();

//        ColorSpace sRgb = ColorSpace.get(ColorSpace.Named.SRGB);

//        // Convert from CIE L*a*b* (color model Lab) to Rec.709 (color model RGB)
//        ColorSpace.Connector connector = ColorSpace.connect(
//                ColorSpace.get(ColorSpace.Named.CIE_LAB),
//                ColorSpace.get(ColorSpace.Named.BT709));

//        color.convert(sRgb);

        return Color.argb(255, red, green, blue);

    }

    /*****************************************************************************************/

    public ColorModel getRGBFromHex(Long colorValue) {

        // edited to support big numbers bigger than 0x80000000
        int color = Integer.valueOf(String.valueOf(colorValue));

        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;

        ColorModel colorModel = new ColorModel(r, g, b);

        fileLog.d(TAG, "colorValue = [ " + colorValue + " ] : " + " R = [ " + colorModel.getR() + " ] " + " G = [ " + colorModel.getG() + " ] " + " B = [ " + colorModel.getB() + " ] ");

        return colorModel;
    }

}

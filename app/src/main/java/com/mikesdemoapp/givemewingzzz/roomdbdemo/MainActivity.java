package com.mikesdemoapp.givemewingzzz.roomdbdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.StackView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.CardAdapter;
import com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.ColorsShadesTask;
import com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.ColorsTask;
import com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.ColorsUtils;
import com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.DialogRecyclerViewAdapter;
import com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.FileLog;
import com.mikesdemoapp.givemewingzzz.roomdbdemo.colorutils.RecyclerViewAdapter;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.view.View.GONE;
import static com.mikesdemoapp.givemewingzzz.roomdbdemo.R.id.textView;

public class MainActivity extends AppCompatActivity implements ColorsTask.ColorsResult, RecyclerViewAdapter.ItemListener, ColorsShadesTask.ColorsShadesResult, DialogRecyclerViewAdapter.DialogItemListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button buildColor;
    RecyclerView colorRecyclerView;

    private Map<Long, Integer> colorsIntMap;

    private ProgressDialog progressDialog;
    private ColorsTask colorsTask;

    private ColorsShadesTask colorsShadesTask;
    private FileLog fileLog;
    private RecyclerViewAdapter colorsAdapter;

    public static final int BLACK_RANGE = -16777216;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fileLog = RoomDbDemoApp.get().getFileLog();

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading colors..");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        buildColor = (Button) findViewById(R.id.buildColorbutton);
        colorRecyclerView = (RecyclerView) findViewById(R.id.colorRecyclerView);

        buildColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ColorsGeneratorTask colorsGeneratorTask = new ColorsGeneratorTask(MainActivity.this);
//                colorsGeneratorTask.execute();
                colorsTask = new ColorsTask(MainActivity.this);
                colorsTask.execute();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPreExecute() {
        progressDialog.show();
    }

    @Override
    public void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);
    }

    @Override
    public void onPostExecute(Map<Long, Integer> colorsIntMap) {

        List<Integer> colorsList = new ArrayList<>(colorsIntMap.values());
        colorsAdapter = new RecyclerViewAdapter(MainActivity.this, colorsList, this);
        colorRecyclerView.setAdapter(colorsAdapter);

        setColorsAdapterLayoutManager();
        progressDialog.dismiss();

    }

    private void setColorsAdapterLayoutManager() {

        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        colorRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onColorIntMapReady(Map<Long, Integer> colorsIntMap) {
        this.colorsIntMap = colorsIntMap;
        Toast.makeText(getApplicationContext(), "Colors Map Ready..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(final int item, int position) {

        Toast.makeText(getApplicationContext(), item + " is clicked in position --> " + position, Toast.LENGTH_SHORT).show();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        final View view = getLayoutInflater().inflate(R.layout.shades_layout, null);
        final View customTitleView = getLayoutInflater().inflate(R.layout.custom_title_view, null);

        alertDialog.setView(view);

        final ImageView mainColorImage = view.findViewById(R.id.imageShadeView);
        final ImageView imageColor2 = view.findViewById(R.id.imageShadeView2);
        final ImageView imageColor4 = view.findViewById(R.id.imageShadeView4);

        final RelativeLayout mainImageCard = view.findViewById(R.id.imageShadeViewCard);
        final CardView mainImageCard2 = view.findViewById(R.id.imageShadeView2Card);
        final CardView mainImageCard4 = view.findViewById(R.id.imageShadeView4Card);

        final TextView errorText1 = view.findViewById(R.id.stackShadesErrorTextView);
        final TextView errorText2 = view.findViewById(R.id.stackShadesError2TextView);
        final TextView colorValueTV = customTitleView.findViewById(R.id.colorValueDialogTextView);

        alertDialog.setCustomTitle(customTitleView);

        final TextView titleView = (TextView) customTitleView.findViewById(R.id.stackShadeTitleTextView);

//        alertDialog.setTitle("Color Details");
//        alertDialog.setMessage("Color : " + item);


        alertDialog.setPositiveButton("Find More Shades", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                colorsShadesTask = new ColorsShadesTask(MainActivity.this, 6);
                colorsShadesTask.execute(Long.valueOf(item));

                dialogInterface.dismiss();

            }
        });

        alertDialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        colorsShadesTask = new ColorsShadesTask(new ColorsShadesTask.ColorsShadesResult() {
            @Override
            public void onColorShadesPreExecute() {

            }

            @Override
            public void onColorShadesProgressUpdate(Integer... values) {

            }

            @Override
            public void onColorShadesPostExecute(List<Integer> colorShadesList) {


                int BLACK_RANGE_TEMP = -16777216;

                int color0 = colorShadesList.get(0);
                int color1 = colorShadesList.get(1);
                int color2 = colorShadesList.get(2);

                titleView.setText("Color Details");
                titleView.setTextColor(colorShadesList.get(2));

                int[] alphaColor = ColorsUtils.getRGB(color1);

                int alphaValColor = Color.argb(255, alphaColor[0], alphaColor[1], alphaColor[2]);

                colorValueTV.setText("COLOR " + item);

                errorText2.bringToFront();
                errorText1.bringToFront();

                errorText2.setTextColor(Color.parseColor("#ffffff"));
                errorText1.setTextColor(Color.parseColor("#ffffff"));

                errorText1.setVisibility(View.VISIBLE);
                errorText2.setVisibility(View.VISIBLE);
                mainImageCard2.bringToFront();
                mainImageCard2.setBackgroundColor(color1);

//                mainImageCard.bringToFront();
                mainImageCard.setBackgroundColor(alphaValColor);
                mainColorImage.setBackgroundColor(color1);

                // Set Left and right images

                if (color1 == BLACK_RANGE_TEMP) {
                    imageColor2.setBackground(getDrawable(R.drawable.rectangle_shape_outline));
                    errorText1.setText("NO\nMORE\nSHADE\nFOUND");
                    errorText1.setVisibility(View.VISIBLE);
                } else {
                    imageColor2.setBackgroundColor(color0);
                }

                if (color2 == BLACK_RANGE_TEMP) {
                    imageColor4.setBackground(getDrawable(R.drawable.rectangle_shape_outline));
                    errorText2.setText("NO\nMORE\nSHADE\nFOUND");
                    errorText2.setVisibility(View.VISIBLE);
                } else {
                    imageColor4.setBackgroundColor(color2);
                    mainImageCard4.setBackgroundColor(color2);
                }
            }
        }, 6);

        colorsShadesTask.execute(Long.valueOf(item));

        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }

    @Override
    public void onColorShadesPreExecute() {
        progressDialog.show();
    }

    @Override
    public void onColorShadesProgressUpdate(Integer... values) {

    }

    public int shouldAnimateCounter = 0;

    @Override
    public void onColorShadesPostExecute(List<Integer> colorShadesList) {

        fileLog.d(TAG, "Colors Shades Size In Main --> " + colorShadesList.size());

        CardAdapter cardAdapter = new CardAdapter(this, colorShadesList);
        DialogRecyclerViewAdapter dialogRecyclerViewAdapter = new DialogRecyclerViewAdapter(this, colorShadesList, this);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.shades_layout_stack_view, null);

        final View customTitleView = getLayoutInflater().inflate(R.layout.custom_title_view, null);
        final TextView colorValueTV = customTitleView.findViewById(R.id.colorValueDialogTextView);

        alertDialog.setView(view);

        int[] rgb = ColorsUtils.getRGB(colorShadesList.get(0));

        colorValueTV.setText("COLOR VALUE  " + colorShadesList.get(0) + " ( " + rgb[0] + ", " + rgb[1] + ", " + rgb[2] + " ) ");
        alertDialog.setCustomTitle(customTitleView);

        final Animation colorAnimation = new ScaleAnimation(
                0f, 1f, // Start and end values for the X axis scaling
                0, 1, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        colorAnimation.setFillAfter(true); // Needed to keep the result of the animation

        final Animation colorValueAnimation = new ScaleAnimation(
                0f, 1f, // Start and end values for the X axis scaling
                0, 1, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        colorValueAnimation.setFillAfter(true); // Needed to keep the result of the animation

        final TextView titleView = (TextView) customTitleView.findViewById(R.id.stackShadeTitleTextView);


        final TextView shadesHeaderView = (TextView) view.findViewById(R.id.shadesHeader);

        titleView.setText("Color Details");
        titleView.setTextColor(colorShadesList.get(2));

        shadesHeaderView.setText("COLOR SHADES - " + colorShadesList.size());
        shadesHeaderView.setTextColor(colorShadesList.get(2));

        // For stack view

        StackView stackView = (StackView) view.findViewById(R.id.shadesStackView);

        stackView.setInAnimation(this, android.R.animator.fade_in);
        stackView.setOutAnimation(this, android.R.animator.fade_out);

//        stackView.onGenericMotionEvent();

//        RecyclerView dialogShadesView = (RecyclerView) view.findViewById(R.id.dialogRecyclerView);

        final TextView colorValue1 = (TextView) view.findViewById(textView);
        final TextView colorValue2 = (TextView) view.findViewById(R.id.textView1);
        final TextView colorValue3 = (TextView) view.findViewById(R.id.textView2);
        final TextView colorValue4 = (TextView) view.findViewById(R.id.textView3);
        final TextView colorValue5 = (TextView) view.findViewById(R.id.textView4);
        final TextView colorValue6 = (TextView) view.findViewById(R.id.textView5);

        final List<TextView> textViews = new ArrayList<>();
        textViews.add(colorValue1);
        textViews.add(colorValue2);
        textViews.add(colorValue3);
        textViews.add(colorValue4);
        textViews.add(colorValue5);
        textViews.add(colorValue6);

        int defColor = colorShadesList.get(3);

        colorValue1.setText("" + colorShadesList.get(0));
        colorValue2.setText("" + colorShadesList.get(1));

        if (colorShadesList.get(2) != null) {
            colorValue3.setText("" + colorShadesList.get(2));
        }

        if (colorShadesList.get(3) != null) {
            colorValue4.setText("" + colorShadesList.get(3));
        }

        if (colorShadesList.get(4) != null) {
            colorValue5.setText("" + colorShadesList.get(4));
        }

        if (colorShadesList.get(5) != null) {
            colorValue6.setText("" + colorShadesList.get(5));
        }

        for (TextView textView : textViews) {
            textView.setTextColor(defColor);
            if (textView.getVisibility() == View.VISIBLE) {
                textView.setVisibility(GONE);
            }

        }

        colorValueAnimation.setDuration(300);
        textViews.get(0).startAnimation(colorValueAnimation);

        CircularImageView imageButton = view.findViewById(R.id.imageButtonMain);
        imageButton.setBackgroundColor(colorShadesList.get(0));
        CircularImageView imageButton1 = view.findViewById(R.id.imageButton1);
        imageButton1.setBackgroundColor(colorShadesList.get(1));
        CircularImageView imageButton2 = view.findViewById(R.id.imageButton2);
        imageButton2.setBackgroundColor(colorShadesList.get(2));
        CircularImageView imageButton3 = view.findViewById(R.id.imageButton3);
        imageButton3.setBackgroundColor(colorShadesList.get(3));
        CircularImageView imageButton4 = view.findViewById(R.id.imageButton4);
        imageButton4.setBackgroundColor(colorShadesList.get(4));
        CircularImageView imageButton5 = view.findViewById(R.id.imageButton5);
        imageButton5.setBackgroundColor(colorShadesList.get(5));

        final List<CircularImageView> imageButtons = new ArrayList<>();
        imageButtons.add(imageButton);
        imageButtons.add(imageButton1);
        imageButtons.add(imageButton2);
        imageButtons.add(imageButton3);
        imageButtons.add(imageButton4);
        imageButtons.add(imageButton5);

        for (CircularImageView circularImageView : imageButtons) {

            if (circularImageView.getVisibility() == View.VISIBLE) {
                circularImageView.setVisibility(GONE);
            }

        }

        // Scale image buttons
//        colorsUtils.scaleImageViews(imageButtons, 0, 1);

        colorAnimation.setDuration(200);
        imageButtons.get(0).startAnimation(colorAnimation); // First Image

        colorAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                for (int i = 0; i < imageButtons.size(); i++) {
                    if (imageButtons.get(i).getVisibility() == GONE) {

                        final Animation colorAnimation = new ScaleAnimation(
                                0f, 1f, // Start and end values for the X axis scaling
                                0, 1, // Start and end values for the Y axis scaling
                                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                        colorAnimation.setFillAfter(true);

                        int factor = (int) Math.round((i + 1) * 1.1);
                        int handlerFactor = (int) Math.round((i + 0.5) * 1.1);
                        int handlerFactorDuration = 200 + (i * (200 / handlerFactor));

                        System.out.println("factor = " + factor);

                        colorAnimation.setDuration(200 + (i * (200 / factor)));

                        final int finalI = i;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                imageButtons.get(finalI).startAnimation(colorAnimation);
                                imageButtons.get(finalI).setVisibility(View.VISIBLE);
                            }
                        }, handlerFactorDuration);
                    }
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        colorValueAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                for (int i = 0; i < textViews.size(); i++) {
                    if (textViews.get(i).getVisibility() == GONE) {

                        final Animation colorValueAnimation = new ScaleAnimation(
                                0f, 1f, // Start and end values for the X axis scaling
                                0, 1, // Start and end values for the Y axis scaling
                                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                        colorValueAnimation.setFillAfter(true);

                        int factor = (int) Math.round((i + 1) * 1.1);
                        int handlerFactor = (int) Math.round((i + 0.5) * 1.1);
                        int handlerFactorDuration = 200 + (i * (200 / handlerFactor));

                        System.out.println("factor = " + factor);

                        colorValueAnimation.setDuration(200 + (i * (200 / factor))); // factor from 0.0 - 0.5

                        final int finalI = i;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textViews.get(finalI).startAnimation(colorValueAnimation);
                                textViews.get(finalI).setVisibility(View.VISIBLE);
                            }
                        }, handlerFactorDuration);

                    }
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        stackView.setAdapter(cardAdapter);

        alertDialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();

        progressDialog.dismiss();
    }

    private static boolean shouldExec = false;

    public class AnimationTask extends AsyncTask<List<CircularImageView>, Integer, Boolean> {

        public AnimationTask(boolean shouldExecute) {
            shouldExec = shouldExecute;
        }

        @Override
        protected Boolean doInBackground(List<CircularImageView>... params) {

            List<CircularImageView> circularImageViews = params[0];

            for (final CircularImageView circularImageView : circularImageViews) {

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (circularImageView.getVisibility() != View.VISIBLE) {

                            Animation anim = new ScaleAnimation(
                                    0f, 1f, // Start and end values for the X axis scaling
                                    0, 1, // Start and end values for the Y axis scaling
                                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                                    Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                            anim.setFillAfter(true); // Needed to keep the result of the animation
                            anim.setDuration(1500);

                            circularImageView.startAnimation(anim);
                            circularImageView.setVisibility(View.VISIBLE);

                            shouldExec = true;

                        } else {
                            shouldExec = false;
                        }

                    }
                });

            }

            return shouldExec;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);

            if (b) {
                new AnimationTask(shouldExec).execute();
            }

        }
    }

}

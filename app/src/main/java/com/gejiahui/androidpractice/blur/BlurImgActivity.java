package com.gejiahui.androidpractice.blur;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/6/7.
 */
public class BlurImgActivity extends AppCompatActivity {
    private static final String TAG = "BlurImgActivity";
    @Bind(R.id.picture)
    ImageView image;
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.time)
    TextView statusText;
    int chosen = R.id.blur;
    float lastY;
    float y;
    float offset;
    String hint = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_img);
        ButterKnife.bind(this);
        applyBlur();

        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                y = event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastY = y;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        offset = y - lastY;
//                        text.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                            @Override
//                            public void onGlobalLayout() {
//                                text.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                                image.buildDrawingCache();
//                                bmp = image.getDrawingCache();
//
//                                if (chosen == R.id.blur) {
//                                    blur(bmp, text);
//                                } else {
//                                    blurscale(bmp, text);
//                                }
//
//                            }
//                        });
                 //       v.layout(v.getLeft(), (int) (v.getTop() + offset), v.getRight(), (int) (v.getBottom() + offset));
                        LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams)v.getLayoutParams();
                        params.topMargin+=offset;
                        v.setLayoutParams(params);
                        bmp = image.getDrawingCache();

                        if (chosen == R.id.blur) {
                            blur(bmp, text);
                        } else {
                            blurscale(bmp, text);
                        }
                        lastY = y;
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }

                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blur_img, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        chosen = id;
        applyBlur();
        //noinspection SimplifiableIfStatement
        if (id == R.id.blur) {
            hint = "";
            return true;
        }
        if (id == R.id.blurscale) {
            hint = "先压缩后模糊";
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    Bitmap bmp ;

    private void applyBlur() {
        image.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                image.getViewTreeObserver().removeOnPreDrawListener(this);
                image.buildDrawingCache();
                bmp = image.getDrawingCache();

                if (chosen == R.id.blur) {
                    blur(bmp, text);
                } else {
                    blurscale(bmp, text);
                }

                return true;
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blur(Bitmap bkg, View view) {

        long startMs = System.currentTimeMillis();

        float radius = 20;

        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth()),
                (int) (view.getMeasuredHeight()), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(overlay);

        canvas.translate(-view.getLeft(), -view.getTop());
        canvas.drawBitmap(bkg, 0, 0, null);
      //  canvas.drawBitmap(bkg, -view.getLeft(), -view.getTop(), null);

        RenderScript rs = RenderScript.create(this);

        Allocation overlayAlloc = Allocation.createFromBitmap(
                rs, overlay);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(
                rs, overlayAlloc.getElement());

        blur.setInput(overlayAlloc);

        blur.setRadius(radius);

        blur.forEach(overlayAlloc);

        overlayAlloc.copyTo(overlay);

        view.setBackground(new BitmapDrawable(getResources(), overlay));

        rs.destroy();
        statusText.setText(System.currentTimeMillis() - startMs + "ms");
    }

    @Override
    public String toString() {
        return "RenderScript";
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blurscale(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();

        float scaleFactor = 8;
        float radius = 2;

        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(overlay);

        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);

        RenderScript rs = RenderScript.create(this);

        Allocation overlayAlloc = Allocation.createFromBitmap(
                rs, overlay);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(
                rs, overlayAlloc.getElement());

        blur.setInput(overlayAlloc);

        blur.setRadius(radius);

        blur.forEach(overlayAlloc);

        overlayAlloc.copyTo(overlay);

        view.setBackground(new BitmapDrawable(getResources(), overlay));

        rs.destroy();
        statusText.setText(hint+""+(System.currentTimeMillis() - startMs) + "ms");
    }


}






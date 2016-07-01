package com.gejiahui.androidpractice.customview.backgroundviewpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.gejiahui.androidpractice.R;

/**
 * Created by gejiahui on 2016/6/29.
 */
public class BackGroundViewPage extends ViewPager {

    private Bitmap mBackground = BitmapFactory.decodeResource(getResources(), R.drawable.e);

    public BackGroundViewPage(Context context) {
        super(context);
    }

    public BackGroundViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {

        if(mBackground != null){
            int width = mBackground.getWidth();
            int height = mBackground.getHeight();
            int count = getAdapter().getCount();
            int x = getScrollX();
            float itemWidth = width *1.0f/ count;
            float widthForPix = itemWidth *1.0f /getWidth();
            Rect scr = new Rect((int)(x *widthForPix),0,(int)(x *widthForPix +itemWidth ),height);

            Rect dest = new Rect((int)getScrollX(),0,getScrollX()+getWidth(),getHeight());

            canvas.drawBitmap(mBackground,scr,dest,null);
          //  canvas.drawBitmap(mBackground,0,0,null);
        }

        super.dispatchDraw(canvas);
    }
}

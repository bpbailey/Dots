package edu.illinois.cs498.dots;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Administrator on 2/12/2016.
 */
public class DotsView extends View  {

    private Paint  mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;


    public DotsView(Context context) {
        super(context);
        initDotsView();
    }

    public DotsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDotsView();
    }

    public DotsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDotsView();
    }

    public DotsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initDotsView();
    }

    private void initDotsView() {
        mPaint = new Paint();
     }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }


}

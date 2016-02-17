package edu.illinois.cs498.dots;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Administrator on 2/12/2016.
 */
public class DotsView extends View implements View.OnTouchListener {

    private Paint  mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private int dotRadius;


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
        dotRadius = 10;
        setOnTouchListener(this);
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

    public boolean onTouch(View v, MotionEvent event) {
        // Log.d("DEBUG", "Receiving touch event");
        int action = event.getActionMasked();
        int index = event.getActionIndex();
        float x = event.getX(index);
        float y = event.getY(index);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                mCanvas.drawCircle(x, y, dotRadius, mPaint);
                invalidate();
                break;
        }
        return true;
    }
}

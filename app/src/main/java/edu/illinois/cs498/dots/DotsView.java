package edu.illinois.cs498.dots;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.HashMap;


/**
 * Created by Administrator on 2/12/2016.
 */
public class DotsView extends View implements View.OnTouchListener {
    public static int SMALL_RADIUS = 10;
    public static int MEDIUM_RADIUS = 20;
    public static int LARGE_RADIUS = 40;
    public static int AREA_RADIUS = 0;

    private Paint  mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private int dotRadius;
    private HashMap pointerMap;


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
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        dotRadius = SMALL_RADIUS;
        pointerMap = new HashMap();
        setOnTouchListener(this);
     }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    public void setDotRadius(int r) {
        dotRadius = r;
    }

    public void setColor(int c) {
        mPaint.setColor(c);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    public boolean onTouch(View v, MotionEvent event) {
        // Log.d("DEBUG", "Receiving touch event");
        int action = event.getActionMasked();
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        float x = event.getX(index);
        float y = event.getY(index);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                Point p = new Point((int)x, (int)y);
                pointerMap.put(id, p);
                break;

            case MotionEvent.ACTION_MOVE:
                for (int i=0; i<event.getPointerCount(); ++i) {
                    id = event.getPointerId(i);
                    x = event.getX(i);
                    y = event.getY(i);
                    Point last = (Point) pointerMap.get(id);
                    if (last != null) {
                        if (dotRadius == AREA_RADIUS) {
                            mPaint.setStrokeWidth((float) event.getSize(i) * 1000);
                        } else {
                            mPaint.setStrokeWidth((float) dotRadius);
                        }
                        mCanvas.drawLine(last.x, last.y, x, y, mPaint);
                    }
                    pointerMap.put(id, new Point((int) x, (int) y));
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                pointerMap.remove(id);
                break;

        }
        return true;
    }
}

package edu.illinois.cs498.dots;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;
import java.util.Calendar;


/**
 * Created by Brian on 3/13/2016.
 */
public class ShakeGestureTracker implements SensorEventListener {
    // This is the min acceleration force to consider
    private static double SHAKE_MIN_THRESHOLD = 14.0;

    // Number of "shakes" that must be detected before recognizing a shake gesture
    private static int SHAKE_NUMBER_OF_STATES = 8;

    // The time window in which the "shakes" must occur
    private static int SHAKE_MIN_TIME = 1000;

    private Context mContext;
    private SensorManager mSensorManager;
    private Sensor mAccelSensor;
    private int state;
    private long startTime;
    private DotsView dotsView;


    public ShakeGestureTracker(Context context, DotsView view) {
        mContext = context;
        dotsView = view;
        mSensorManager = null;
        mAccelSensor = null;

        // Get reference to the accelerometer
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager != null) {
            mAccelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            registerListeners();
        }
        state = 0;
        startTime = 0;
    }

    protected void registerListeners() {
        if (mSensorManager != null) {
            mSensorManager.registerListener(this, mAccelSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    protected void unRegisterListeners() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    public void onSensorChanged(SensorEvent event) {
        trackShakeGesture(event);
    }

    // Implement the state machine shown in the lecture notes
    private void trackShakeGesture(SensorEvent event) {
        double length = getVectorLength(event.values);
        if (state == 0) {
            // is there enough acceleration force to count
            if (length > SHAKE_MIN_THRESHOLD) {
                ++state;
                startTime = Calendar.getInstance().getTimeInMillis();
            } else {
                state = 0;
            }

          // there was enough motion to detect a shake gesture
        } else if (state >= SHAKE_NUMBER_OF_STATES) {
            onShakeDetected();
            state = 0;

          // continue to track length of motion
        } else if (state > 0) {
            if ((Calendar.getInstance().getTimeInMillis() - startTime) > SHAKE_MIN_TIME) {
                // it has taken too long, reset
                state = 0;
            } else if (length > SHAKE_MIN_THRESHOLD) {
                // otherwise, check if sufficient force was present to advance
                ++state;
            }
        }
    }


    private void onShakeDetected() {
        dotsView.eraseCanvas();
        Toast.makeText(mContext, "Shake Gesture Detected", Toast.LENGTH_SHORT).show();
    }

    private double getVectorLength(float [] vector) {
        return Math.sqrt(vector[0]*vector[0] + vector[1]*vector[1] + vector[2]*vector[2]);
    }


    // Must implement for interface but not needed
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

}

package edu.illinois.cs498.dots;

/**
 * Created by Brian on 4/4/2016.
 */

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;



public class OrientationTracker implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelSensor;
    private int yaw, pitch, roll;


    public OrientationTracker(Context context) {
        mSensorManager = null;
        mAccelSensor = null;
        yaw = 0;
        pitch = 0;
        roll = 0;

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager != null) {
            mAccelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            registerListeners();
        }
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
        // Implement yaw, pitch, roll equations given in lecture
        double Ax = event.values[0];
        double Ay = event.values[1];
        double Az = event.values[2];
        double Ax2 = Ax * Ax;
        double Ay2 = Ay * Ay;
        double Az2 = Az * Az;

        // Implement equations in the course lecture notes
        pitch = Math.round((int)Math.toDegrees(Math.atan2(Ay, Math.sqrt(Ax2 + Az2))));

        // Swap the sign to match Android framework. Only matters for interpretation.
        roll = -Math.round((int)Math.toDegrees(Math.atan2(Ax, Az)));

        /* Only use this yaw calculation if Az is close to zero. Otherwise, use Android's
        getRotationVector() followed by getOrientation(). These calls use the
        accelerometer and magnetometer to correctly measure yaw. The calculation is
        accurate but computationally more expensive.
         */
        yaw = Math.round((int)Math.toDegrees(Math.atan2(Ay, Ax)));


        // print(null);
    }

    public int getYaw() {
        return yaw;
    }

    public int getPitch() {
        return pitch;
    }

    public int getRoll() {
        return roll;
    }


    public void print(TextView view){
        String s = "YAW: " + Integer.toString(yaw) + " PITCH: " + Integer.toString(pitch) + " ROLL: " + Integer.toString(roll);
        if (view != null) {
            view.setText(s);
        } else {
            System.out.println(s);
        }
    }


    // Must implement for interface but not needed
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

}

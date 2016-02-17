package edu.illinois.cs498.dots;


import android.app.Activity;
import android.graphics.Color;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WidthSelectionMenu wMenu = new WidthSelectionMenu(this);
        ColorSelectionMenu cMenu = new ColorSelectionMenu(this);
    }
}

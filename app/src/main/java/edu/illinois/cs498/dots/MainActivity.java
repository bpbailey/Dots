package edu.illinois.cs498.dots;


import android.app.Activity;
import android.graphics.Color;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WidthSelectionMenu wMenu = new WidthSelectionMenu(this);
        ColorSelectionMenu cMenu = new ColorSelectionMenu(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.dots_toolbar);
        setActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.erase_the_view) {
            //handle menu item
            DotsView dots = (DotsView) findViewById(R.id.dots_view);
            if (dots != null) {
                dots.eraseCanvas();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}


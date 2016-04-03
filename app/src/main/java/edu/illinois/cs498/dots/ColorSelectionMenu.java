package edu.illinois.cs498.dots;

import android.app.Activity;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.view.GestureDetector;


public class ColorSelectionMenu extends GestureDetector.SimpleOnGestureListener
        implements View.OnTouchListener, View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    private Activity activity;
    private Button cSelectionButton;
    private PopupMenu cSelectionMenu;
    private GestureDetector mDetector;
    private int currentColorIndex;
    private static String[] colors = {"BLACK", "RED", "GREEN", "BLUE"};


    public ColorSelectionMenu(Activity activity) {
        this.activity = activity;
        cSelectionButton = (Button) activity.findViewById(R.id.color_selection_button);
        //cSelectionButton.setOnClickListener(this);
        cSelectionButton.setOnTouchListener(this);
        mDetector = new GestureDetector(activity, this);
        currentColorIndex = 0;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        // check that it is mostly horizontal movement
        if (Math.abs(velocityX) >= Math.abs(velocityY)) {
            // check left or right direction
            if (event1.getX() > event2.getX()) {
                currentColorIndex++;
                if (currentColorIndex >= colors.length) {
                    currentColorIndex = 0;
                }
            } else {
                currentColorIndex--;
                if (currentColorIndex < 0) {
                    currentColorIndex = colors.length - 1;
                }
            }
            DotsView dots = (DotsView) activity.findViewById(R.id.dots_view);
            dots.setColor(colors[currentColorIndex]);
            cSelectionButton.setText(colors[currentColorIndex]);
        }
        return true;
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.color_selection_button:
                cSelectionMenu.show();
                break;
        }
    }

    public boolean onMenuItemClick(MenuItem item) {
        item.setChecked(true);
        DotsView dots = (DotsView) activity.findViewById(R.id.dots_view);
        switch (item.getItemId()) {
            case R.id.black_menu_item:
                dots.setColor(Color.BLACK);
                cSelectionButton.setText("BLACK");
                break;
            case R.id.red_menu_item:
                dots.setColor(Color.RED);
                cSelectionButton.setText("RED");
                break;
            case R.id.green_menu_item:
                dots.setColor(Color.GREEN);
                cSelectionButton.setText("GREEN");
                break;
            case R.id.blue_menu_item:
                dots.setColor(Color.BLUE);
                cSelectionButton.setText("BLUE");
                break;
            default:
                break;
        }
        return true;
    }
}

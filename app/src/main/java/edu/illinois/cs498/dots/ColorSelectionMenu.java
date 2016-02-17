package edu.illinois.cs498.dots;

import android.app.Activity;
import android.graphics.Color;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

/**
 * Created by Administrator on 2/16/2016.
 */
public class ColorSelectionMenu implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    private Activity activity;
    private Button cSelectionButton;
    private PopupMenu cSelectionMenu;

    public ColorSelectionMenu(Activity activity) {
        this.activity = activity;
        cSelectionButton = (Button) activity.findViewById(R.id.color_selection_button);
        cSelectionButton.setOnClickListener(this);

        cSelectionMenu = new PopupMenu(activity, cSelectionButton);
        MenuInflater inflater = cSelectionMenu.getMenuInflater();
        inflater.inflate(R.menu.color_selection_menu, cSelectionMenu.getMenu());
        cSelectionMenu.setOnMenuItemClickListener(this);

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
        }
        return true;
    }
}

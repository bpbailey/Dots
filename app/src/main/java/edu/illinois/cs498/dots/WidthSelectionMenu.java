package edu.illinois.cs498.dots;

import android.app.Activity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

/**
 * Created by Administrator on 2/16/2016.
 */
public class WidthSelectionMenu implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private Activity activity;
    private Button wSelectionButton;
    private PopupMenu wSelectionMenu;

    public WidthSelectionMenu(Activity activity) {
        this.activity = activity;
        wSelectionButton = (Button) activity.findViewById(R.id.width_selection_button);
        wSelectionButton.setOnClickListener(this);

        wSelectionMenu = new PopupMenu(activity, wSelectionButton);
        MenuInflater inflater = wSelectionMenu.getMenuInflater();
        inflater.inflate(R.menu.width_selection_menu, wSelectionMenu.getMenu());
        wSelectionMenu.setOnMenuItemClickListener(this);

    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.width_selection_button:
                wSelectionMenu.show();
                break;
        }
    }

    public boolean onMenuItemClick(MenuItem item) {
        item.setChecked(true);
        DotsView dots = (DotsView) activity.findViewById(R.id.dots_view);
        switch (item.getItemId()) {
            case R.id.small_menu_item:
                dots.setDotRadius(DotsView.SMALL_RADIUS);
                wSelectionButton.setText("SMALL");
                break;
            case R.id.medium_menu_item:
                dots.setDotRadius(DotsView.MEDIUM_RADIUS);
                wSelectionButton.setText("MEDIUM");
                break;
            case R.id.large_menu_item:
                dots.setDotRadius(DotsView.LARGE_RADIUS);
                wSelectionButton.setText("LARGE");
                break;
            case R.id.area_menu_item:
                dots.setDotRadius(DotsView.AREA_RADIUS);
                wSelectionButton.setText("AREA");
                break;
            default:
                break;
        }
        return true;
    }
}

package edu.illinois.cs498.dots;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toolbar;


public class MainActivity extends Activity implements ListView.OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WidthSelectionMenu wMenu = new WidthSelectionMenu(this);
        ColorSelectionMenu cMenu = new ColorSelectionMenu(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.dots_toolbar);
        setActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mDrawerLayout.openDrawer(mDrawerListView);
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.drawer_list_view);
        mDrawerListView.setOnItemClickListener(this);

        String items[] = new String[] { "New Canvas","Recent","Settings"};
        ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.nav_drawer_list_item, items);
        mDrawerListView.setAdapter(listAdapter);
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


    public void onItemClick(AdapterView parent, View view, int position, long id) {
        ListView v = (ListView) parent;
        mDrawerLayout.closeDrawers();
    }
}


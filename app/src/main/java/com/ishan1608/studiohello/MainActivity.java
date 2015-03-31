package com.ishan1608.studiohello;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;


public class MainActivity extends Activity {
    DrawerLayout mainDrawerLayout;
    LinearLayout mainContainer;
    FrameLayout leftDrawer;
    LinearLayout rightDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Handle for all four layouts
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mainContainer = (LinearLayout) findViewById(R.id.main_container);
        leftDrawer = (FrameLayout) findViewById(R.id.left_drawer);
        rightDrawer = (LinearLayout) findViewById(R.id.right_drawer);

        // Setting the tags for the drawers
        leftDrawer.setTag("left");
        rightDrawer.setTag("right");

        // Handling on slide
        mainDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float offset) {
                if (drawerView.getTag().toString().equalsIgnoreCase("left")) {
                    mainContainer.setTranslationX(offset*drawerView.getWidth());
                } else {
                    mainContainer.setTranslationX(-offset*drawerView.getWidth());
                }
            }

            @Override
            public void onDrawerOpened(View view) {

            }

            @Override
            public void onDrawerClosed(View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        // Populating the ListView
        ListView itemsListView = (ListView) findViewById(R.id.items_list_view);
        itemsListView.setAdapter(new ItemsAdapter(this));

        // Setting toggler on the right drawer's contents
        View headingView = findViewById(R.id.headingContainer);
        headingView.setOnClickListener(new contentToggler());
    }
}

class contentToggler implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        ViewGroup parent = (ViewGroup) v.getParent();
        View childView = parent.getChildAt(parent.indexOfChild(v) + 1);
        this.viewToggler(childView);
    }

    private void viewToggler(View view) {
        if(view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }
}
package com.ishan1608.studiohello;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class MainActivity extends Activity {
    DrawerLayout mainDrawerLayout;
    FrameLayout mainContainer;
    FrameLayout leftDrawer;
    LinearLayout rightDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Handle for all four layouts
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mainContainer = (FrameLayout) findViewById(R.id.main_container);
        leftDrawer = (FrameLayout) findViewById(R.id.left_drawer);
        rightDrawer = (LinearLayout) findViewById(R.id.right_drawer);

        // Handling on slide
        mainDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float offset) {
                mainContainer.setTranslationX(offset*drawerView.getWidth());
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

    }
}

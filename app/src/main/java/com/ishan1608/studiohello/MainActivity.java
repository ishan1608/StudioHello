package com.ishan1608.studiohello;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;


public class MainActivity extends Activity {
    DrawerLayout mainDrawerLayout;
    LinearLayout mainContainer;
    LinearLayout leftDrawer;
    LinearLayout rightDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Handle for all four layouts
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mainContainer = (LinearLayout) findViewById(R.id.main_container);
        leftDrawer = (LinearLayout) findViewById(R.id.left_drawer);
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

        // Getting a handle on the number picker on the left drawer
        NumberPicker passengersCountPicker = (NumberPicker) findViewById(R.id.passengers_count_picker);
        // Setting the maximum and minimum values
        passengersCountPicker.setMaxValue(9);
        passengersCountPicker.setMinValue(0);
        // Setting current Value
        passengersCountPicker.setValue(5);
        // Setting the OnValueChangeListener
        passengersCountPicker.setOnValueChangedListener(new passengerValueListener(this));

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

/*
 * Passenger value change listener
 * Does whatever is required to be done upon the change of value
 */
class passengerValueListener implements NumberPicker.OnValueChangeListener {
    Activity activity;
    TextView passengersCountDisplay;
    public passengerValueListener(Activity activity) {
        super();
        this.activity = activity;
        passengersCountDisplay = (TextView) this.activity.findViewById(R.id.passengers_count_display);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
        CharSequence previousText = passengersCountDisplay.getText();
        // Making sure the text doesn't exceeds reasonable length
        if(previousText.length() >= 40) {
            passengersCountDisplay.setText(previousText.subSequence(3, previousText.length()));
        }
        // Appending the current value
        passengersCountDisplay.append("--"+newVal);
    }
}
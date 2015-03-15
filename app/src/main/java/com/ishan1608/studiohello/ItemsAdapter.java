package com.ishan1608.studiohello;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemsAdapter extends BaseAdapter{
    // Headings for list
    String[] itemHeading;
    // Description for list
    String[] itemDescription;
    // List of image files (Resource ID) for list
    TypedArray item_images;
    // Activity on which the list is displayed
    Activity activity;
    public ItemsAdapter(Activity activity) {
        // Storing the activity reference
        this.activity = activity;
        // Storing the reference to the array or resource IDs of list images
        item_images = activity.getResources().obtainTypedArray(R.array.item_images);
        // Storing the reference to the string array of headings
        itemHeading = activity.getResources().getStringArray(R.array.item_headings);
        // Storing the reference to the string array of descriptions
        itemDescription = activity.getResources().getStringArray(R.array.item_descriptions);

    }

    @Override
    public int getCount() {
        return itemHeading.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Creating new list view only when required
        if(convertView == null) {
            // Inflating the layout for single list item
            convertView = activity.getLayoutInflater().inflate(R.layout.item_single, null);
            // Getting a handle for the heading TextView
            TextView itemHeadingTextView = (TextView) convertView.findViewById(R.id.item_heading);
            // Setting the text of heading
            itemHeadingTextView.setText(itemHeading[position]);
            // Getting a handle for the description TextView
            TextView itemDescriptionTextView = (TextView) convertView.findViewById(R.id.item_description);
            // Setting the text of description
            itemDescriptionTextView.setText(itemDescription[position]);
            // Getting a handle for the icon ImageView
            ImageView itemImageView = (ImageView) convertView.findViewById(R.id.item_image);
            // Setting the image from item_images (Resource ID array), defaulting to aeroplane icon
            itemImageView.setImageResource(item_images.getResourceId(position, R.drawable.aeroplane));
        }
        return convertView;
    }
}

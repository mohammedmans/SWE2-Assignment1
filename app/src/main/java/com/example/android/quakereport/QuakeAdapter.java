package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by Mohammed on 1/30/2018.
 */

public class QuakeAdapter extends ArrayAdapter<Quake> {


    public QuakeAdapter(@NonNull Context context, ArrayList<Quake> quakes) {
        super(context, 0, quakes);
    }

    String locOffset;
    String priLoc;


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Quake currentQuake = getItem(position);

        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);
        double m = currentQuake.getMag();
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        String newMag = magnitudeFormat.format(m);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        magTextView.setText(newMag);

        TextView locOffsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView priLocTextView = (TextView) listItemView.findViewById(R.id.primary_location);

        String s = currentQuake.getLocation();
        if (s.contains("of")) {
            String[] parts = s.split("of");
            locOffset = parts[0] + "of";
            priLoc = parts[1];
        } else {
            locOffset = getContext().getString(R.string.near_the);
            priLoc = s;
        }


        locOffsetTextView.setText(locOffset);
        priLocTextView.setText(priLoc);


        Date dateObject = new Date(currentQuake.getTime());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy "); // best to make a separate fn that return string
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss ");
        String dateToDisplay = dateFormatter.format(dateObject);
        String timeToDisplay = timeFormatter.format(dateObject);

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.timme);


        dateTextView.setText(dateToDisplay);
        timeTextView.setText(timeToDisplay);


        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}

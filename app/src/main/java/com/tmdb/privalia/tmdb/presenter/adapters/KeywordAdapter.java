package com.tmdb.privalia.tmdb.presenter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tmdb.privalia.tmdb.R;

import java.util.List;

/**
 * Created by fernando on 2/12/17.
 */

public class KeywordAdapter extends ArrayAdapter<String> {

    public KeywordAdapter(Context context, List<String> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String keyword = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.keyword_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.text_keyword);
        // Populate the data into the template view using the data object
        tvName.setText(keyword);
        // Return the completed view to render on screen
        return convertView;
    }

}

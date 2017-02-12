package com.tmdb.privalia.tmdb.presenter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.interactor.KeywordsInteractor;
import com.tmdb.privalia.tmdb.interactor.model.Keyword;

import java.util.List;

/**
 * Created by fernando on 2/12/17.
 */

public class KeywordsAdapter extends ArrayAdapter<Keyword> implements Filterable {

    private KeywordsInteractor keywordsInteractor;

    public KeywordsAdapter(Context _context, List<Keyword> _objects) {
        super(_context, 0, _objects);
        keywordsInteractor = new KeywordsInteractor();
    }




    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Keyword keyword = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.keyword_item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.text_keyword);
        tvName.setText(keyword.getName());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    keywordsInteractor.cancelSearch();
                    List<Keyword> keywords = keywordsInteractor.getKeyworks(constraint.toString()).getResults();

                    // Assign the data to the FilterResults
                    filterResults.values = keywords;
                    filterResults.count = keywords.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    clear();
                    addAll((List<Keyword>) results.values);
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;
    }
}

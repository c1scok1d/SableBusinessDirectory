package com.macinternetservices.sablebusinessdirectory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<SearchListItems> SearchListItemsList;
    private ArrayList<SearchListItems> arraylist;

    public SearchListViewAdapter(Context context, List<SearchListItems> SearchListItemsList) {
        mContext = context;
        this.SearchListItemsList = SearchListItemsList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(SearchListItemsList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return SearchListItemsList.size();
    }

    @Override
    public SearchListItems getItem(int position) {
        return SearchListItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.searchlist_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name =  view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(SearchListItemsList.get(position).getSearchListItem());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        SearchListItemsList.clear();
        if (charText.length() == 0) {
            SearchListItemsList.addAll(arraylist);
        } else {
            for (SearchListItems wp : arraylist) {
                if (wp.getSearchListItem().toLowerCase(Locale.getDefault()).contains(charText)) {
                    SearchListItemsList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}

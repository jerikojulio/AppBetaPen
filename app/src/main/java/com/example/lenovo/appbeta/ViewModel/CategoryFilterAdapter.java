package com.example.lenovo.appbeta.ViewModel;

import android.app.Activity;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lenovo.appbeta.R;

import java.util.ArrayList;

/**
 * Created by jeriko on 8/14/17.
 */

public class CategoryFilterAdapter extends ArrayAdapter<String>{

    Activity context;
    ArrayList<String> listName;
    LayoutInflater inflater;

    public CategoryFilterAdapter(Activity context, ArrayList<String> listName)
    {
        super(context, R.layout.content_list_view,listName);
        this.context=context;
        this.listName=listName;
    }

    //1
    @Override
    public int getCount() {
        return listName.size();
    }

    //2
    /*
    @Override
    public Object getItem(int position) {
        return listName.get(position);
    }
    */

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = context.getLayoutInflater();

        // Get view for row item
        View rowView =  inflater.inflate(R.layout.content_list_view, null, true);

        TextView subtitleView = (TextView) rowView.findViewById(R.id.textView01);

        if (Build.VERSION.SDK_INT >= 24) {
            subtitleView.setText(Html.fromHtml(listName.get(position),-1)); // for 24 api and more
        } else {
            subtitleView.setText(Html.fromHtml(listName.get(position))); // for older api
        }

       //subtitleView.setText(listName.get(position));

        return rowView;
    }

}
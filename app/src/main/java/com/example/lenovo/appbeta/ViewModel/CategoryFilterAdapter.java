package com.example.lenovo.appbeta.ViewModel;

import android.app.Activity;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.appbeta.Models.FirebaseModel;
import com.example.lenovo.appbeta.R;

import java.util.ArrayList;

/**
 * Created by jeriko on 8/14/17.
 */

public class CategoryFilterAdapter extends ArrayAdapter<String>{

    Activity context;
    ArrayList<FirebaseModel> model;
    LayoutInflater inflater;

    public CategoryFilterAdapter(Activity context, ArrayList<FirebaseModel> model)
    {
        super(context, R.layout.content_list_view);
        this.context = context;
        this.model = model;
    }

    //1
    @Override
    public int getCount() {
        return model.size();
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
        View rowView;


        // Get view for row item
        if (position == 0){
            rowView = inflater.inflate(R.layout.content_list_view02, null, true);
        } else {
            rowView =  inflater.inflate(R.layout.content_list_view, null, true);
        }


        TextView subtitleView = (TextView) rowView.findViewById(R.id.textView01);
        ImageView imageView =(ImageView) rowView.findViewById(R.id.ImageView01);

        if (Build.VERSION.SDK_INT >= 24) {
            subtitleView.setText(Html.fromHtml(model.get(position).parsedTitle,-1)); // for 24 api and more
        } else {
            subtitleView.setText(Html.fromHtml(model.get(position).parsedTitle)); // for older api
        }

        Glide
                .with(context)
                .load(model.get(position).parsedImageUrl)
                .centerCrop()
                .crossFade()
                .into(imageView);

        return rowView;
    }

}

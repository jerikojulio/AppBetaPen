package com.example.lenovo.appbeta.ViewModel;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.appbeta.R;

/**
 * Created by LENOVO on 8/30/2016.
 */
public class CustomAdapterDrawer extends ArrayAdapter<String>
{
    Activity navcontext;
    String[] navlistName;
    TypedArray navlistImage;



    public CustomAdapterDrawer(Activity navcontext, String[] navlistName,TypedArray navlistImage)
    {
        super(navcontext, R.layout.content_drawer,navlistName);
        this.navcontext=navcontext;
        this.navlistName=navlistName;
        this.navlistImage=navlistImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView;
        LayoutInflater inflater;


        inflater = navcontext.getLayoutInflater();
        rowView = inflater.inflate(R.layout.content_drawer, null,true);

        TextView textTitle = (TextView) rowView.findViewById(R.id.drawertextView);
        ImageView imageTitle = (ImageView) rowView.findViewById(R.id.drawerimageView);

        textTitle.setText(navlistName[position]);
        imageTitle.setImageResource(navlistImage.getResourceId(position,-1));

        return rowView;
    }
}
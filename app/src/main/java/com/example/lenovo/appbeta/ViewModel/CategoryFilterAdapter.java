package com.example.lenovo.appbeta.ViewModel;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.example.lenovo.appbeta.R;

import java.util.ArrayList;

/**
 * Created by jeriko on 8/14/17.
 */

public class CategoryFilterAdapter extends ArrayAdapter<String>{

    Activity context;
    ArrayList<String> listName;
    Integer[] listImage;

    public CategoryFilterAdapter(Activity context, ArrayList<String> listName, Integer[] listImage)
    {
        super(context, R.layout.content_list_view,listName);
        this.context=context;
        this.listName=listName;
        this.listImage=listImage;
    }
}

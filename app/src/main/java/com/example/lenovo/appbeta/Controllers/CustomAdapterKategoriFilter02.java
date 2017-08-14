package com.example.lenovo.appbeta.Controllers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.appbeta.R;

import java.util.ArrayList;

/**
 * Created by LENOVO on 9/4/2016.
 */
public class CustomAdapterKategoriFilter02 extends ArrayAdapter<String>
{
    Activity context;
    ArrayList<String> listName;
    Integer[] listImage;
    Integer[] listImage2;
    private static final int layout_type1=0;
    private static final int layout_type2=1;

    public CustomAdapterKategoriFilter02(Activity context, ArrayList<String> listName, Integer[] listImage, Integer[] listImage2)
    {
        super(context, R.layout.content_list_view,listName);
        this.context=context;
        this.listName=listName;
        this.listImage=listImage;
        this.listImage2=listImage2;
    }


    public int getCount() {
        return listName.size();
    }

        /*
        public Object getItem(int arg0) {
            return null;
        }
        */

    public long getItemId(int position) {
        return position;
    }

    int layout_type;
    @Override
    public int getItemViewType (int position)
    {
        if (position==0)
        {
            layout_type=layout_type1;
        }
        else
        {
            layout_type=layout_type2;
        }

        return layout_type;
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageTitle;
        View rowView = convertView;
        LayoutInflater inflater;
        int layout_type = getItemViewType(position);

        ViewHolderItems viewHolder;
        if (rowView == null) {

            if (layout_type == layout_type1) {
                inflater = context.getLayoutInflater();
                rowView = inflater.inflate(R.layout.content_list_view02, null, true);

                viewHolder = new ViewHolderItems();
                viewHolder.mStoreName =(TextView) rowView.findViewById(R.id.textView01);
                viewHolder.mStoreImage =(ImageView) rowView.findViewById(R.id.ImageView01);
                //textTitle = (TextView) rowView.findViewById(R.id.textView01);
                imageTitle = (ImageView) rowView.findViewById(R.id.ImageView01);

                rowView.setTag(viewHolder);

                //rowView.setTag(R.id.textView01, textTitle);
                //rowView.setTag(R.id.ImageView01, imageTitle);

            } else {
                inflater = context.getLayoutInflater();
                rowView = inflater.inflate(R.layout.content_list_view, null, true);

                viewHolder = new ViewHolderItems();
                viewHolder.mStoreName =(TextView) rowView.findViewById(R.id.textView01);
                viewHolder.mStoreImage =(ImageView) rowView.findViewById(R.id.ImageView01);

                //textTitle = (TextView) rowView.findViewById(R.id.textView01);
                imageTitle = (ImageView) rowView.findViewById(R.id.ImageView01);

                //rowView.setTag(R.id.textView01, textTitle);

                //rowView.setTag(R.id.ImageView01, imageTitle);

                rowView.setTag(viewHolder);

            }
        }else {

            viewHolder = (ViewHolderItems) rowView.getTag();
            imageTitle = (ImageView) rowView.findViewById(R.id.ImageView01);

            DecodeTask dt1 = (DecodeTask)imageTitle.getTag(R.id.ImageView01);
            if(dt1 != null)
                dt1.cancel(true);
        }

        //if (layout_type == layout_type1) {
        //    textTitle.setText(listName.get(position));
        //    imageTitle.setImageResource(listImage2[position]);
        //} else {
        //    textTitle.setText(listName.get(position));
        //   imageTitle.setImageResource(listImage[position]);
        //}

        //textTitle.setText(listName.get(position));
        //imageTitle.setImageResource(listImage[position]);

        viewHolder.mStoreName.setText(listName.get(position));

        //viewHolder.mStoreImage.setImageResource(listImage[position]);

        //imageTitle.setTag(listImage[position]);

        //new AsyncImageSetter().execute();

        imageTitle.setImageBitmap(null);
        DecodeTask dt2 = new DecodeTask(getContext(), imageTitle, listImage[position]);
        dt2.execute();
        imageTitle.setTag(R.id.ImageView01, dt2);

        return rowView;
    }


    static class ViewHolderItems
    {
        public ImageView mStoreImage;
        public TextView mStoreName;
    }
}
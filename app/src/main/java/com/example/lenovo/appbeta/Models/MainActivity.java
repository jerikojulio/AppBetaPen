package com.example.lenovo.appbeta.Models;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.appbeta.Controllers.Article;
import com.example.lenovo.appbeta.Controllers.CustomAdapterDrawer;
import com.example.lenovo.appbeta.Controllers.CustomAdapterKategoriFilter02;
import com.example.lenovo.appbeta.Controllers.TableListSemua;
import com.example.lenovo.appbeta.R;
import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{
    //nav CustomAdapterDrawer
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ListView mDrawerList;
    private Integer kategori;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true); //set logo
        getSupportActionBar().setIcon(R.drawable.app_title); //set logo

        FacebookSdk.sdkInitialize(getApplicationContext());

        getIntent();
        Bundle extras = getIntent().getExtras();
        kategori=5;
        if (extras != null) {
            kategori = extras.getInt("position",-1);
            // and get whatever id is
        }

        if (kategori == 0)
        {
            populateListSD();
            registerOnClickMethodSD();
        }
        else if (kategori == 1)
        {
            populateListSmp();
            registerOnClickMethodSmp();
        }
        else if (kategori == 2)
        {
            populateListSma();
            registerOnClickMethodSma();
        }
        else if (kategori == 3)
        {
            populateListMahasw();
            registerOnClickMethodMahasw();
        }
        else if (kategori == 4)
        {
            populateListUmum();
            registerOnClickMethodUmum();
        }
        else
        {
            populatelistSemua();
            registerOnClickMethod();
        }

        //for(int i=0; i<13; i++){
        //    if (check.equals(kategoriArtikel[i])){;}
        //    else if (check.equals("semua")){;}
        //    else {adapter.remove(listName[i]);}
        //}


        //navdrawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        TypedArray navlistImage = getResources().obtainTypedArray(R.array.drawerIconLst);
        String[] navlistName = getResources().getStringArray(R.array.navlist);

        mDrawerList = (ListView)findViewById(R.id.listView02);
        CustomAdapterDrawer navAdapter = new CustomAdapterDrawer(this, navlistName, navlistImage);

        mDrawerList.setAdapter(navAdapter);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

       registerOnClickDrawer();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Keluar")
                .setMessage("Anda yakin keluar?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .show();
    }

    private void populateListSD()
    {
        TypedArray listImage = getResources().obtainTypedArray(R.array.imageHLlist);
        TypedArray listImage2 = getResources().obtainTypedArray(R.array.imageHLlist);
        String[] listName = getResources().getStringArray(R.array.testlist);

        //filter population
        String[] kategori = getResources().getStringArray(R.array.kategori);
        Integer len = listImage.length();
        Integer len2 = listImage2.length();

        Integer[]resIds = new Integer[len];
        Integer[]resIds2 = new Integer[len2];

        for (int i = 0; i < len; i++) {
            resIds[i] = listImage.getResourceId(i, -1);
        }
        listImage.recycle();

        for (int i = 0; i < len2; i++) {
            resIds2[i] = listImage2.getResourceId(i, -1);
        }
        listImage2.recycle();


        Integer listLength=listName.length;
        int a = 0;

        for (int i = 0; i < listLength; i++){
            if (kategori[i].equals("SD"))
            {
                a=a+1;
            }
        }

        String[]listName2=new String[a];
        a=a+1;
        Integer[]realList=new Integer[a];


        Integer temp = 0;
        for (int i=0 ; i<listLength;i++){
            if (kategori[i].equals("SD"))
            {
                listName2 [temp]= listName[i];
                resIds[temp]=resIds[i];
                resIds2[temp]=resIds2[i];
                temp=temp+1;
                realList[temp]=i;
            }
        }
        //filter population end

        ArrayList<String> arrayName= new ArrayList<String>(Arrays.asList(listName2));

        CustomAdapterKategoriFilter02 kategoriAdapter = new CustomAdapterKategoriFilter02(this, arrayName, resIds, resIds2);

        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(kategoriAdapter);
    }

    private void populateListSmp()
    {
        TypedArray listImage = getResources().obtainTypedArray(R.array.imageHLlist);
        TypedArray listImage2 = getResources().obtainTypedArray(R.array.imageHLlist);
        String[] listName = getResources().getStringArray(R.array.testlist);

        //filter population
        String[] kategori = getResources().getStringArray(R.array.kategori);
        Integer len = listImage.length();
        Integer len2 = listImage2.length();

        Integer[]resIds = new Integer[len];
        Integer[]resIds2 = new Integer[len2];

        for (int i = 0; i < len; i++) {
            resIds[i] = listImage.getResourceId(i, -1);
        }
        listImage.recycle();

        for (int i = 0; i < len2; i++) {
            resIds2[i] = listImage2.getResourceId(i, -1);
        }
        listImage2.recycle();


        Integer listLength=listName.length;
        int a = 0;

        for (int i = 0; i < listLength; i++){
            if (kategori[i].equals("SMP"))
            {
                a=a+1;
            }
        }

        String[]listName2=new String[a];
        a=a+1;
        Integer[]realList=new Integer[a];


        Integer temp = 0;
        for (int i=0 ; i<listLength;i++){
            if (kategori[i].equals("SMP"))
            {
                listName2 [temp]= listName[i];
                resIds[temp]=resIds[i];
                resIds2[temp]=resIds2[i];
                temp=temp+1;
                realList[temp]=i;
            }
        }
        //filter population end

        ArrayList<String> arrayName= new ArrayList<String>(Arrays.asList(listName2));

        CustomAdapterKategoriFilter02 kategoriAdapter = new CustomAdapterKategoriFilter02(this, arrayName, resIds, resIds2);

        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(kategoriAdapter);
    }

    private void populateListSma()
    {
        TypedArray listImage = getResources().obtainTypedArray(R.array.imageHLlist);
        TypedArray listImage2 = getResources().obtainTypedArray(R.array.imageHLlist);
        String[] listName = getResources().getStringArray(R.array.testlist);

        //filter population
        String[] kategori = getResources().getStringArray(R.array.kategori);
        Integer len = listImage.length();
        Integer len2 = listImage2.length();

        Integer[]resIds = new Integer[len];
        Integer[]resIds2 = new Integer[len2];

        for (int i = 0; i < len; i++) {
            resIds[i] = listImage.getResourceId(i, -1);
        }
        listImage.recycle();

        for (int i = 0; i < len2; i++) {
            resIds2[i] = listImage2.getResourceId(i, -1);
        }
        listImage2.recycle();


        Integer listLength=listName.length;
        int a = 0;

        for (int i = 0; i < listLength; i++){
            if (kategori[i].equals("SMA"))
            {
                a=a+1;
            }
        }

        String[]listName2=new String[a];
        a=a+1;
        Integer[]realList=new Integer[a];


        Integer temp = 0;
        for (int i=0 ; i<listLength;i++){
            if (kategori[i].equals("SMA"))
            {
                listName2 [temp]= listName[i];
                resIds[temp]=resIds[i];
                resIds2[temp]=resIds2[i];
                temp=temp+1;
                realList[temp]=i;
            }
        }
        //filter population end

        ArrayList<String> arrayName= new ArrayList<String>(Arrays.asList(listName2));

        CustomAdapterKategoriFilter02 kategoriAdapter = new CustomAdapterKategoriFilter02(this, arrayName, resIds, resIds2);

        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(kategoriAdapter);
    }

    private void populateListMahasw()
    {
        TypedArray listImage = getResources().obtainTypedArray(R.array.imageHLlist);
        TypedArray listImage2 = getResources().obtainTypedArray(R.array.imageHLlist);
        String[] listName = getResources().getStringArray(R.array.testlist);

        //filter population
        String[] kategori = getResources().getStringArray(R.array.kategori);
        Integer len = listImage.length();
        Integer len2 = listImage2.length();

        Integer[]resIds = new Integer[len];
        Integer[]resIds2 = new Integer[len2];

        for (int i = 0; i < len; i++) {
            resIds[i] = listImage.getResourceId(i, -1);
        }
        listImage.recycle();

        for (int i = 0; i < len2; i++) {
            resIds2[i] = listImage2.getResourceId(i, -1);
        }
        listImage2.recycle();


        Integer listLength=listName.length;
        int a = 0;

        for (int i = 0; i < listLength; i++){
            if (kategori[i].equals("Mahasiswa"))
            {
                a=a+1;
            }
        }

        String[]listName2=new String[a];
        a=a+1;
        Integer[]realList=new Integer[a];


        Integer temp = 0;
        for (int i=0 ; i<listLength;i++){
            if (kategori[i].equals("Mahasiswa"))
            {
                listName2 [temp]= listName[i];
                resIds[temp]=resIds[i];
                resIds2[temp]=resIds2[i];
                temp=temp+1;
                realList[temp]=i;
            }
        }
        //filter population end

        ArrayList<String> arrayName= new ArrayList<String>(Arrays.asList(listName2));

        CustomAdapterKategoriFilter02 kategoriAdapter = new CustomAdapterKategoriFilter02(this, arrayName, resIds, resIds2);

        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(kategoriAdapter);
    }

    private void populateListUmum()
    {
        TypedArray listImage = getResources().obtainTypedArray(R.array.imageHLlist);
        TypedArray listImage2 = getResources().obtainTypedArray(R.array.imageHLlist);
        String[] listName = getResources().getStringArray(R.array.testlist);

        //filter population
        String[] kategori = getResources().getStringArray(R.array.kategori);
        Integer len = listImage.length();
        Integer len2 = listImage2.length();

        Integer[]resIds = new Integer[len];
        Integer[]resIds2 = new Integer[len2];

        for (int i = 0; i < len; i++) {
            resIds[i] = listImage.getResourceId(i, -1);
        }
        listImage.recycle();

        for (int i = 0; i < len2; i++) {
            resIds2[i] = listImage2.getResourceId(i, -1);
        }
        listImage2.recycle();


        Integer listLength=listName.length;
        int a = 0;

        for (int i = 0; i < listLength; i++){
            if (kategori[i].equals("Umum"))
            {
                a=a+1;
            }
        }

        String[]listName2=new String[a];
        a=a+1;
        Integer[]realList=new Integer[a];


        Integer temp = 0;
        for (int i=0 ; i<listLength;i++){
            if (kategori[i].equals("Umum"))
            {
                listName2 [temp]= listName[i];
                resIds[temp]=resIds[i];
                resIds2[temp]=resIds2[i];
                temp=temp+1;
                realList[temp]=i;
            }
        }
        //filter population end

        ArrayList<String> arrayName= new ArrayList<String>(Arrays.asList(listName2));

        CustomAdapterKategoriFilter02 kategoriAdapter = new CustomAdapterKategoriFilter02(this, arrayName, resIds, resIds2);

        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(kategoriAdapter);
    }

    private void populatelistSemua()
    {
        TypedArray listImage = getResources().obtainTypedArray(R.array.imageHLlist);
        TypedArray listImage2 = getResources().obtainTypedArray(R.array.imageHLlist);
        String[] listName = getResources().getStringArray(R.array.testlist);

        //filter population
        Integer len = listImage.length();
        Integer len2 = listImage2.length();

        Integer[]resIds = new Integer[len];
        Integer[]resIds2 = new Integer[len2];

        for (int i = 0; i < len; i++) {
            resIds[i] = listImage.getResourceId(i, -1);
        }
        listImage.recycle();

        for (int i = 0; i < len2; i++) {
            resIds2[i] = listImage2.getResourceId(i, -1);
        }
        listImage2.recycle();


        Integer listLength=listName.length;
        int a = 0;
        for (int i = 0; i < listLength; i++){
            a=a+1;
        }

        String[]listName2=new String[a];
        a=a+1;
        Integer[]realList=new Integer[a];

        String[] articleIndex = getResources().getStringArray(R.array.articleIndex);

        Integer temp = 0;
        for (int i=0 ; i<listLength;i++){
            int articleIndexInt = Integer.parseInt(articleIndex[i]);
            articleIndexInt = articleIndexInt-1;

            for (int b=0 ; b<listLength;b++){
                if (articleIndexInt==b)
                {
                    listName2 [temp]= listName[b];
                    resIds[temp]=resIds[b];
                    resIds2[temp]=resIds2[b];
                    temp=temp+1;
                    realList[temp]=b;
                }
            }
        }

        //filter population end

        ArrayList<String> arrayName= new ArrayList<String>(Arrays.asList(listName2));

        CustomAdapterKategoriFilter02 kategoriAdapter = new CustomAdapterKategoriFilter02(this, arrayName, resIds, resIds2);

        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(kategoriAdapter);
    }

    public class CustomAdapterKategori extends ArrayAdapter<String>
    {
        Activity context;
        ArrayList<String> listName;
        TypedArray listImage;
        private static final int layout_type1=0;
        private static final int layout_type2=1;

        public CustomAdapterKategori(Activity context, ArrayList<String> listName, TypedArray listImage)
        {
            super(context, R.layout.content_list_view,listName);
            this.context=context;
            this.listName=listName;
            this.listImage=listImage;
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
            View rowView = convertView;
            LayoutInflater inflater = null;
            int layout_type = getItemViewType(position);

            if (rowView == null) {
                if (layout_type == layout_type1) {
                    inflater = context.getLayoutInflater();
                    rowView = inflater.inflate(R.layout.content_list_view02, null, true);
                } else {
                    inflater = context.getLayoutInflater();
                    rowView = inflater.inflate(R.layout.content_list_view, null, true);
                }
            }
            TextView textTitle = (TextView) rowView.findViewById(R.id.textView01);
            ImageView imageTitle = (ImageView) rowView.findViewById(R.id.ImageView01);

            textTitle.setText(listName.get(position));

            //imageTitle.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.id.ImageView01, 100, 100));

            imageTitle.setImageResource(listImage.getResourceId(position, -1));

                return rowView;
            }
    }



    //test

    //testend

    private void registerOnClickDrawer()
    {
        final ListView mDrawerList = (ListView) findViewById(R.id.listView02);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent0 = new Intent(MainActivity.this, MainActivity.class);
                intent0.putExtra("position", position);
                startActivity(intent0);
                finish();
            }
        });
}

        //navdrawer open close button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void registerOnClickMethodSD() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            //filter population
                                            String[] listName = getResources().getStringArray(R.array.testlist);
                                            String[] kategori = getResources().getStringArray(R.array.kategori);
                                            Integer listLength=listName.length;
                                            int a = 0;
                                            for (int i = 0; i < listLength; i++){
                                                if (kategori[i].equals("SD"))
                                                {
                                                    a=a+1;
                                                }
                                            }

                                            a=a+1;
                                            Integer[]realList=new Integer[a];

                                            Integer temp = 0;
                                            for (int i=0 ; i<listLength;i++){
                                                if (kategori[i].equals("SD"))
                                                {
                                                    temp=temp+1;
                                                    realList[temp]=i;
                                                }
                                            }
                                            //filter population end
                                            Intent intent0 = new Intent(MainActivity.this, Article.class);
                                            intent0.putExtra("kategori", kategori); // merujuk untuk reload ulang page ini
                                            position=position+1;
                                            intent0.putExtra("position", realList[position]); //merujuk ke article
                                            startActivity(intent0);
                                        }
                                    }
        );
    }

    private void registerOnClickMethodSmp() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            //filter population
                                            String[] listName = getResources().getStringArray(R.array.testlist);
                                            String[] kategori = getResources().getStringArray(R.array.kategori);
                                            Integer listLength=listName.length;
                                            int a = 0;
                                            for (int i = 0; i < listLength; i++){
                                                if (kategori[i].equals("SMP"))
                                                {
                                                    a=a+1;
                                                }
                                            }

                                            a=a+1;
                                            Integer[]realList=new Integer[a];

                                            Integer temp = 0;
                                            for (int i=0 ; i<listLength;i++){
                                                if (kategori[i].equals("SMP"))
                                                {
                                                    temp=temp+1;
                                                    realList[temp]=i;
                                                }
                                            }
                                            //filter population end
                                            Intent intent0 = new Intent(MainActivity.this, Article.class);
                                            intent0.putExtra("kategori", kategori);
                                            position=position+1;
                                            intent0.putExtra("position", realList[position]);
                                            startActivity(intent0);
                                        }
                                    }
        );
    }

    private void registerOnClickMethodSma() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            //filter population
                                            String[] listName = getResources().getStringArray(R.array.testlist);
                                            String[] kategori = getResources().getStringArray(R.array.kategori);
                                            Integer listLength=listName.length;
                                            int a = 0;
                                            for (int i = 0; i < listLength; i++){
                                                if (kategori[i].equals("SMA"))
                                                {
                                                    a=a+1;
                                                }
                                            }

                                            a=a+1;
                                            Integer[]realList=new Integer[a];

                                            Integer temp = 0;
                                            for (int i=0 ; i<listLength;i++){
                                                if (kategori[i].equals("SMA"))
                                                {
                                                    temp=temp+1;
                                                    realList[temp]=i;
                                                }
                                            }
                                            //filter population end
                                            Intent intent0 = new Intent(MainActivity.this, Article.class);
                                            intent0.putExtra("kategori", kategori);
                                            position=position+1;
                                            intent0.putExtra("position", realList[position]);
                                            startActivity(intent0);
                                        }
                                    }
        );
    }

    private void registerOnClickMethodMahasw() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            String[] listName = getResources().getStringArray(R.array.testlist);
                                            String[] kategori = getResources().getStringArray(R.array.kategori);
                                            Integer listLength=listName.length;


                                            //filter population
                                            int a = 0;
                                            for (int i = 0; i < listLength; i++){
                                                if (kategori[i].equals("Mahasiswa"))
                                                {
                                                    a=a+1;
                                                }
                                            }

                                            a=a+1;
                                            Integer[]realList=new Integer[a];

                                            Integer temp = 0;
                                            for (int i=0 ; i<listLength;i++){
                                                if (kategori[i].equals("Mahasiswa"))
                                                {
                                                    temp=temp+1;
                                                    realList[temp]=i;
                                                }
                                            }

                                            //filter population end
                                            Intent intent0 = new Intent(MainActivity.this, Article.class);
                                            intent0.putExtra("kategori", kategori);
                                            position=position+1;
                                            intent0.putExtra("position", realList[position]);
                                            startActivity(intent0);
                                        }
                                    }
        );
    }

    private void registerOnClickMethodUmum() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            //filter population
                                            String[] listName = getResources().getStringArray(R.array.testlist);
                                            String[] kategoriList = getResources().getStringArray(R.array.kategori);
                                            Integer listLength=listName.length;

                                            TableListSemua filtered = new TableListSemua();
                                            /*
                                            int a = 0;
                                            for (int i = 0; i < listLength; i++){
                                                if (kategoriList[i].equals("Umum"))
                                                {
                                                    a=a+1;
                                                }
                                            }

                                            a=a+1;
                                            Integer[]realList=new Integer[a];

                                            Integer temp = 0;
                                            for (int i=0 ; i<listLength;i++){
                                                if (kategoriList[i].equals("Umum"))
                                                {
                                                    temp=temp+1;
                                                    realList[temp]=i;
                                                }
                                            }
                                            */
                                            //filter population end
                                            Intent intent0 = new Intent(MainActivity.this, Article.class);
                                            intent0.putExtra("kategori", kategoriList);
                                            position=position+1;
                                            intent0.putExtra("position", filtered.calculateListWithFilter(listLength,kategoriList ,position, "Umum"));
                                            startActivity(intent0);
                                        }
                                    }
        );
    }

    private void registerOnClickMethod() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            //filter population start
                                            String[] listName = getResources().getStringArray(R.array.testlist);
                                            String[] kategori = getResources().getStringArray(R.array.kategori);
                                            Integer listLength=listName.length;
                                            String[] articleIndex = getResources().getStringArray(R.array.articleIndex);


                                            TableListSemua ListSemua = new TableListSemua();

                                            /* moved to ListSemua.calculateList(listLength,articleIndex, position)
                                            int a = 0;
                                            for (int i = 0; i < listLength; i++){
                                                    a=a+1;
                                            }

                                            a=a+1;
                                            Integer[]realList=new Integer[a];


                                            Integer temp = 0;
                                            for (int i=0 ; i<listLength;i++){
                                                int articleIndexInt = Integer.parseInt(articleIndex[i]);
                                                articleIndexInt = articleIndexInt-1;

                                                for (int b=0 ; b<listLength;b++){
                                                    if (articleIndexInt==b)
                                                    {
                                                        temp=temp+1;
                                                        realList[temp]=b;
                                                    }
                                                }
                                            }
                                            */
                                            //filter population end
                                            Intent intent0 = new Intent(MainActivity.this, Article.class);
                                            intent0.putExtra("kategori", kategori);
                                            position=position+1;
                                            intent0.putExtra("position", ListSemua.calculateList(listLength,articleIndex, position));
                                            startActivity(intent0);
                                        }
                                    }
        );
    }
}

package com.example.lenovo.appbeta.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.appbeta.Models.FirebaseModel;
import com.example.lenovo.appbeta.R;
import com.example.lenovo.appbeta.ViewModel.CategoryFilterAdapter;
import com.example.lenovo.appbeta.ViewModel.CustomAdapterDrawer;
import com.facebook.FacebookSdk;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    //nav CustomAdapterDrawer
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ListView mDrawerList;
    private Integer kategori;
    private ArrayList<FirebaseModel> fireModel = new ArrayList<>();

    Activity currentContext;
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

        Firebase.setAndroidContext(this);

        getIntent();
        Bundle extras = getIntent().getExtras();

        currentContext = this;

        kategori = 5;
        if (extras != null) {
            kategori = extras.getInt("position", -1);
            // and get whatever id is
        }

        String category;

        switch (kategori) {
            case 0:
                category = "sd";
                getDatabase(category);
                break;

            case 1:
                category = "smp";
                getDatabase(category);
                break;

            case 2:
                category = "sma";
                getDatabase(category);
                break;

            case 3:
                category = "mahasiswa";
                getDatabase(category);
                break;

            case 4:
                category = "umum";
                getDatabase(category);
                break;

            default:
                category = "semua";
                getDatabase(category);
                break;
        }

        //navdrawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        TypedArray navlistImage = getResources().obtainTypedArray(R.array.drawerIconLst);
        String[] navlistName = getResources().getStringArray(R.array.navlist);

        mDrawerList = (ListView) findViewById(R.id.listView02);
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
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .show();
    }

    private void registerOnClickDrawer() {
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

    private void registerOnClickMethodforFirebase() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                            Intent intent0 = new Intent(MainActivity.this, ArticleFromFireDB.class);
                                            intent0.putExtra("kategori", kategori); // merujuk untuk reload ulang page ini
                                            intent0.putExtra("parsedTitle", fireModel.get(position).parsedTitle);
                                            intent0.putExtra("parsedText",fireModel.get(position).parsedText); //merujuk ke article
                                            intent0.putExtra("parsedImageUrl", fireModel.get(position).parsedImageUrl);
                                            intent0.putExtra("parsedArticleUrl", fireModel.get(position).parsedArticleUrl);
                                            startActivity(intent0);
                                        }
                                    }
        );
    }

    private void getDatabase(String category) {

        Firebase firebaseDB = new Firebase("https://bantupen-f7f8a.firebaseio.com/");

        final ArrayList<String> listOfCategory = new ArrayList<>();

        if (category == "semua") {

            listOfCategory.add("sd");
            listOfCategory.add("smp");
            listOfCategory.add("sma");
            listOfCategory.add("mahasiswa");
            listOfCategory.add("umum");

        } else {

            listOfCategory.add(category);

        }

        for (final String currentCategory: listOfCategory){

                Firebase newChild = firebaseDB.child(currentCategory);

                newChild.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                            String text = (String) singleSnapshot.child("text").getValue();
                            String title =  (String) singleSnapshot.child("title").getValue();
                            String imageUrl =  (String) singleSnapshot.child("imageUrl").getValue();
                            String articleUrl = (String) singleSnapshot.child("articleUrl").getValue();
                            String tempKey =  singleSnapshot.getKey();

                            FirebaseModel tempModel = new FirebaseModel(text,title,imageUrl, articleUrl, tempKey);

                            fireModel.add(tempModel);

                        }

                        Collections.sort(fireModel, new Comparator<FirebaseModel>() {
                            @Override
                            public int compare(FirebaseModel t1, FirebaseModel t2) {
                                String s1 = t1.key;
                                String s2 = t2.key;
                                return s2.compareTo(s1);
                            }
                        });

                        CategoryFilterAdapter kategoriAdapter = new CategoryFilterAdapter(currentContext, fireModel);

                        list = (ListView) findViewById(R.id.listView);
                        list.setAdapter(kategoriAdapter);

                        registerOnClickMethodforFirebase();
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
    }
}
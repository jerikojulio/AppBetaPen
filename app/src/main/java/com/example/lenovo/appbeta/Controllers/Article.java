package com.example.lenovo.appbeta.Controllers;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.appbeta.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

public class Article extends AppCompatActivity {

    ShareDialog shareDialog;
    CallbackManager callbackManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        getIntent();
        Bundle bundle = getIntent().getExtras();
        //Integer position = 1;
        artikellistSemua();

        /*
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        printKeyHash();
        */
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*
    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("jk", "Exception(NameNotFoundException) : " + e);
        } catch (NoSuchAlgorithmException e) {
            Log.e("mkm", "Exception(NoSuchAlgorithmException) : " + e);
        }
    }
    */


    private void artikellistSemua() {
        getIntent();
        Bundle bundle = getIntent().getExtras();
        //Integer position = 1;
        Integer position = bundle.getInt("position", -1);

        String[] articletitle = getResources().getStringArray(R.array.testlist);
        TextView articletitleText = (TextView) findViewById(R.id.articletextView02);
        articletitleText.setText(articletitle[position]);

        String[] articlelist = getResources().getStringArray(R.array.articlelist);
        TextView articleTextview = (TextView) findViewById(R.id.articletextView01);
        //articleTextview.setText(articlelist[position]);

        if (Build.VERSION.SDK_INT >= 24) {
            articleTextview.setText(Html.fromHtml(articlelist[position], -1)); // for 24 api and more
        } else {
            articleTextview.setText(Html.fromHtml(articlelist[position])); // for older api
        }

        TypedArray imageList = getResources().obtainTypedArray(R.array.imageHLlist);
        ImageView articleImageview = (ImageView) findViewById(R.id.articleImageView01);
        articleImageview.setImageResource(imageList.getResourceId(position, -1));
    }

    public void Share(View view) {
        //ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
        //shareButton.setShareContent(content);

        getIntent();
        Bundle bundle = getIntent().getExtras();
        //Integer position = 1;
        Integer position = bundle.getInt("position", -1);

        String[] articletitle = getResources().getStringArray(R.array.testlist);

        String[] articlelist = getResources().getStringArray(R.array.articlelist);
        if (Build.VERSION.SDK_INT >= 24) {
            articlelist[position] = Html.fromHtml(articlelist[position], -1).toString(); // for 24 api and more
        } else {
            articlelist[position] = Html.fromHtml(articlelist[position]).toString(); // for older api
        }

        String[] articleimageurl = getResources().getStringArray(R.array.articleimageurl);
        String[] articleurl = getResources().getStringArray(R.array.articleurl);

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(articletitle[position])
                    .setContentDescription(
                            articlelist[position])
                    //"The 'Hello Facebook' sample  showcases simple Facebook integration")
                    .setContentUrl(Uri.parse(articleurl[position]))
                    .setImageUrl(Uri.parse(articleimageurl[position]))
                    .build();

            shareDialog.show(linkContent);
        }
    } //facebook share

    private Integer imageFilter(Integer position){
        TypedArray listImage = getResources().obtainTypedArray(R.array.imageHLlist);
        Integer len = listImage.length();
        Integer[]resIds = new Integer[len];
        for (int i = 0; i < len; i++) {
            resIds[i] = listImage.getResourceId(i, -1);
        }
        listImage.recycle();
        return resIds[position];
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, "Instagram not installed!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void createInstagramIntent(View view){

        getIntent();
        Bundle bundle = getIntent().getExtras();
        Integer position = bundle.getInt("position", -1);

        //Uri file = Uri.parse("android.resource://com.example.lenovo.appbeta/drawable/ai30");
        //Uri file = Uri.parse("android.resource://com.example.lenovo.appbeta/drawable/"+R.drawable.ai30);

        //pass local drawables
        Uri file = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(imageFilter(position))
                + '/' + getResources().getResourceTypeName(imageFilter(position)) + '/' + getResources().getResourceEntryName(imageFilter(position)));
        //pass local drawables end here


        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM,file);
        shareIntent.putExtra(Intent.EXTRA_TITLE, "testtesttesttest");
        shareIntent.setPackage("com.instagram.android");
        if (isAppInstalled(this,"com.instagram.android")){startActivity(shareIntent);}
    }
}

package com.example.lenovo.appbeta.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.appbeta.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ArticleFromFireDB extends AppCompatActivity {


    ShareDialog shareDialog;
    CallbackManager callbackManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private String parsedTitle;
    private String parsedText;
    private String parsedImageUrl;
    private String parsedArticleUrl;
    private Bundle passedBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        setUpBundleInfos();
        loadArticle();

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

    private void setUpBundleInfos(){
        getIntent();
        passedBundle = getIntent().getExtras();

        parsedTitle = passedBundle.getString("parsedTitle");
        parsedText = passedBundle.getString("parsedText");
        parsedImageUrl = passedBundle.getString("parsedImageUrl");
        parsedArticleUrl = passedBundle.getString("parsedArticleUrl");

    }


    private void loadArticle() {

        TextView articleTitle = (TextView) findViewById(R.id.articletextView02);
        TextView articleText = (TextView) findViewById(R.id.articletextView01);

        articleTitle.setText(parsedTitle);

        if (Build.VERSION.SDK_INT >= 24) {
            articleText.setText(Html.fromHtml(parsedText, -1)); // for 24 api and more
        } else {
            articleText.setText(Html.fromHtml(parsedText)); // for older api
        }
        ImageView articleImageview = (ImageView) findViewById(R.id.articleImageView01);

        Glide
                .with(this)
                .load(parsedImageUrl)
                .centerCrop()
                .crossFade()
                .into(articleImageview);
    }

    public void Share(View view) {

        String finishedText;

        if (Build.VERSION.SDK_INT >= 24) {
            finishedText = Html.fromHtml(parsedText, -1).toString(); // for 24 api and more
        } else {
            finishedText = Html.fromHtml(parsedText).toString(); // for older api
        }

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(parsedTitle)
                    .setContentDescription(
                            finishedText)
                    //"The 'Hello Facebook' sample  showcases simple Facebook integration")
                    .setContentUrl(Uri.parse(parsedArticleUrl))
                    .setImageUrl(Uri.parse(parsedImageUrl))
                    .build();

            shareDialog.show(linkContent);
        }
    } //facebook share

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

        Uri file = Uri.parse(parsedImageUrl);

        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM,file);
        shareIntent.putExtra(Intent.EXTRA_TITLE, "testtesttesttest");
        shareIntent.setPackage("com.instagram.android");
        if (isAppInstalled(this,"com.instagram.android")){startActivity(shareIntent);}
    }

}

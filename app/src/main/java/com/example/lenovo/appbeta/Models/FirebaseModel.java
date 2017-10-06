package com.example.lenovo.appbeta.Models;

/**
 * Created by jeriko on 8/21/17.
 */

public class FirebaseModel {

    public String parsedText;
    public String parsedTitle;
    public String parsedImageUrl;
    public String parsedArticleUrl;
    public String key;

    public FirebaseModel(String parsedText, String parsedTitle, String parsedImageUrl, String parsedArticleUrl, String key)
    {
        this.parsedText = parsedText;
        this.parsedArticleUrl = parsedArticleUrl;
        this.parsedTitle = parsedTitle;
        this.parsedImageUrl = parsedImageUrl;
        this.key = key;
    }
}

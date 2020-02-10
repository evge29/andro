package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.DrawableRes;

public class Horoscope {

    public String title;
    public  String info;
    public int imageResource;
    public String text;

    public static String TITLE_KEY = "Title";
    public static String IMAGE_KEY = "Image Resource";
    public static String TEXT_KEY= "Text";

    public Horoscope(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }



    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }


    public int getImageResource() {
        return imageResource;
    }
    public String getText(){
        return text;
    }


    public static Intent starter(Context context, String title, @DrawableRes int imageResId, String text) {
        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra(TITLE_KEY, title);
        detailIntent.putExtra(IMAGE_KEY, imageResId);
        detailIntent.putExtra(TEXT_KEY,text);
        return detailIntent;
    }
}

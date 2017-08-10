package net.callofdroidy.pictureoftheday.view;

import android.content.Context;

import net.callofdroidy.pictureoftheday.MyIdlingResource;

public interface ViewActMain {
    void initViews();

    void showPicture(String url);

    void showPicTitle(String title);

    void showErrMsg(String msg);

    Context getContext();

    MyIdlingResource getIdlingResource();
}

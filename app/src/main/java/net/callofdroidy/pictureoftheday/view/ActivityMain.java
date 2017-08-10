package net.callofdroidy.pictureoftheday.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.callofdroidy.pictureoftheday.R;
import net.callofdroidy.pictureoftheday.MyIdlingResource;
import net.callofdroidy.pictureoftheday.presenter.PresenterPicture;

public class ActivityMain extends AppCompatActivity implements ViewActMain {
    private static final String TAG = "ActivityMain";

    ImageView ivPicture;
    TextView tvTitle;

    PresenterPicture presenterPicture;

    public MyIdlingResource simpleIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleIdlingResource = new MyIdlingResource();

        initViews();

        presenterPicture = new PresenterPicture(this);

        simpleIdlingResource.setIdleState(false);
        presenterPicture.getNasaPicture();
    }

    @Override
    public void initViews(){
        ivPicture = (ImageView) findViewById(R.id.iv_picture);
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    @Override
    public void showPicture(String url){
        Picasso.with(ActivityMain.this)
                .load(url)
                .resize(ivPicture.getWidth(), ivPicture.getHeight())
                .centerInside()
                .into(ivPicture);
    }

    @Override
    public void showPicTitle(String title){
        tvTitle.setText(title);
    }

    @Override
    public void showErrMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext(){
        return this;
    }

    @Override
    public MyIdlingResource getIdlingResource(){
        if(simpleIdlingResource == null)
            simpleIdlingResource = new MyIdlingResource();
        return simpleIdlingResource;
    }

    public void onPictureClick(View view){
        Intent intent = new Intent(this, ActivityFullScreenPic.class);
        intent.putExtra("url", presenterPicture.getUrl());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, ivPicture, "nasa");
        startActivity(intent, optionsCompat.toBundle());
    }
}

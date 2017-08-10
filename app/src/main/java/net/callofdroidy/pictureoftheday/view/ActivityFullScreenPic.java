package net.callofdroidy.pictureoftheday.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.callofdroidy.pictureoftheday.R;

public class ActivityFullScreenPic extends AppCompatActivity {

    ImageView ivPictureLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        ivPictureLarge = (ImageView) findViewById(R.id.iv_picture_large);
        Picasso.with(this)
                .load(getIntent().getStringExtra("url"))
                .into(ivPictureLarge);
    }
}

package com.example.hunter.pitch;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Congrats extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sap);
        mediaPlayer.start();

        mImageView = (ImageView) findViewById(R.id.imageView2);
        mImageView.setImageResource(R.drawable.cup);
    }
}

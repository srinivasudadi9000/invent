package com.maple.goindia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
     ImageView boom_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boom_img = (ImageView) findViewById(R.id.boom_img);
        boom_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
                slideUp.setRepeatCount(Animation.INFINITE);
                boom_img.startAnimation(slideUp);*/

                Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_grow_fade_in_from_bottom);
                slideUp.setRepeatCount(Animation.INFINITE);
                boom_img.startAnimation(slideUp);
            }
        });


    }
}

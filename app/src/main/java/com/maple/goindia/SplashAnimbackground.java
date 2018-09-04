package com.maple.goindia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashAnimbackground extends AppCompatActivity {
    private StarAnimationView mAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_animbackground);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.splash_animbackground);
        mAnimationView = (StarAnimationView) findViewById(R.id.animated_view);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}

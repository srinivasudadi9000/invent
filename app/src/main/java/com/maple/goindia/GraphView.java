package com.maple.goindia;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Path;
import java.util.ArrayList;

public class GraphView extends Activity {
    RecyclerView graph_rv;
    ArrayList<Graph_Path> paths;
    StudyAdapterGraph studyAdapterGraph;
    ImageView home_img;
    RelativeLayout home_rll;
    AnimationDrawable drawableAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_view);
        home_img = (ImageView) findViewById(R.id.home_img);
        home_rll = (RelativeLayout) findViewById(R.id.home_rll);
       /* home_img.setBackgroundResource(R.drawable.running);
        drawableAnimation = (AnimationDrawable) home_img.getBackground();
        drawableAnimation.start();*/

       /* Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
        slideUp.setRepeatCount(Animation.INFINITE);
        home_img.startAnimation(slideUp);*/

        TranslateAnimation animation = new TranslateAnimation(0.0f, 1000.0f,
                0.0f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(5000);  // animation duration
        animation.setRepeatCount(Animation.INFINITE);  // animation repeat count
        animation.setRepeatMode(Animation.RESTART);   // repeat animation (left to right, right to left )
        //animation.setFillAfter(true);

        home_img.startAnimation(animation);

      /*  final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = home_rll.getWidth();
                final float translationX = width * progress;
                home_rll.setTranslationX(translationX);
                home_rll.setTranslationX(translationX - width);
            }
        });
        animator.start();*/

        graph_rv = (RecyclerView) findViewById(R.id.graph_rv);
        graph_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        paths = new ArrayList<Graph_Path>();
        try {
            JSONArray m_jArry = new JSONArray(getIntent().getStringExtra("path"));
            // JSONArray m_jArry = obj.getJSONArray("Message");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject res = m_jArry.getJSONObject(i);
                System.out.println("Resule " + res.toString());
                paths.add(new Graph_Path(res.getString("classes"), res.getString("duration")));
                //   messages.add(new Study(res.getString("goal").toString(),res.getString("code"),res.getString("path")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Resule " + e.toString());
        }

        studyAdapterGraph = new StudyAdapterGraph(paths, R.layout.study_single_hor, getApplicationContext());
        graph_rv.setAdapter(studyAdapterGraph);


    }
}

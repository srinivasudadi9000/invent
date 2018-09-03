package com.maple.goindia;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Splash extends Activity {
    RecyclerView classes_rv, graph_rv;
    ArrayList<Study> messages;
    StudyAdapter messagingAdapter;
    StudyAdapterGraph studyAdapterGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        graph_rv = (RecyclerView) findViewById(R.id.graph_rv);
        graph_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        classes_rv = (RecyclerView) findViewById(R.id.classes_rv);
        classes_rv.setLayoutManager(new LinearLayoutManager(this));
        messages = new ArrayList<Study>();
        messages.add(new Study("1", "Tenth"));
        messages.add(new Study("1", "Intermediate"));
        messages.add(new Study("1", "Degree"));
        messages.add(new Study("1", "B.Tech"));
        messages.add(new Study("1", "Diplomatic"));
        messages.add(new Study("1", "Aeuronotical Engineering"));
        messagingAdapter = new StudyAdapter(messages, R.layout.study_single, getApplicationContext());
        classes_rv.setAdapter(messagingAdapter);

        studyAdapterGraph = new StudyAdapterGraph(messages, R.layout.study_single_hor, getApplicationContext());
        graph_rv.setAdapter(studyAdapterGraph);


    }
}

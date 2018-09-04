package com.maple.goindia;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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
        // classes_rv.setLayoutManager(new GridLayoutManager(this,5, LinearLayoutManager.HORIZONTAL, false));
        // classes_rv.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
        messages = new ArrayList<Study>();

        messagingAdapter = new StudyAdapter(messages, R.layout.study_single, getApplicationContext());
        classes_rv.setAdapter(messagingAdapter);

       /* studyAdapterGraph = new StudyAdapterGraph(messages, R.layout.study_single_hor, getApplicationContext());
        graph_rv.setAdapter(studyAdapterGraph);
*/
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("Message");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject res = m_jArry.getJSONObject(i);
                messages.add(new Study(res.getString("goal").toString(), res.getString("code"), res.getString("path")));
            }
            System.out.println("Resule " + m_jArry.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Resule " + e.toString());
        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = Splash.this.getAssets().open("ss.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            System.out.println("Resule " + json.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Resule " + ex.toString());
            return null;
        }
        return json;
    }
}

package com.maple.goindia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Path;
import java.util.ArrayList;

public class GraphView extends AppCompatActivity {
    RecyclerView graph_rv;
    ArrayList<Graph_Path> paths;
    StudyAdapterGraph studyAdapterGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_view);
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

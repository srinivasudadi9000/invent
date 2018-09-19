package com.maple.goindia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class Splash extends Activity {
    RecyclerView classes_rv, graph_rv;
    ArrayList<Study> messages;
    StudyAdapter messagingAdapter;
    StudyAdapterGraph studyAdapterGraph;

    @SuppressLint("NewApi")
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

        givepermissionaccess();

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


        Calendar cal2 = Calendar.getInstance();
        Intent intent1 = new Intent(Splash.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Splash.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) Splash.this.getSystemService(Splash.this.ALARM_SERVICE);
        //am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        //am.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis()+3000, pendingIntent);
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), 10000, pendingIntent);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED
                && grantResults[4] == PackageManager.PERMISSION_GRANTED && grantResults[5] == PackageManager.PERMISSION_GRANTED) {
            //resume tasks needing this permission
            givepermissionaccess();
            //  Toast.makeText(getBaseContext(), "Resume Task needing this permission", Toast.LENGTH_SHORT).show();
        } else {
            //finish();
            Toast.makeText(getBaseContext(), "you can not use this application without givivng access to ur location Thanks!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void givepermissionaccess() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                ) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.READ_PHONE_STATE}, 0);
        } else {
            Toast.makeText(getBaseContext(), "All permissions granted.", Toast.LENGTH_SHORT).show();

        }
    }
}

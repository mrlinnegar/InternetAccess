package com.linnegar.james.internetaccess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final String REQUEST_TAG = "TRAINS";
    private static final String url = "https://trains.mcrlab.co.uk/departures/NMC/MAN";

    private RequestQueue mRequestQueue;

    private JsonObjectRequest jsonRequest;
    private ServiceList serviceList = new ServiceList();

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button button = findViewById(R.id.get_button);
        textView = findViewById(R.id.get_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRequest();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_refresh_services:
                handleRequest();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void handleRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        textView.setText("Loading...");

        jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG,"success");
                try {
                    serviceList.clear();
                    JSONArray array = response.getJSONArray("departures");
                    for(int i = 0; i < array.length(); i++) {
                        JSONObject serviceData = array.getJSONObject(i);
                        TrainService train = ServiceFactory.createService(serviceData);
                        serviceList.addService(train);
                    }

                    textView.setText(serviceList.toString());
                } catch(JSONException e){
                    textView.setText("Error");
                    Log.i(TAG, "Error "+ e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "fail");
            }
        });


        jsonRequest.setTag(REQUEST_TAG);
        mRequestQueue.add(jsonRequest);


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(REQUEST_TAG);
        }
    }
}

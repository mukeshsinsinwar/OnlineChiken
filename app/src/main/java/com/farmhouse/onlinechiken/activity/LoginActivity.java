package com.farmhouse.onlinechiken.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.farmhouse.onlinechiken.R;
import com.farmhouse.onlinechiken.connections.VolleyManager;
import com.farmhouse.onlinechiken.webrequest.GetRequest;
import com.farmhouse.onlinechiken.webrequest.PostRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static java.util.Arrays.sort;

public class LoginActivity extends AppCompatActivity {

    String s = "";
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        final int arr[]={0,3,3,7,5,3,11,1};

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = solution(arr);
                System.out.println(val+"");
                login.setText(""+val);
            }
        });

    }

    private void requestGet(String authToken) {
        final ProgressDialog pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        VolleyManager.getInstance(LoginActivity.this).addRequest(new GetRequest(authToken,"http://URL", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseJsonObj = new JSONObject(response);


                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    pDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String s = "";
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(LoginActivity.this, "Please check internet connection", Toast.LENGTH_LONG).show();
                } else {

                    s = new String(error.networkResponse.data);
                }

                try {
                    JSONObject responseJsonObj = new JSONObject(s);
                    if (responseJsonObj.has("message")) {
                        Toast.makeText(LoginActivity.this, responseJsonObj.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    pDialog.dismiss();
                }


            }
        }));
    }

    public void requestPost(JSONObject jsonObject) {

        final ProgressDialog pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        VolleyManager.getInstance(LoginActivity.this).addRequest(new PostRequest("token","https:url", jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject res) {
                Log.d("mukesh", "call");



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String s = "";
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(LoginActivity.this, "Please check internet connection", Toast.LENGTH_LONG).show();
                } else {

                    s = new String(error.networkResponse.data);
                }
                Log.d("mukesh", "call");

                try {
                    JSONObject responseJsonObj = new JSONObject(s);
                    Toast.makeText(LoginActivity.this, responseJsonObj.getJSONObject("error").getString("message"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pDialog.dismiss();
            }
        }));
    }

    public int solution(int[] A) {

        if (A.length == 1){
            return -2;
        }

       // sort(A);


        for(int i = 0 ; i < A.length;i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }




        long minDistance = Long.MAX_VALUE;
       for(int i =1; i<A.length ; i++){
            long distance = (long) A[i] - A[i-1];
            if(distance<minDistance){
                minDistance = distance;
            }
        }


        return minDistance>100000000 ?-1 :(int) minDistance;
        // write your code in Java SE 8
    }
    public int solutions(int[] A) {

        for(int i = 0 ; i < A.length;i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }

        long minDistance = Long.MAX_VALUE;
        for(int i =1; i<A.length ; i++){
            long distance = (long) A[i] - A[i-1];
            if(distance<minDistance){
                minDistance = distance;
            }
        }

        return minDistance>100000000 ? -1 : (int) minDistance;
        // write your code in Java SE 8
    }
}

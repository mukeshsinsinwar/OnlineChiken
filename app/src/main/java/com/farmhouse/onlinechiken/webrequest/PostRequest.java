package com.farmhouse.onlinechiken.webrequest;

import android.support.v4.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by mukesh on 15/3/18.
 */

public class PostRequest extends JsonObjectRequest {
    Map<String ,String> mHeader = new ArrayMap<>();

    public PostRequest(String token, String url, final JSONObject jsonRequest, final Response.Listener listener, final Response.ErrorListener errorListener) {
        super(Method.POST, url, jsonRequest, listener, errorListener);
        mHeader.put("Accept", "application/json");
        mHeader.put("Content-Type", "application/json");
        mHeader.put("Authorization","Bearer "+token);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeader;
    }

}
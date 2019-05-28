package com.farmhouse.onlinechiken.webrequest;

import android.support.v4.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by mukesh on 29/05/19.
 */

public class GetRequest extends StringRequest {

    Map<String,String> mHeader = new ArrayMap<>();

    public GetRequest(String token, String url , Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, url, listener, errorListener);
        mHeader.put("Accept", "application/json");
        mHeader.put("Content-Type", "application/json");
        mHeader.put("Authorization","Bearer "+token);

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeader;
    }
}

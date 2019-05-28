package com.farmhouse.onlinechiken.connections;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by mukesh on 30/01/18.
 */

public class VolleyManager {
    private static VolleyManager ourInstance;
    private final Context mContext;
    private final ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;
    public static synchronized VolleyManager getInstance(final Context pContext) {
        if (ourInstance == null) {
            ourInstance = new VolleyManager(pContext);
        }
        return ourInstance;
    }

    private VolleyManager(final Context pContext){
        mContext = pContext;
        mImageLoader = new ImageLoader(getRequestQueue(), new ImageLoader.ImageCache(){

            private LruCache mCache = new LruCache<>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public void addRequest(final Request pRequest) {
        getRequestQueue().add(pRequest);
        pRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}

package com.example.android_cw2;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocketFactory;





public class MySingleton {
    public static final String TAG = MyApplication.class
            .getSimpleName();
    private static MySingleton mInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private MySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    public static MySingleton getInstance() {
        if (mInstance == null) {
            mInstance = new MySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addRequest(Request<T> req) {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLSv1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslcontext.init(null, null, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());

        HttpsURLConnection.setDefaultSSLSocketFactory(NoSSLv3Factory);


        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslContext.init(null, null, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        SSLEngine engine = sslContext.createSSLEngine();

        int socketTimeout = 30000; //30 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(new DefaultRetryPolicy(0, -1, 0));
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}

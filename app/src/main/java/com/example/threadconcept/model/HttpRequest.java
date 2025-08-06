package com.example.threadconcept.model;

import android.util.Log;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequest {
    public static final OkHttpClient client = new OkHttpClient();
    public static String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Log.d("HTTPGetRequest::",request.toString());
        Response response = client.newCall(request).execute();
        Log.d("HTTPResponse::", String.valueOf(response.code()));
        try (response) {
            String data = response.body().string();
            Log.d("HTTPResponse::",data);
            return data;
        }
    }
}

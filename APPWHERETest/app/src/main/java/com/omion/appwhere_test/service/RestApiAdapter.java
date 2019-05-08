package com.omion.appwhere_test.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {
    public EndPointsRestApi establecerConexionServidor(){

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        //setup cache
        //File httpCacheDirectory = new File("Android/data/com.omion.appwhere_test/files/Pictures", "responses");
        //int cacheSize = 1024 * 1024 * 1024; // 10 MiB
        //Cache cache = new Cache(httpCacheDirectory, cacheSize);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MINUTES)
                //.cache(cache)
                .writeTimeout(5,TimeUnit.MINUTES)
                .connectTimeout(5,TimeUnit.MINUTES)
                ;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestAPI.URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();
        return retrofit.create(EndPointsRestApi.class);
    }
}

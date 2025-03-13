package com.example.bookinghotel.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.lang.ref.Cleaner;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.31.84/hotelApp/";
    // Change IP if needed

    public static Retrofit getRetrofitInstance() {
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES) // 10 min timeout
                .readTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create()) // For plain text response
                    .addConverterFactory(GsonConverterFactory.create()) // For JSON response
                    .build();
        }
        return retrofit;
    }
}

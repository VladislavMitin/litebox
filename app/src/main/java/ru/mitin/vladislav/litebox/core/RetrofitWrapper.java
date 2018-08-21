package ru.mitin.vladislav.litebox.core;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWrapper {
    private RetrofitWrapper(){}

    public static <T> T getService(Class<T> serviceInterface) {
        return RetrofitHolder.instance.create(serviceInterface);
    }

    private static class RetrofitHolder {
        private final static Retrofit instance = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.googleapis.com/")
                .build();
    }
}

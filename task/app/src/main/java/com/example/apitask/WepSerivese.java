package com.example.apitask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
// try
public class WepSerivese {
    private static Retrofit retrofit=null;
    public static Retrofit getRetrofit(){
        if (retrofit==null){
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("https://el-warsha.herokuapp.com/api/");
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit= builder
                    .build();
        }
        return retrofit;
    }
}

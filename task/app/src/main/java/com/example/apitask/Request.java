package com.example.apitask;

import com.example.apitask.modl.Result;
import com.example.apitask.modl.SignUp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Request {
    @POST("register")
    @FormUrlEncoded
    Call<SignUp> register(@Field("first_name") String UserFname,
                          @Field("last_name") String UserLname,
                          @Field("email") String UserEmail,
                          @Field("password") String UserPass);
    @POST("login")
    @FormUrlEncoded
    Call<SignUp> login(@Field("email") String UserEmail,
                       @Field("password") String UserPass);
}

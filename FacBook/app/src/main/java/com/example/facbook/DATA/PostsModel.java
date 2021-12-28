package com.example.facbook.DATA;



import com.example.facbook.Pojo.FaceModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsModel {
    @GET("/posts")
    public Call<List<FaceModel>> getPosts();
}

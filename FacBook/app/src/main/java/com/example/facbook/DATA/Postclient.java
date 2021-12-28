package com.example.facbook.DATA;

import com.example.facbook.Pojo.FaceModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Postclient {
    private static final String BASE_URL="https://jsonplaceholder.typicode.com";

    private PostsModel postsModel;
    private  static Postclient INATAMCE;

    public Postclient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postsModel=retrofit.create(PostsModel.class);
    }

    public static Postclient getINATAMCE() {
        if (INATAMCE==null){
            INATAMCE=new Postclient();
        }
        return INATAMCE;
    }
    public Call<List<FaceModel>> getPosts(){
        return postsModel.getPosts();
    }
}

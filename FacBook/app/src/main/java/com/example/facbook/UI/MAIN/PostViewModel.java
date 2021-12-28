package com.example.facbook.UI.MAIN;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.facbook.DATA.Postclient;
import com.example.facbook.Pojo.FaceModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostViewModel extends ViewModel {
    MutableLiveData<List<FaceModel>> viewMutableDataLive = new MutableLiveData<>();
    private static final String TAG = "PostViewModel";

    public void getPosts() {
    Postclient.getINATAMCE().getPosts().enqueue(new Callback<List<FaceModel>>() {
        @Override
        public void onResponse(Call<List<FaceModel>> call, Response<List<FaceModel>> response) {
        viewMutableDataLive.setValue(response.body());
        }

        @Override
        public void onFailure(Call<List<FaceModel>> call, Throwable t) {
     Log.v("massage",t.getMessage());
        }
    });

    }
}
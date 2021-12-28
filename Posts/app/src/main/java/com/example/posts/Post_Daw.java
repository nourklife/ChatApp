package com.example.posts;

import android.database.Observable;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface Post_Daw {
    @Insert
    Completable insert(Post post);
    @Query("select * from posts_table ")
    Single<List<Post>> getPost();
}

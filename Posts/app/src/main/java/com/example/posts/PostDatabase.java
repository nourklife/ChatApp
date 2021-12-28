package com.example.posts;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Post.class,version = 2)
 abstract class PostDatabase extends RoomDatabase {
    private  static PostDatabase instance;
    public abstract Post_Daw postDaw();
    static synchronized PostDatabase getInstance(Context context){
        if (instance==null){


            instance= Room.databaseBuilder(context.getApplicationContext(),
                    PostDatabase.class, "Database_name")
                    .fallbackToDestructiveMigration()
                    .build();
        }

       return instance;
    }
}

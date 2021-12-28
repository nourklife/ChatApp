package com.example.posts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdpter extends RecyclerView.Adapter<PostAdpter.PostHolder> {
   ArrayList<Post> mList=new ArrayList<>();


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        PostHolder postHolder=new PostHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
holder.mTitle.setText(mList.get(position).getTitle());
holder.mBody.setText(mList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setmList(ArrayList<Post> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
 TextView mTitle,mBody;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.title);
            mBody=itemView.findViewById(R.id.body);
        }
    }
}

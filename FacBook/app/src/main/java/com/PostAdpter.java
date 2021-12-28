package com;

import android.media.FaceDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facbook.Pojo.FaceModel;
import com.example.facbook.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdpter extends RecyclerView.Adapter<PostAdpter.PostHolder> {

    ArrayList<FaceModel> mList = new ArrayList<>();


    public void setmList(ArrayList<FaceModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        PostHolder postHolder=new PostHolder(view);
        return postHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.mTitle.setText(mList.get(position).getTitle());
        holder.mBody.setText(mList.get(position).getBody());
        holder.mUserId.setText(mList.get(position).getUserId());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
TextView mUserId,mTitle,mBody;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            mUserId=itemView.findViewById(R.id.userid);
            mBody=itemView.findViewById(R.id.body);
            mTitle=itemView.findViewById(R.id.titeltextView);
        }
    }
}

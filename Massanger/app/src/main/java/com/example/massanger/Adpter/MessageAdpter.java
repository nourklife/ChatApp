package com.example.massanger.Adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.massanger.MasageActivity;
import com.example.massanger.R;
import com.example.massanger.model.Chat;
import com.example.massanger.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdpter extends RecyclerView.Adapter<MessageAdpter.HolderUser> {
    public static final int messageTypeLift = 0;
    public static final int messageTypeRight = 1;

    List<Chat> mList;
    Context mContext;
    String imageUrl;
    FirebaseUser firebaseUser;

    public MessageAdpter(List<Chat> mList, Context mContext, String magUrl) {
        this.mList = mList;
        this.mContext = mContext;
        this.imageUrl = magUrl;
    }

    @NonNull
    @Override
    public MessageAdpter.HolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == messageTypeRight) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chats_right, parent, false);
            return new MessageAdpter.HolderUser(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_items, parent, false);
            return new MessageAdpter.HolderUser(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdpter.HolderUser holder, final int position) {
Chat chat=mList.get(position);
holder.ShowMessage.setText(chat.getMessage());
if (imageUrl.equals("default")){
    holder.mItemImage.setImageResource(R.mipmap.ic_launcher);
}else {
    Glide.with(mContext).load(imageUrl).into(holder.mItemImage);
    }
if (position==mList.size()-1){
    if (chat.isIsseen()){
        holder.text_seen.setText("seen");
    }else {
        holder.text_seen.setText("Delivered");
    }


}else {
    holder.text_seen.setVisibility(View.GONE);
}
    }





    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HolderUser extends RecyclerView.ViewHolder {
        ImageView mItemImage;
        TextView  ShowMessage;
        public TextView text_seen;

        public HolderUser(@NonNull View itemView) {
            super(itemView);
            mItemImage = itemView.findViewById(R.id.profile_image);
            ShowMessage = itemView.findViewById(R.id.show_message);
            text_seen = itemView.findViewById(R.id.text_seen);


        }

    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (mList.get(position).getSender().equals(firebaseUser.getUid())){
            return messageTypeRight;
        }
        else return messageTypeLift;
    }
}
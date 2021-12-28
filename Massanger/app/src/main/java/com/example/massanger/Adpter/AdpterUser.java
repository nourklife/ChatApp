package com.example.massanger.Adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.massanger.R;
import com.example.massanger.MasageActivity;
import com.example.massanger.model.Chat;
import com.example.massanger.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdpterUser extends RecyclerView.Adapter<AdpterUser.HolderUser> {

    List<User> mList;
    Context mContext;
    boolean isChat;
    String last_Message;

    public AdpterUser(List<User> mList, Context mContext,boolean isChat) {
        this.mList = mList;
        this.mContext = mContext;
        this.isChat =  isChat;
    }

    @NonNull
    @Override
    public HolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);

        return new AdpterUser.HolderUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderUser holder, final int position) {
        User user=mList.get(position);
    holder.mUsername.setText(user.getUserName());

        if (user.getImageUrl().equals("default")) {
             holder.mItemImage.setImageResource(R.mipmap.ic_launcher);

      } else {
             Glide.with(mContext).load(user.getImageUrl()).into(holder.mItemImage);

              }
        if(isChat){
            last_message(user.getId(),holder.last_msg);
        }else{
            holder.last_msg.setVisibility(View.GONE);
        }
        if(isChat){
            if(user.getStatus().equals("online")){
                holder.img_on.setVisibility(View.VISIBLE);
                holder.img_of.setVisibility(View.GONE);
            }else {
                holder.img_on.setVisibility(View.GONE);
                holder.img_of.setVisibility(View.VISIBLE);
            }
        }else {
            holder.img_on.setVisibility(View.GONE);
            holder.img_of.setVisibility(View.GONE);
        }
        holder.mUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MasageActivity.class);
                intent.putExtra("userid", mList.get(position).getId());
                mContext.startActivity(intent);
            }
        });
}


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HolderUser extends RecyclerView.ViewHolder {
        ImageView mItemImage;
        TextView mUsername;
        ImageView img_on;
        ImageView img_of;
        TextView last_msg;

        public HolderUser(@NonNull View itemView) {
            super(itemView);
            mItemImage=itemView.findViewById(R.id.profile_image);
            mUsername=itemView.findViewById(R.id.user_name);
            img_on=itemView.findViewById(R.id.im_on);
            img_of=itemView.findViewById(R.id.im_of);
            last_msg=itemView.findViewById(R.id.last_msg);


        }
    }

    public static class TabAdpter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
        ArrayList<String> mFragmentName=new ArrayList<>();
        public TabAdpter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }
       public void addFragment(Fragment fragment,String title){
            fragmentArrayList.add(fragment);
            mFragmentName.add(title);

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentName.get(position);

        }
    }
    public void last_message(final String userid, final TextView mlast_message){
     last_Message="default";
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Chat chat=dataSnapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(firebaseUser.getUid()) &&chat.getSender().equals(userid)
                    ||chat.getReceiver().equals(userid) &&chat.getSender().equals(firebaseUser.getUid())){
                        last_Message=chat.getMessage();
                    }
                }
    switch (last_Message){
        case "default":
            mlast_message.setText("no message");
            break;
        default:
            mlast_message.setText(last_Message);
            break;
}
                last_Message="default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

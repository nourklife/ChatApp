package com.example.apitask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_Adpter extends RecyclerView.Adapter<Item_Adpter.ItemHolder> {
    ArrayList<String> mList;
    Context mContext;

    public Item_Adpter(ArrayList<String> mList6, Context mContext) {
        this.mList = mList6;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home,parent,false);
        ItemHolder itemHolder=new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        ImageView mItemImage;
        TextView mItemType,mIemPrice;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mItemImage=itemView.findViewById(R.id.image_item);
            mIemPrice=itemView.findViewById(R.id.item_price);
            mItemType=itemView.findViewById(R.id.item_type);
         
        }
    }
}

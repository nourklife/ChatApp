package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdpter extends RecyclerView.Adapter<NotesAdpter.HolderNotes> {
    ArrayList<NotesModel> mList;
    Context mContext;

    public NotesAdpter(ArrayList<NotesModel> mList6, Context mContext) {
        this.mList = mList6;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HolderNotes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes, parent, false);
        HolderNotes HolderNotes = new HolderNotes(view);
        return HolderNotes;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNotes holder, int position) {
holder.mTitle.setText(mList.get(position).getTital());
holder.mDiscrubtion.setText(mList.get(position).getDescriptions());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HolderNotes extends RecyclerView.ViewHolder {
      TextView mTitle,mDiscrubtion;

        public HolderNotes(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.tital_not);
            mDiscrubtion=itemView.findViewById(R.id.desc_not);


        }
    }
}

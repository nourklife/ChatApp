package com.example.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class NotesAdpter extends RecyclerView.Adapter<NotesAdpter.notesHolder> {
        ArrayList<NotesModel> arrayList=new ArrayList<>();
        Context context;

        public NotesAdpter(ArrayList<NotesModel> arrayList, Context context) {
            this.arrayList = arrayList;
            this.context = context;
        }

        public notesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes,parent,false);
            notesHolder notesHolder=new notesHolder(view);
            return notesHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull notesHolder holder, int position) {
            holder.mDesc.setText(arrayList.get(position).getDescrption());
            holder.mTital.setText(arrayList.get(position).getTital());
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class notesHolder extends RecyclerView.ViewHolder {

            TextView mTital,mDesc;
            public notesHolder(@NonNull View itemView) {
                super(itemView);
                mTital=itemView.findViewById(R.id.tit);
                mDesc=itemView.findViewById(R.id.desc);
            }
        }
    }



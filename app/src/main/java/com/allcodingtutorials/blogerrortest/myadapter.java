package com.allcodingtutorials.blogerrortest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends  RecyclerView.Adapter <myadapter.myviewHolder> {
private OnNoteListener mOnNoteListener;

    public myadapter(ArrayList<model> dataholder,OnNoteListener mOnNoteListener)
    {
        this.dataholder = dataholder;
        this.mOnNoteListener= mOnNoteListener;
    }

    ArrayList <model> dataholder;
    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myviewHolder(view,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {
        holder.title.setText(dataholder.get(position).getTitle());
        holder.datercvr.setText(dataholder.get(position).getDatercv());
        holder.description.setText(dataholder.get(position).getDescription());



    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,description,imgtext,datercvr;
        OnNoteListener onNoteListener;
        public myviewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            title=itemView.findViewById(R.id.titleget);
            description=itemView.findViewById(R.id.descget);


            datercvr=itemView.findViewById(R.id.datetxtdisplay);
            this.onNoteListener=onNoteListener;
itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
 void onNoteClick(int position);
    }
}

package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<taskitem> mtaskitemList;

    public RecyclerViewAdapter(Context context, List<taskitem> mtaskitemList){
        this.context = context;
        this.mtaskitemList = mtaskitemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.single_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titletxt.setText(mtaskitemList.get(position).getTitle());
        holder.datetxt.setText(mtaskitemList.get(position).getDate());
        holder.timetxt.setText(mtaskitemList.get(position).getTime());
        holder.descriptiontxt.setText(mtaskitemList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mtaskitemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titletxt,datetxt,timetxt,descriptiontxt;
        private CardView mainCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            titletxt = itemView.findViewById(R.id.title_text);
            datetxt = itemView.findViewById(R.id.date_text);
            timetxt = itemView.findViewById(R.id.time_text);
            descriptiontxt = itemView.findViewById(R.id.description_text);
            mainCard = itemView.findViewById(R.id.mainCard);
        }

        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            taskitem item = mtaskitemList.get(pos);

            Intent detailsScreenData = new Intent(context,Detail.class);

            detailsScreenData.putExtra("Task", item.getTask());
            detailsScreenData.putExtra("Title", item.getTitle());
            detailsScreenData.putExtra("Date", item.getDate());
            detailsScreenData.putExtra("Time", item.getTime());
            detailsScreenData.putExtra("Description", item.getDescription());

            detailsScreenData.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(detailsScreenData);
        }
    }
}

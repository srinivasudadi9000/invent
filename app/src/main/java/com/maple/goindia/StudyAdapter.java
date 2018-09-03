package com.maple.goindia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.CheckIn> {

    ArrayList<Study> messagings;
    int Rowlayout;
    Context context;

    public StudyAdapter(ArrayList<Study> messagings, int check_single, Context applicationContext) {
        this.context = applicationContext;
        this.Rowlayout = check_single;
        this.messagings = messagings;
    }


    @Override
    public CheckIn onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(Rowlayout, parent, false);
        return new CheckIn(view);

    }

     @Override
    public void onBindViewHolder(final CheckIn holder, int position) {
        holder.study_tv.setText(messagings.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return messagings.size();
    }

    public class CheckIn extends RecyclerView.ViewHolder {
        TextView study_tv;
        public CheckIn(View itemView) {
            super(itemView);
            study_tv = (TextView) itemView.findViewById(R.id.study_tv);
        }
    }


}

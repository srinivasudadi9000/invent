package com.maple.goindia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class StudyAdapterGraph extends RecyclerView.Adapter<StudyAdapterGraph.CheckIn> {

    ArrayList<Graph_Path> graph_paths;
    int Rowlayout;
    Context context;

    public StudyAdapterGraph(ArrayList<Graph_Path> graph_paths, int check_single, Context applicationContext) {
        this.context = applicationContext;
        this.Rowlayout = check_single;
        this.graph_paths = graph_paths;
    }


    @Override
    public CheckIn onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(Rowlayout, parent, false);
        return new CheckIn(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final CheckIn holder, int position) {
        holder.study_tv.setText(graph_paths.get(position).getClasses());
        if (position % 2 == 0) {

            holder.left_duration.setBackground(context.getResources().getDrawable(R.drawable.year_bg));
            holder.left_duration.setText(graph_paths.get(position).getDuration());

            holder.duration_tv.setBackgroundColor(Color.WHITE);
            holder.duration_tv.setText("");
            if (graph_paths.size()-1 == position){
                holder.duration_tv.setBackground(context.getResources().getDrawable(R.drawable.year_bg));
                holder.duration_tv.setText("Goal Reached");
            }
        } else {
            holder.left_duration.setBackgroundColor(Color.WHITE);
            holder.left_duration.setText("");
            holder.duration_tv.setBackground(context.getResources().getDrawable(R.drawable.year_bg));
            holder.duration_tv.setText(graph_paths.get(position).getDuration());
            if (graph_paths.size()-1 == position){
                holder.left_duration.setBackground(context.getResources().getDrawable(R.drawable.year_bg));
                holder.left_duration.setText("Goal Reached");
            }
        }
    }

    @Override
    public int getItemCount() {
        return graph_paths.size();
    }

    public class CheckIn extends RecyclerView.ViewHolder {
        TextView study_tv, duration_tv, left_duration;

        public CheckIn(View itemView) {
            super(itemView);
            left_duration = (TextView) itemView.findViewById(R.id.left_duration);
            duration_tv = (TextView) itemView.findViewById(R.id.duration_tv);
            study_tv = (TextView) itemView.findViewById(R.id.study_hor_tv);
        }
    }


}

package com.maple.goindia;

import android.content.Context;
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

    @Override
    public void onBindViewHolder(final CheckIn holder, int position) {
        holder.study_tv.setText(graph_paths.get(position).getClasses());
        holder.duration_tv.setText(graph_paths.get(position).getDuration());
    }

    @Override
    public int getItemCount() {
        return graph_paths.size();
    }

    public class CheckIn extends RecyclerView.ViewHolder {
        TextView study_tv, duration_tv;

        public CheckIn(View itemView) {
            super(itemView);
            duration_tv = (TextView) itemView.findViewById(R.id.duration_tv);
            study_tv = (TextView) itemView.findViewById(R.id.study_hor_tv);
        }
    }


}

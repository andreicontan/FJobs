package com.qubiz.fjobs.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;

import java.util.List;

/**
 * Created by Andreea's on 8/23/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Job> jobs;

    public MyAdapter(List<Job> jobList) {
        jobs = jobList;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.mTitleView.setText(job.getTitle());
        holder.mDescriptionView.setText(job.getDescription());

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleView;
        private TextView mDescriptionView;


        private ViewHolder(View v) {
            super(v);
            mTitleView = v.findViewById(R.id.job_title);
            mDescriptionView = v.findViewById(R.id.job_description);

            

        }
    }
}




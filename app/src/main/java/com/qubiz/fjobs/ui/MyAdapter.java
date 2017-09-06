package com.qubiz.fjobs.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.network.JobApiCalls;
import com.qubiz.fjobs.util.ImageLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Andreea's on 8/23/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Job> jobs;
    private Context context;

    public MyAdapter(Context context, List<Job> jobList) {
        this.context = context;
        jobs = jobList;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Job job = jobs.get(position);
        ImageLoader.loadImage(context, job.getPhoto(), holder.jobImageView);
        holder.jobTitleView.setText(job.getTitle());
        holder.jobDescriptionView.setText(job.getDescription());
        holder.jobEstimatedWorkTimeView.setText(context.getString(R.string.job_list_est_work_time, job.getEstimatedTime()));
        holder.jobRewardView.setText(context.getString(R.string.job_list_reward, job.getJobReward()));
        holder.jobDifficultyView.setText(context.getString(R.string.job_list_difficulty, job.getDifficulty()));
        holder.jobDismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobs.remove(position);
                notifyDataSetChanged();
            }
        });
        /*holder.jobApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Applied!!!", Toast.LENGTH_SHORT).show();
            }
        });*/
        holder.ApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobApiCalls.addStudentToJob(job.getId(), "59a534e7e4b01e9cf162556f", new Callback<Job>() {
                    @Override
                    public void onResponse(Call<Job> call, Response<Job> response) {
                        Toast.makeText(context, "Applied!!!", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<Job> call, Throwable t) {
                        Toast.makeText(context, "Cannot apply to this job!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, JobDetailsActivity.class);
                myIntent.putExtra("ID",job.getId());
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView jobImageView;
        private TextView jobTitleView;
        private TextView jobDescriptionView;
        private TextView jobEstimatedWorkTimeView;
        private TextView jobRewardView;
        private TextView jobDifficultyView;
        private TextView jobDismissButton;
        private TextView ApplyButton;

        private ViewHolder(View v) {
            super(v);
            jobImageView = v.findViewById(R.id.job_image_view);
            jobTitleView = v.findViewById(R.id.job_title);
            jobDescriptionView = v.findViewById(R.id.job_description);
            jobEstimatedWorkTimeView = v.findViewById(R.id.job_estimated_work_time);
            jobRewardView = v.findViewById(R.id.job_reward);
            jobDifficultyView = v.findViewById(R.id.job_difficulty);
            jobDismissButton = v.findViewById(R.id.dismiss_button);
            ApplyButton = v.findViewById(R.id.apply_button);
        }
    }

}




package com.qubiz.fjobs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.network.JobApiCalls;
import com.qubiz.fjobs.util.ImageLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andrei on 29.08.2017.
 */

public class JobDetailsActivity extends AppCompatActivity{

    private Job job;
    private TextView Title;
    private TextView Description;
    private TextView City;
    private TextView Address;
    private TextView EstimatedWorkTime;
    private TextView AvailableFrom;
    private TextView ExpiresOn;
    private TextView Difficulty;
    private TextView Reward;
    private ImageView JobImage;
    private Button Apply;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        String id=getIntent().getExtras().getString("ID");
        getJob(id);
    }

    private void getJob(String id) {
        JobApiCalls.getJobByID(id, new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                job=response.body();
                initUIElements();
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {

            }
        });
    }

    private void initUIElements() {
        Title=(TextView) findViewById(R.id.job_title_description);
        Description=(TextView) findViewById(R.id.job_description_description);
        City=(TextView) findViewById(R.id.job_city_description);
        Address=(TextView) findViewById(R.id.job_address_description);
        EstimatedWorkTime=(TextView) findViewById(R.id.job_estimatedWorkTime_description);
        AvailableFrom=(TextView) findViewById(R.id.job_startDate_description);
        ExpiresOn=(TextView) findViewById(R.id.job_endDate_description);
        Difficulty=(TextView) findViewById(R.id.job_difficulty_description);
        Reward=(TextView) findViewById(R.id.job_reward_description);
        JobImage=(ImageView) findViewById(R.id.job_image_view);

        Title.setText(job.getTitle());
        Description.setText(job.getDescription());
        City.setText(job.getCity());
        Address.setText(job.getAddress());
        EstimatedWorkTime.setText(String.valueOf(job.getEstimatedTime()));
        AvailableFrom.setText(job.getStartDate());
        ExpiresOn.setText(job.getEndDate());
        Difficulty.setText(String.valueOf(job.getDifficulty()));
        Reward.setText(job.getJobReward());
        ImageLoader.loadImage(this,job.getPhoto(), JobImage);



    }

}

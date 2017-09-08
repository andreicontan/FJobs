package com.qubiz.fjobs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.network.JobApiCalls;
import com.qubiz.fjobs.util.RefreshContentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowJobsActivity extends AppCompatActivity {

    private RecyclerView jobListView;
    private MyAdapter mAdapter;
    private List<Job> jobList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_show_jobs);
        setTitle(R.string.job_list_title);
        jobListView = (RecyclerView) findViewById(R.id.jobsRV);
        FloatingActionButton createJobButton = (FloatingActionButton) findViewById(R.id.create_job_button);
        createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startJobActivityIntent = new Intent(ShowJobsActivity.this, CreateJobActivity.class);
                startActivity(startJobActivityIntent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        jobListView.setLayoutManager(linearLayoutManager);
        mAdapter= new MyAdapter(this, jobList);
        jobListView.setAdapter(mAdapter);
        getAllJobs();

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshContentEvent event) {
        getAllJobs();
    }


    public void getAllJobs() {
        JobApiCalls.getAllJobs(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.body() != null) {
                    if(jobList != null && !jobList.isEmpty()) {
                        jobList.clear();
                    }
                    jobList.addAll(response.body());
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Toast.makeText(ShowJobsActivity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
            }
        });

    }

}


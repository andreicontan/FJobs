package com.qubiz.fjobs.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.network.JobApiCalls;

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
        setContentView(R.layout.activity_show_jobs);
        jobListView = (RecyclerView) findViewById(R.id.jobsRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        jobListView.setLayoutManager(linearLayoutManager);
        mAdapter= new MyAdapter(jobList);
        jobListView.setAdapter(mAdapter);
        getAllJobs();

    }


    public void getAllJobs() {
        JobApiCalls.getAllJobs(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.body() != null) {
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


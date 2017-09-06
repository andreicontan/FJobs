package com.qubiz.fjobs.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.data.Student;
import com.qubiz.fjobs.network.JobApiCalls;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pc on 9/4/2017.
 */

public class AplicantsActivity extends AppCompatActivity {
    private RecyclerView aplicantsListView;
    private AplicantAdapter mAdapter;
    private List<Student> studentList = new ArrayList<>();
    private Job job;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_aplicants);
        setTitle(R.string.list_of_applicans_title);
        aplicantsListView = (RecyclerView) findViewById(R.id.RV_of_aplicants);
        String id=getIntent().getExtras().getString("ID");
        setData();
        getJob(id);


        //FloatingActionButton createJobButton = (FloatingActionButton) findViewById(R.id.create_job_button);
        /*createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startJobActivityIntent = new Intent(.this, CreateJobActivity.class);
                startActivity(startJobActivityIntent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        aplicantsListView.setLayoutManager(linearLayoutManager);
        mAdapter= new AplicantAdapter(this, studentList);
        aplicantsListView.setAdapter(mAdapter);
        getAllJobs();*/

    }

    private void getJob(String id) {
        JobApiCalls.getJobByID(id, new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                job=response.body();
                if (job.getStudents() != null) {
                    studentList.addAll(job.getStudents());
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {

            }
        });
    }

    private void setData() {
        mAdapter = new AplicantAdapter(this, studentList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        aplicantsListView.setLayoutManager(linearLayoutManager);
        aplicantsListView.setAdapter(mAdapter);
    }


}

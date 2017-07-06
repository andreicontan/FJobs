package com.qubiz.fjobs.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.data.JobReward;
import com.qubiz.fjobs.network.JobApiCalls;
import com.qubiz.fjobs.util.DataUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testPostJob(); //TODO to be removed
        testGetJobs(); //TODO to be removed

    }

    private void testPostJob() {
        JobReward jobReward = new JobReward("bilet untold", "");
        Job testJob = new Job(DataUtils.nextValue(), "description1", "title1", null, "Cluj", "Piata muzeului, nr. 5",
                30, "06.07.2017", "08.07.2017", "09.07.2017", 5, jobReward);

        JobApiCalls.postNewJob(testJob, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MainActivity.this, response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void testGetJobs() {
        JobApiCalls.getAllJobs(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                Toast.makeText(MainActivity.this, "It worked!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "It didn't work :(", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}

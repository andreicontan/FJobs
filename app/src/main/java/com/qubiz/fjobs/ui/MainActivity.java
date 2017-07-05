package com.qubiz.fjobs.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.network.JobApiCalls;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testGetJobs(); //TODO to be removed
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

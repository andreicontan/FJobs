package com.qubiz.fjobs.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button createJobButton = (Button) findViewById(R.id.create_job_button);
        createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startJobActivityIntent = new Intent(MainActivity.this, CreateJobActivity.class);
                startActivity(startJobActivityIntent);
            }
        });

        Button showJobsButton = (Button) findViewById(R.id.show_jobs_button);
        showJobsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showJobsActivityIntent = new Intent(MainActivity.this, ShowJobsActivity.class);
                startActivity(showJobsActivityIntent);
            }
        });
    }
}

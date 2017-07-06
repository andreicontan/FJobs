package com.qubiz.fjobs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.data.JobReward;
import com.qubiz.fjobs.network.JobApiCalls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cameliahotico on 7/6/17.
 */

public class CreateJobActivity extends AppCompatActivity {

    private EditText titleEditText;
    //TODO declare all layout resources

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        initUIElements();

        final Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveJob();
            }
        });

    }

    private void initUIElements() {
        titleEditText = (EditText) findViewById(R.id.title_edit_text);
    }

    private void saveJob() {
        Job job = new Job();
        job.setTitle(titleEditText.getText().toString());

        postJob(job);
    }

    private void postJob(Job job) {
        JobApiCalls.postNewJob(job, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(CreateJobActivity.this, response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(CreateJobActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

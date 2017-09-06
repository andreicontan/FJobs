package com.qubiz.fjobs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Employer;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.network.EmployerApiCalls;
import com.qubiz.fjobs.network.JobApiCalls;
import com.qubiz.fjobs.util.ImageLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andrei on 04.09.2017.
 */

public class EmployerProfileActivity extends AppCompatActivity {

    private Employer employer;
    private TextView Name;
    private TextView Phone;
    private TextView Email;
    private TextView City;
    private TextView Address;
    private ImageView Image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_profile);
        String id=getIntent().getExtras().getString("ID");
        getEmployer(id);
    }

    private void getEmployer(String id) {
        EmployerApiCalls.getEmployerByID(id, new Callback<Employer>() {
            @Override
            public void onResponse(Call<Employer> call, Response<Employer> response) {
                employer=response.body();
                initUIElements();
            }

            @Override
            public void onFailure(Call<Employer> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initUIElements() {
        Name=(TextView) findViewById(R.id.employer_name);
        Phone=(TextView) findViewById(R.id.employer_phone);
        Email=(TextView) findViewById(R.id.employer_email);
        City=(TextView) findViewById(R.id.employer_city);
        Address=(TextView) findViewById(R.id.employer_address);
        Image=(ImageView) findViewById(R.id.employer_image_view);

        Name.setText(employer.getFname()+" "+employer.getLname());
        Phone.setText(employer.getPhone());
        Email.setText(employer.getEmail());
        City.setText(employer.getCity());
        Address.setText(employer.getAddress());
        ImageLoader.loadImage(this,employer.getPhoto(), Image);
    }

}

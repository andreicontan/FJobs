package com.qubiz.fjobs.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.data.JobReward;
import com.qubiz.fjobs.network.JobApiCalls;

import java.util.List;
import java.util.Locale;
import java.util.jar.Manifest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cameliahotico on 7/6/17.
 */

public class CreateJobActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText cityEditText;
    private EditText addressEditText;
    private Spinner daysSpinner;
    private int days;
    private Spinner hoursSpinner;
    private int hours;
    private Spinner minutesSpinner;
    private int minutes;

    Button button;
    TextView textView;
    private static final int MY_PERMISSION_REQUEST_LOCATION=1;


    //TODO declare all layout resources

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void OnClick(View view) {
                if(ContextCompat.checkSelfPermission(CreateJobActivity.this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(CreateJobActivity.this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        ActivityCompat.requestPermissions(CreateJobActivity.this,
                                new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
                    } else {
                        ActivityCompat.requestPermissions(CreateJobActivity.this,
                                new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
                    }
                } else {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try {
                        textView.setText(hereLocation(location.getLatitude(), location.getLongitude()));
                    } catch(Exception e) {
                        e.printStackTrace();
                        Toast.makeText(CreateJobActivity.this, "Not found!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        initUIElements();

        final Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveJob();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions , int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_LOCATION: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(CreateJobActivity.this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        try {
                            textView.setText(hereLocation(location.getLatitude(), location.getLongitude()));
                        } catch(Exception e) {
                            e.printStackTrace();
                            Toast.makeText(CreateJobActivity.this, "Not found!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(this, "No permission granted!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public String hereLocation(double lat, double lon) {
        String ourCity="";

        Geocoder geocoder = new Geocoder(CreateJobActivity.this, Locale.getDefault());
        List<Address> addressList;
        try{
            addressList=geocoder.getFromLocation(lat,lon,1);
            if(addressList.size()>0) {
                ourCity=addressList.get(0).getLocality();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ourCity;
    }

    private void initUIElements() {
        titleEditText = (EditText) findViewById(R.id.title_edit_text);
        descriptionEditText = (EditText) findViewById(R.id.description_edit_text);
        cityEditText = (EditText) findViewById(R.id.city_edit_text);
        addressEditText = (EditText) findViewById(R.id.address_edit_text);
        daysSpinner = (Spinner) findViewById(R.id.daysSpinner);
        days = Integer.valueOf(daysSpinner.getSelectedItem().toString());
        hoursSpinner = (Spinner) findViewById(R.id.hoursSpinner);
        hours = Integer.valueOf(hoursSpinner.getSelectedItem().toString());
        minutesSpinner = (Spinner) findViewById(R.id.minutesSpinner);
        minutes = Integer.valueOf(minutesSpinner.getSelectedItem().toString());

    }

    private void saveJob() {
        Job job = new Job();
        job.setTitle(titleEditText.getText().toString());
        job.setDescription(descriptionEditText.getText().toString());
        job.setCity(cityEditText.getText().toString());
        job.setAddress(addressEditText.getText().toString());
        job.setEstimatedTime(days*1440+hours*60+minutes);
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

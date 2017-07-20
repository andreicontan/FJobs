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
    private RatingBar difficultyBar;
    private float difficulty;
    public DatePickerDialog myDialog;
    Calendar myCalendar = Calendar.getInstance();


    //TODO declare all layout resources

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        final EditText createdDateText = (EditText) findViewById(R.id.createdDate);
        final EditText endDateText = (EditText) findViewById(R.id.endDate);

        createdDateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myDialog = new DatePickerDialog(CreateJobActivity.this, 0, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String date = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                        Toast toast = Toast.makeText(CreateJobActivity.this, date, Toast.LENGTH_SHORT);
                        toast.show();
                        createdDateText.setText(date);
                    }
                },  myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));;
                myDialog.show();
            }
        });

        endDateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myDialog = new DatePickerDialog(CreateJobActivity.this, 0, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String date = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                        Toast toast = Toast.makeText(CreateJobActivity.this, date, Toast.LENGTH_SHORT);
                        toast.show();
                        endDateText.setText(date);
                    }
                },  myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));;
                myDialog.show();
            }
        });

        difficultyBar= (RatingBar) findViewById(R.id.difficultyBar);
        difficultyBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar difficultyBar, float rating, boolean fromUser) {
                Toast.makeText(CreateJobActivity.this, String.valueOf(rating), Toast.LENGTH_SHORT).show();
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
        difficulty = ((RatingBar)findViewById(R.id.difficultyBar)).getRating();

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

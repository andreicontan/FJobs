package com.qubiz.fjobs.ui;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.network.JobApiCalls;

import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cameliahotico on 7/6/17.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
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
    private EditText startDateEditText;
    private EditText endDateEditText;
    private int difficulty;
    private DatePickerDialog myDialog;
    private EditText rewardEditText;
    private Calendar myCalendar = Calendar.getInstance();
    private RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        final EditText startDateText = (EditText) findViewById(R.id.startDate);
        final EditText endDateText = (EditText) findViewById(R.id.endDate);

        startDateText.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                myDialog = new DatePickerDialog(CreateJobActivity.this, 0, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String date = year + "-" + String.format(Locale.getDefault(), "%02d", (monthOfYear + 1)) + "-" + String.format(Locale.getDefault(), "%02d", dayOfMonth);
                        Toast toast = Toast.makeText(CreateJobActivity.this, date, Toast.LENGTH_SHORT);
                        toast.show();
                        startDateText.setText(date);
                    }
                },  myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                myDialog.show();
            }
        });

        endDateText.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                myDialog = new DatePickerDialog(CreateJobActivity.this, 0, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String date = year + "-" + String.format(Locale.getDefault(), "%02d", (monthOfYear + 1)) + "-" + String.format(Locale.getDefault(), "%02d", dayOfMonth);
                        Toast toast = Toast.makeText(CreateJobActivity.this, date, Toast.LENGTH_SHORT);
                        toast.show();
                        endDateText.setText(date);
                    }
                },  myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
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

        hoursSpinner = (Spinner) findViewById(R.id.hoursSpinner);

        minutesSpinner = (Spinner) findViewById(R.id.minutesSpinner);

        ratingBar = ((RatingBar)findViewById(R.id.difficultyBar));
        startDateEditText= (EditText) findViewById(R.id.startDate);
        endDateEditText= (EditText) findViewById(R.id.endDate);
        rewardEditText = (EditText) findViewById(R.id.reward);



    }

    private void saveJob() {
        Job job = new Job();
        job.setTitle(titleEditText.getText().toString());
        job.setDescription(descriptionEditText.getText().toString());
        job.setCity(cityEditText.getText().toString());
        job.setAddress(addressEditText.getText().toString());
        if (daysSpinner.getSelectedItem().toString().equals("Days")) {
            days = 0;
        } else {
            days = Integer.valueOf(daysSpinner.getSelectedItem().toString());
        }
        if (hoursSpinner.getSelectedItem().toString().equals("Hours")) {
            hours = 0;
        } else {
            hours = Integer.valueOf(hoursSpinner.getSelectedItem().toString());
        }
        if (minutesSpinner.getSelectedItem().toString().equals("Mins")) {
            minutes = 0;
        } else {
            minutes = Integer.valueOf(minutesSpinner.getSelectedItem().toString());
        }
        job.setEstimatedTime(days*1440+hours*60+minutes);
        job.setStartDate(startDateEditText.getText().toString());
        job.setEndDate(endDateEditText.getText().toString());
        difficulty = ratingBar.getProgress();
        job.setDifficulty(difficulty);
        job.setJobReward(rewardEditText.getText().toString());
        postJob(job);
    }

    private void postJob(Job job) {
        JobApiCalls.postNewJob(job, new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                Toast.makeText(CreateJobActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                CreateJobActivity.this.finish();
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(CreateJobActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

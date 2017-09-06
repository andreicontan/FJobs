package com.qubiz.fjobs.ui;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.network.JobApiCalls;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
    private EditText hoursEditText;
    private int hours;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private int difficulty;
    private DatePickerDialog myDialog;
    private EditText rewardEditText;
    private Calendar myCalendar = Calendar.getInstance();
    private EditText difficultyEditText;
    private Map<String, Integer> hoursMap = new HashMap<>();
    private Map<String, Integer> difficultyMap = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);
        populateHoursMap();
        populateDifficultyMap();

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
        hoursEditText = (EditText) findViewById(R.id.estimated_work_time_edit_text );
        hoursEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(hoursEditText, R.menu.hours_menu, new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        hours = hoursMap.get(menuItem.getTitle().toString());
                        hoursEditText.setText(menuItem.getTitle());
                        return true;
                    }
                });
            }
        });
        difficultyEditText = ((EditText)findViewById(R.id.difficulty_edit_text));
        difficultyEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(difficultyEditText, R.menu.difficulty_menu, new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        difficulty = difficultyMap.get(menuItem.getTitle().toString());
                        difficultyEditText.setText(menuItem.getTitle());
                        return true;
                    }
                });
            }
        });
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
        job.setEstimatedTime(hours);
        job.setDifficulty(difficulty);
        job.setStartDate(startDateEditText.getText().toString());
        job.setEndDate(endDateEditText.getText().toString());
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

    public void showPopup(View view, int menuRes, PopupMenu.OnMenuItemClickListener listener) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(menuRes, popup.getMenu());
        popup.setOnMenuItemClickListener(listener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popup.setGravity(Gravity.RIGHT);
        }
        popup.show();
    }

    private void populateHoursMap() {
        for (int i = 1; i < 9; i++) {
            if (i == 1) {
                hoursMap.put("1 hour", 1);
            } else {
                hoursMap.put(i + " hours", i);
            }
        }
    }

    private void populateDifficultyMap() {
        difficultyMap.put(Job.VERY_LOW_DIFFICULTY, 1);
        difficultyMap.put(Job.LOW_DIFFICULTY, 2);
        difficultyMap.put(Job.MEDIUM_DIFFICULTY, 3);
        difficultyMap.put(Job.TOUGH_DIFFICULTY, 4);
        difficultyMap.put(Job.HARD_CORE_DIFFICULTY, 5);
    }

}

package com.qubiz.fjobs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Employer;
import com.qubiz.fjobs.data.Student;
import com.qubiz.fjobs.network.EmployerApiCalls;
import com.qubiz.fjobs.network.StudentApiCalls;
import com.qubiz.fjobs.util.ImageLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andrei on 06.09.2017.
 */

public class StudentProfileActivity extends AppCompatActivity {

    private Student student;
    private TextView Name;
    private TextView Phone;
    private TextView Email;
    private TextView City;
    private TextView Age;
    private TextView Gender;
    private TextView Skills;
    private ImageView Image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        String id=getIntent().getExtras().getString("ID");
        getStudent(id);
    }

    private void getStudent(String id) {
        StudentApiCalls.getStudentByID(id, new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                student=response.body();
                initUIElements();
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initUIElements() {
        Name=(TextView) findViewById(R.id.student_name);
        Phone=(TextView) findViewById(R.id.student_phone);
        Email=(TextView) findViewById(R.id.student_email);
        City=(TextView) findViewById(R.id.student_city);
        Age=(TextView) findViewById(R.id.student_age);
        Gender=(TextView) findViewById(R.id.student_gender);
        Skills=(TextView) findViewById(R.id.student_skills);
        Image=(ImageView) findViewById(R.id.student_image_view);

        Name.setText(student.getFname()+" "+student.getLname());
        Phone.setText(student.getPhone());
        Email.setText(student.getEmail());
        City.setText(student.getCity());
        Age.setText(String.valueOf(student.getAge()));
        Gender.setText(student.getGender());
        Skills.setText(student.getEarnedSkills());
        ImageLoader.loadImage(this,student.getPhoto(), Image);
    }

}

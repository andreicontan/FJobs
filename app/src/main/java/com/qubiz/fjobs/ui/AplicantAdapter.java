package com.qubiz.fjobs.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qubiz.fjobs.R;
import com.qubiz.fjobs.data.Student;
import com.qubiz.fjobs.util.ImageLoader;

import java.util.List;

/**
 * Created by Pc on 9/4/2017.
 */
public class AplicantAdapter extends RecyclerView.Adapter<AplicantAdapter.ViewHolder> {
    private List<Student> students;
    private Context context;

    public AplicantAdapter(Context context, List<Student> AplicantsList) {
        this.context = context;
        students = AplicantsList;
    }

    @Override
    public AplicantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_profile, parent, false);
        AplicantAdapter.ViewHolder vh = new AplicantAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Student student = students.get(position);
        ImageLoader.loadImage(context, student.getPhoto(), holder.studentImageView);
        holder.studentName.setText(student.getFname() + " " + student.getLname());
        holder.studentPhone.setText(student.getPhone());
        holder.studentEmail.setText(student.getEmail());
        holder.studentCity.setText(student.getCity());
        holder.studentAge.setText(context.getString(R.string.student_age, student.getAge()));
        holder.studentSkills.setText(student.getEarnedSkills());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, StudentProfileActivity.class);
                myIntent.putExtra("ID",student.getId());
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView studentImageView;
        private TextView studentName;
        private TextView studentPhone;
        private TextView studentEmail;
        private TextView studentCity;
        private TextView studentAge;
        private TextView studentSkills;


        private ViewHolder(View v) {
            super(v);
            studentImageView = v.findViewById(R.id.student_image_view);
            studentName = v.findViewById(R.id.student_name);
            studentPhone=v.findViewById(R.id.student_phone);
            studentEmail=v.findViewById(R.id.student_email);
            studentCity=v.findViewById(R.id.student_city);
            studentAge=v.findViewById(R.id.student_age);
            studentSkills=v.findViewById(R.id.student_skills);
        }
    }
}

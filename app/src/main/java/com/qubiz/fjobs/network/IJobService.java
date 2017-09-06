package com.qubiz.fjobs.network;

import com.qubiz.fjobs.data.ApplyJobRequestBody;
import com.qubiz.fjobs.data.Job;
import com.qubiz.fjobs.data.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface IJobService {

    @GET("jobs")
    Call<List<Job>> getAllJobs();

    @POST("jobs")
    Call<Job> postJob(@Body Job job);

    @GET("jobs/{jobId}")
    Call<Job> getJobId(@Path("jobId") String jobId);

    @PATCH("jobs/{jobId}/students")
    Call<String> addStudentToJob(@Path("jobId") String jobId, @Body ApplyJobRequestBody studentIdBody);

}

package com.qubiz.fjobs.network;

import com.qubiz.fjobs.data.Job;

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

    @PATCH("jobs/{jobId}/students/{studentId}")
    Call<Job> addStudentToJob(@Path("jobId") String jobId, @Path("studentId") String studentId);

}

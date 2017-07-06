package com.qubiz.fjobs.network;

import com.qubiz.fjobs.data.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface IJobService {

    @GET("jobs")
    Call<List<Job>> getAllJobs();

    @POST("jobs")
    Call<String> postJob(@Body Job job);

}

package com.qubiz.fjobs.network;

import com.qubiz.fjobs.data.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface IJobService {
    @GET("jobs")
    Call<List<Job>> getAllJobs();
}

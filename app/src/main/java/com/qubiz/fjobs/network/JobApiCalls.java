package com.qubiz.fjobs.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.qubiz.fjobs.BuildConfig;
import com.qubiz.fjobs.data.Job;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class JobApiCalls {
    private static final String BASE_URL = "https://virtserver.swaggerhub.com/FJobs/Start_App/1.0.0/";
    private static Retrofit retrofit;
    private static IJobService jobService;
    private static OkHttpClient okHttpClient;

    private static OkHttpClient getHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
            }
            okHttpClient = okHttpClientBuilder.build();
        }

        return okHttpClient;
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                    .client(getHttpClient())
                    .build();
        }

        return retrofit;
    }

    private static IJobService getJobService() {
        if (jobService == null) {
            jobService = getRetrofit().create(IJobService.class);
        }

        return jobService;
    }


    public static void getAllJobs(Callback<List<Job>> requestCallback) {
        getJobService().getAllJobs().enqueue(requestCallback);
    }


}

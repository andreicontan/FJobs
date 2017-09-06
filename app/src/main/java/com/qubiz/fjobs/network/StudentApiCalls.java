package com.qubiz.fjobs.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.qubiz.fjobs.BuildConfig;
import com.qubiz.fjobs.data.Student;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrei on 06.09.2017.
 */

public class StudentApiCalls {
    private static final String BASE_URL = "https://quiet-river-94387.herokuapp.com/";
    private static Retrofit retrofit;
    private static IStudentService studentService;
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

    private static IStudentService getStudentService() {
        if (studentService == null) {
            studentService = getRetrofit().create(IStudentService.class);
        }

        return studentService;
    }

    public static void getStudentByID(String studentId, Callback<Student> requestCallback){
        getStudentService().getStudentId(studentId).enqueue(requestCallback);
    }
}

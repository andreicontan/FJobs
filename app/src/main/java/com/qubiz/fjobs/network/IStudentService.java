package com.qubiz.fjobs.network;

import com.qubiz.fjobs.data.Student;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by andrei on 06.09.2017.
 */

public interface IStudentService {

    @GET("students/{studentId}")
    Call<Student> getStudentId(@Path("studentId") String studentId);

}

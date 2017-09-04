package com.qubiz.fjobs.network;

import com.qubiz.fjobs.data.Employer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by andrei on 04.09.2017.
 */

public interface IEmployerService {

    @GET("employers/{employerId}")
    Call<Employer> getEmployerId(@Path("employerId") String employerId);

}

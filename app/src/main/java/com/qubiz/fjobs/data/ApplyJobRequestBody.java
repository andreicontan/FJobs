package com.qubiz.fjobs.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andrei on 06.09.2017.
 */

public class ApplyJobRequestBody {
    @SerializedName("studentId")
    private String studentId;

    public ApplyJobRequestBody(String studentId) {
        this.studentId = studentId;
    }
}

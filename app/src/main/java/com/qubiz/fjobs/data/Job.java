package com.qubiz.fjobs.data;


import com.google.gson.annotations.SerializedName;
import com.qubiz.fjobs.util.DataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Job {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    private String id;
    private String description;
    private String title;
    private String photo;
    private String city;
    private String address;

    @SerializedName("estimated_time")
    private int estimatedTime;

    @SerializedName("created_date")
    private String createdDate;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("end_date")
    private String endDate;

    private int difficulty;

    @SerializedName("reward")
    private String jobReward;

    public Job() {
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public String getJobReward() {
        return jobReward;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setJobReward(String jobReward) {
        this.jobReward = jobReward;
    }
}

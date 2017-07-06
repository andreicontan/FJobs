package com.qubiz.fjobs.data;


import com.google.gson.annotations.SerializedName;

public class Job {
    private int id;
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
    private JobReward jobReward;

    public Job(int id, String description, String title, String photo, String city, String address, int estimatedTime, String createdDate, String startDate, String endDate, int difficulty, JobReward jobReward) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.photo = photo;
        this.city = city;
        this.address = address;
        this.estimatedTime = estimatedTime;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.difficulty = difficulty;
        this.jobReward = jobReward;
    }

    public int getId() {
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

    public int getDifficulty() {
        return difficulty;
    }

    public JobReward getJobReward() {
        return jobReward;
    }
}

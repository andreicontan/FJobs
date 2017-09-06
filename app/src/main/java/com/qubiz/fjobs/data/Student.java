package com.qubiz.fjobs.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andreicontan on 30/08/2017.
 */

public class Student {
    
    @SerializedName("id")
    private String id;

    @SerializedName("jobs") /* jobs applied for */
    public List<String> jobs;

    @SerializedName("fname")
    private String fname ;

    @SerializedName("lname")
    private String lname ;

    @SerializedName("email")
    private String email ;

    @SerializedName("phone")
    private String phone ;

    @SerializedName("rating")
    private Integer rating;

    @SerializedName("city")
    private String city ;

    @SerializedName("age")
    private Integer age ;

    @SerializedName("photo")
    private String photo ;

    @SerializedName("earned_skills")
    private String earnedSkills;

    public String getGender() {
        return gender;
    }

    private String gender;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEarnedSkills() {
        return earnedSkills;
    }

    public void setEarnedSkills(String earnedSkills) {
        this.earnedSkills = earnedSkills;
    }
}


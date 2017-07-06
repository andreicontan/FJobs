package com.qubiz.fjobs.data;

/**
 * Created by cameliahotico on 7/6/17.
 */

public class JobReward {
    private String value;
    private String description;

    public JobReward() {
    }

    public JobReward(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

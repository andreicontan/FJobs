package com.qubiz.fjobs.data;

/**
 * Created by cameliahotico on 7/6/17.
 */

public class JobReward {
    private String value;
    private String description;

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
}

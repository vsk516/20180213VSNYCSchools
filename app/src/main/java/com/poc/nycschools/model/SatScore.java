package com.poc.nycschools.model;

import com.google.gson.annotations.SerializedName;

public class SatScore {

    @SerializedName("dbn")
    private String dbn;

    @SerializedName("num_of_sat_test_takers")
    private String numOfSatTestTakers;

    @SerializedName("sat_critical_reading_avg_score")
    private String satCriticalReadingAvgScore;

    @SerializedName("sat_math_avg_score")
    private String satMathAvgScore;

    @SerializedName("sat_writing_avg_score")
    private String satWritingAvgScore;

    @SerializedName("school_name")
    private String schoolName;

    public String getDbn() {
        return dbn;
    }

    public String getNumOfSatTestTakers() {
        return numOfSatTestTakers;
    }

    public String getSatCriticalReadingAvgScore() {
        return satCriticalReadingAvgScore;
    }

    public String getSatMathAvgScore() {
        return satMathAvgScore;
    }

    public String getSatWritingAvgScore() {
        return satWritingAvgScore;
    }

    public String getSchoolName() {
        return schoolName;
    }
}

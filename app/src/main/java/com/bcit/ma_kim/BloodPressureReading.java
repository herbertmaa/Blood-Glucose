package com.bcit.ma_kim;

import android.icu.text.SimpleDateFormat;

import java.io.Serializable;
import java.util.Date;

public class BloodPressureReading implements Serializable {
    public String id;
    public String spUser;
    public String date;
    public String systolicReading;
    public String diastolicReading;
    public String condition;

    public BloodPressureReading() {
    }

    public BloodPressureReading(String spUser, String systolicReading,
                                String diastolicReading, String dateString) {

        this.id = String.valueOf(System.currentTimeMillis());
        this.spUser = spUser;

        if (dateString.isEmpty()) {
            //Datetime string
            Date currentDate = new Date(System.currentTimeMillis());
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy/MM/dd");
            dateString = dateTimeFormatter.format(currentDate);
        }

        this.date = dateString;
        this.systolicReading = systolicReading;
        this.diastolicReading = diastolicReading;

        //Readings into ints for comparison
        int systolicReadingInt = Integer.parseInt(this.systolicReading);
        int diastolicReadingInt = Integer.parseInt(this.diastolicReading);
        if (systolicReadingInt > 180 || diastolicReadingInt > 120) {
            this.condition = ConditionTypes.HYPERTENSIVE.toString();
        } else if (systolicReadingInt >= 140 || diastolicReadingInt >= 90) {
            this.condition = ConditionTypes.STAGE2.toString();
        } else if (systolicReadingInt >= 130 || diastolicReadingInt >= 80) {
            this.condition = ConditionTypes.STAGE1.toString();
        } else if (systolicReadingInt >= 120) {
            this.condition = ConditionTypes.ELEVATED.toString();
        } else {
            this.condition = ConditionTypes.NORMAL.toString();
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpUser() {
        return spUser;
    }

    public void setSpUser(String spUser) {
        this.spUser = spUser;
    }

    @Override
    public String toString() {
        return "BloodPressureReading{" +
                "id='" + id + '\'' +
                ", spUser='" + spUser + '\'' +
                ", date='" + date + '\'' +
                ", systolicReading='" + systolicReading + '\'' +
                ", diastolicReading='" + diastolicReading + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSystolicReading() {
        return systolicReading;
    }

    public void setSystolicReading(String systolicReading) {
        this.systolicReading = systolicReading;
    }

    public String getDiastolicReading() {
        return diastolicReading;
    }

    public void setDiastolicReading(String diastolicReading) {
        this.diastolicReading = diastolicReading;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition() {
        int systolicReadingInt = Integer.parseInt(this.getSystolicReading());
        int diastolicReadingInt = Integer.parseInt(this.getDiastolicReading());
        if (systolicReadingInt > 180 || diastolicReadingInt > 120) {
            this.condition = ConditionTypes.HYPERTENSIVE.toString();
        } else if (systolicReadingInt >= 140 || diastolicReadingInt >= 90) {
            this.condition = ConditionTypes.STAGE2.toString();
        } else if (systolicReadingInt >= 130 || diastolicReadingInt >= 80) {
            this.condition = ConditionTypes.STAGE1.toString();
        } else if (systolicReadingInt >= 120) {
            this.condition = ConditionTypes.ELEVATED.toString();
        } else {
            this.condition = ConditionTypes.NORMAL.toString();
        }
    }
}

//Possible condition types.
enum ConditionTypes {
    NORMAL, ELEVATED, STAGE1, STAGE2, HYPERTENSIVE
}


package com.bashizip.bhlib;


import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class BusinessHours extends BasePojo implements Comparable<BusinessHours> {

    static int[] week_days_int = {1, 2, 3, 4, 5, 6, 7};
    private int dayIndex;
    private String dayOfWeek;
    private String from;
    private String to, to24, from24;
    private String shortDayOfWeek;

    public BusinessHours() {


    }


    public BusinessHours(String dayOfWeek, String from, String to) {
        this.dayOfWeek = dayOfWeek;
        this.from = from;
        this.to = to;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getShortDayOfWeek() {
        return shortDayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setShortDayOfWeek(String shortDayOfWeek) {
        this.shortDayOfWeek = shortDayOfWeek;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo24() {
        return to24;
    }

    public void setTo24(String to24) {
        this.to24 = to24;
    }

    public String getFrom24() {
        return from24;
    }

    public void setFrom24(String from24) {
        this.from24 = from24;
    }


    //ArrayList<String> businessHoursFoo = new ArrayList<>();
    @Override
    public String toString() {

        /*String businessHoursFoo = dayOfWeek + ", " + from + " - " + to;
        String businessHoursFooData = "\\"+"\""+shortDayOfWeek+ " "+from24+ "-"+to24+"\\"+"\"";*/



       /* businessHoursFoo.add(dayOfWeek + ", " + from + " - " + to);
        businessHoursFoo.add("\\"+"\""+shortDayOfWeek+ " "+from24+ "-"+to24+"\\"+"\"");

        System.out.println("Hours View String: " +businessHoursFoo.get(0));
        System.out.println("Hours View String: " +businessHoursFoo.get(1));

        System.out.println("Hours View String: " +dayOfWeek + ", " + from + " - " + to);*/
        return dayOfWeek + ", " + from + " - " + to;
    }

    @Override
    public int compareTo(BusinessHours businessHours) {
        return 0;
    }
}

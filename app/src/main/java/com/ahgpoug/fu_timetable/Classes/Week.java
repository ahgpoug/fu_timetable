package com.ahgpoug.fu_timetable.Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Week implements Serializable {
    private String weekName;
    private ArrayList<Day> listOfDays;

    public Week(String weekName, ArrayList<Day> listOfDays){
        this.weekName = weekName;
        this.listOfDays = listOfDays;
    }

    public String getWeekName() { return weekName; }
    public ArrayList<Day> getListOfDays() { return listOfDays; }
}

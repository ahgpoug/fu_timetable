package com.ahgpoug.fu_timetable.Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Day implements Serializable {

    private String dayDate;
    private ArrayList<Class_o> listOfClasses;

    public Day(String dayDate, ArrayList<Class_o> listOfClasses){
        this.dayDate = dayDate;
        this.listOfClasses = listOfClasses;
    }

    public String getDayDate() { return dayDate; }
    public ArrayList<Class_o> getListOfClasses() { return listOfClasses; }
}

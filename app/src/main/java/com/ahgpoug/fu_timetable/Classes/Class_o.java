package com.ahgpoug.fu_timetable.Classes;


import java.io.Serializable;

public class Class_o implements Serializable{
    private String classTime;
    private String classType;
    private String classGroups;
    private String className;
    private String classNumber;
    private String classLecturer;

    public Class_o(String classTime, String classType, String classGroups, String className, String classNumber, String classLecturer, String classExtra){
        this.classTime = classTime;
        this.classType = classType;
        this.classGroups = classGroups;
        this.className = className;
        this.classNumber = classNumber;
        this.classLecturer = classLecturer;
    }

    public String getClassTime() { return classTime; }
    public String getClassType() { return classType; }
    public String getClassGroups() { return classGroups; }
    public String getClassName() { return className; }
    public String getClassNumber() { return classNumber; }
    public String getClassLecturer() { return classLecturer; }

}

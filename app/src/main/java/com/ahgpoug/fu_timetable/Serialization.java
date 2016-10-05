package com.ahgpoug.fu_timetable;

import android.content.Context;
import android.os.Environment;

import com.ahgpoug.fu_timetable.Classes.Week;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Serialization {

    public static void Serialize(){
        try {
            FileOutputStream fileOut;
            ObjectOutputStream out;

            new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.ahgpoug.fu_timetable/files").mkdirs();

            File fileR = new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/fu_timetable/files", "data.dat");
            fileOut = new FileOutputStream(fileR);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(GlobalVariables.mainList);
            out.close();
            fileOut.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void Deserialize(){
        try {
            FileInputStream fileIn;
            ObjectInputStream in;

            new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.ahgpoug.fu_timetable/files").mkdirs();

            File fileR = new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.ahgpoug.fu_timetable/files", "data.dat");

            fileIn = new FileInputStream(fileR);
            in = new ObjectInputStream(fileIn);
            GlobalVariables.mainList = (ArrayList<Week>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
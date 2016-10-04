package com.ahgpoug.fu_timetable;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.ahgpoug.fu_timetable.Classes.Week;
import com.opencsv.CSVReader;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class PDFReader {
    private static int cursor;
    public static void getData(String path) {
        try {
            CSVReader reader = new CSVReader(new FileReader(path + "//files//timetable.csv"));
            String[] nextLine;

            PrintWriter out = new PrintWriter(new FileOutputStream(path + "//files//result.txt"));

            ArrayList<String[]> listOfLines = new ArrayList<String[]>();

            while ((nextLine = reader.readNext()) != null) {
                listOfLines.add(nextLine);
            }

            ArrayList<String> newListOfLines;
            newListOfLines = parseLines(listOfLines);
            for (String s : newListOfLines)
            {
                out.println(s.replaceAll("\\p{C}", " "));
            }
            out.flush();
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static ArrayList<String> parseLines(ArrayList<String[]> listOfLines){
        cursor = 0;
        ArrayList<String> newListOfLines = new ArrayList<String>();

        for (int i = 0; i < listOfLines.size(); i++){
            String line = Arrays.toString(listOfLines.get(i));
            newListOfLines.add(line.trim());
        }
        return newListOfLines;
    }


    private static String parseToSingleLine(ArrayList<String[]> listOfLines){
        String resultStr = "";
        for (int i = cursor; i < listOfLines.size(); i++){
            String line = Arrays.toString(listOfLines.get(i));
            resultStr += " ";
            resultStr += line;
            if (line.charAt(line.length() - 1) == ']') {
                cursor = i;
                break;
            }
        }
        Log.e("MyTagResult", resultStr);
        cursor++;
        return resultStr;
    }
}

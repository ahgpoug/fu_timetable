package com.ahgpoug.fu_timetable;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.ahgpoug.fu_timetable.Classes.Class_o;
import com.ahgpoug.fu_timetable.Classes.Day;
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

import static java.util.Arrays.asList;

public class PDFReader {
    private static int position;

    public static void getData(String path) {
        try {
            CSVReader reader = new CSVReader(new FileReader(path + "//files//timetable.csv"));
            String[] nextLine;
            ArrayList<String> newListOfLines;
            PrintWriter out = new PrintWriter(new FileOutputStream(path + "//files//result.txt"));
            ArrayList<String[]> listOfLines = new ArrayList<String[]>();

            while ((nextLine = reader.readNext()) != null) {
                listOfLines.add(nextLine);
            }

            newListOfLines = parseLines(listOfLines);
            /*for (String s : newListOfLines)
            {
                out.println(s);
            }

            out.flush();
            out.close();*/

            readDay(1, "1 september", newListOfLines);


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static ArrayList<String> parseLines(ArrayList<String[]> listOfLines){
        ArrayList<String> newListOfLines = new ArrayList<String>();

        for (int i = 0; i < listOfLines.size(); i++){
            String line = Arrays.toString(listOfLines.get(i));
            if (!(line.charAt(3) == '/') && !(line.charAt(6) == '/'))
                newListOfLines.add((line.replaceAll("\\p{C}", " ").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\(.*?\\)", "")));
            else
                newListOfLines.add(line.replaceAll("\\p{C}", " "));
        }
        return newListOfLines;
    }

    private static void setData(ArrayList<String> listOfLines){
        position = 0;

        for (int ln = 0; ln < listOfLines.size(); ln++){
            String line = listOfLines.get(ln);
        }
    }

    private static Day readDay(int start, String header, ArrayList<String> listOfLines){
        String line;
        ArrayList<String[]> dayList = new ArrayList<String[]>();
        ArrayList<Class_o> classList = new ArrayList<Class_o>();

        for (int i = start; i < listOfLines.size(); i++){
            line = listOfLines.get(i);
            if ((line.charAt(0) == '[') && (line.charAt(line.length() - 1) == ']')){
                position = i;
                break;
            } else {
                dayList.add(readClass(line));
            }
        }

        for (int i = 0; i < dayList.size(); i++){
            String[] a = dayList.get(i);
            classList.add(new Class_o(a[0].trim(), a[1].trim(), a[2].trim(), a[3].trim(), a[4].trim(), a[5].trim(), a[6].trim()));
            Log.e("MyTag", a[0].trim() + ", " + a[1].trim() + ", " + a[2].trim() + ", " +  a[3].trim() + ", " + a[4].trim() + ", " + a[5].trim() + ", " + a[6].trim() + ", ");
        }

        Day day = new Day(header, classList);

        return day;
    }

    private static String[] readClass(String line){
        int counter = 0;
        String[] arr = new String[7];
        String[] newArr = new String[7];
        String data = "";

        for (char c : line.toCharArray()){
            if (c == ',') {
                arr[counter] = data;
                counter++;
                data = "";
            } else
                data += c;
        }
        arr[counter] = data;


        newArr[0] = arr[0].substring(0, 11);
        newArr[1] = arr[0].substring(12, arr[0].length());

        newArr[2] = "";
        for (int i = 1; i < counter - 3; i++)
            newArr[2] += arr[i];

        newArr[3] = arr[counter - 2];

        String str = arr[counter];
        str = str.replaceAll("[^0-9]+", " ");
        List<String> lst = Arrays.asList(str.trim().split(" "));
        newArr[4] = "";
        for (int i = 0; i < lst.size(); i++) {
            if (i == lst.size() - 1)
                newArr[4] += (lst.get(i));
            else
                newArr[4] += (lst.get(i) + "\n");
        }

        if (lst.size() == 1)
            newArr[5] = arr[counter].replaceAll("\\d", "");
        else
            newArr[5] = arr[counter].replaceAll("\\d", "\n");
        newArr[6] = arr[counter - 1];

        return newArr;
    }
}

package com.ahgpoug.fu_timetable;

import android.content.Context;
import android.util.Log;


import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PDFReader {


    public static String getData(String path, Context context) {
        try {
            PdfReader reader = new PdfReader(path + "//files//timetable.pdf");

            PdfReaderContentParser parser = new PdfReaderContentParser(reader);

            PrintWriter out = new PrintWriter(new FileOutputStream(path + "//files//result.txt"));


            TextExtractionStrategy strategy;

            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                out.println(strategy.getResultantText());
            }

            reader.close();
            out.flush();
            out.close();

            StringBuilder text = new StringBuilder();

            BufferedReader br = new BufferedReader(new FileReader(path + "//files//result.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();

            return "ok";
        } catch (IOException e){
            e.printStackTrace();
        }
        return "error";
    }
}

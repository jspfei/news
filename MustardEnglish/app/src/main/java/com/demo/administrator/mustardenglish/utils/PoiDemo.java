package com.demo.administrator.mustardenglish.utils;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * Created by Administrator on 2017/1/8.
 */

public class PoiDemo {
    public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException{
        Workbook wb = WorkbookFactory.create(new FileInputStream("sentence.xlsx"));
        for (Sheet sheet : wb ) {
            for (Row row : sheet) {
                for (Cell cell : row) {
                    String a =cell.getStringCellValue();// Do something here
                    a=a;
                    Log.d("Tag", "       "+a+"");
                }
            }
        }
    }
}
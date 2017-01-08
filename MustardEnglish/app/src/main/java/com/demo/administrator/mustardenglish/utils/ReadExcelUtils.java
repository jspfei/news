package com.demo.administrator.mustardenglish.utils;

import android.content.Context;
import android.util.Log;

import com.demo.administrator.mustardenglish.bean.Sentence;


import org.apache.poi.hssf.usermodel.HSSFCell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */

public class ReadExcelUtils {

    private static List<Sentence> sentenceList;
    //读取Excel表
    public static void readExcel(Context context) {
        try {
           // InputStream input = new FileInputStream(new File("sentence.xlsx"));
            InputStream input =context.getAssets().open("sentence.xlsx");

            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            // Iterate over each row in the sheet
            Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {

                XSSFRow row = (XSSFRow) rows.next();
                System.out.println("Row #" + row.getRowNum());
                //每一行 = 新建一个学生
                Sentence sentence = new Sentence();
                // Iterate over each cell in the row and print out the cell"s
                // content
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()) {
                    XSSFCell cell = (XSSFCell) cells.next();
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            System.out.println("number= " + (int) (cell.getNumericCellValue()));
                            //自定操作,我这里写入学号
                            sentence.setId((int) (cell.getNumericCellValue()));
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            System.out.println("string= " + cell.getStringCellValue());
                            //自定操作,我这里写入姓名
                            sentence.setCn(cell.getStringCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.println("boolean= " + cell.getBooleanCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            System.out.println("formula= " + cell.getCellFormula());
                            break;
                        default:
                            System.out.println("unsuported sell type");
                            break;
                    }
                }
                sentence.save();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //刷新列表
        getAllStudent();
    }



    //查询所有句子
    private static void getAllStudent() {
        sentenceList = DataSupport.findAll(Sentence.class);

        for( Sentence s : sentenceList){
            Log.d("TAG","cn    "+s.getCn());
            Log.d("TAG","en    "+s.getEn());
        }
    }

}

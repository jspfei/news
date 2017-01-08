package com.demo.administrator.mustardenglish.utils;
import android.content.Context;

import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.common.Common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * Created by Administrator on 2017/1/8.
 */

public class ReadExcel {

    /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<Sentence> readExcel(Context context,String path) throws IOException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(context,path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }

    /**
     56      * Read the Excel 2010
     57      * @param path the path of the excel file
     58      * @return
     59      * @throws IOException
     60      */
    public List<Sentence> readXlsx(Context context, String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
      //  InputStream is = new FileInputStream(path);
        InputStream is = context.getResources().getAssets().open(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Sentence student = null;
        List<Sentence> list = new ArrayList<Sentence>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    student = new Sentence();
                    XSSFCell id = xssfRow.getCell(0);
                    XSSFCell cn = xssfRow.getCell(1);
                    XSSFCell en = xssfRow.getCell(2);

                    student.setId( Integer.valueOf(getValue(id)));
                    student.setCn(getValue(cn));
                    student.setEn(getValue(en));
                    list.add(student);
                }
            }
        }
        return list;
    }
    /**
     94      * Read the Excel 2003-2007
     95      * @param path the path of the Excel
     96      * @return
     97      * @throws IOException
     98      */
    public List<Sentence> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Sentence student = null;
        List<Sentence> list = new ArrayList<Sentence>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    student = new Sentence();
                    HSSFCell id = hssfRow.getCell(0);
                    HSSFCell cn = hssfRow.getCell(1);
                    HSSFCell en = hssfRow.getCell(2);

                    student.setId( Integer.valueOf(getValue(id)));
                    student.setCn(getValue(cn));
                    student.setEn(getValue(en));

                    list.add(student);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
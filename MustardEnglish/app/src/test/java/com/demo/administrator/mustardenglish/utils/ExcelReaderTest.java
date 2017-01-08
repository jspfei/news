package com.demo.administrator.mustardenglish.utils;

import android.util.Log;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/1/8.
 */
public class ExcelReaderTest {
    @Test
    public void testRead() throws FileNotFoundException, IOException {
        Workbook wb = ExcelReader.createWb("src/main/assets/sentence.xlsx");

        // 获取Workbook中Sheet个数
        int sheetTotal = wb.getNumberOfSheets();
        Debug.printf("工作簿中的工作表个数为：{}", sheetTotal);

        // 获取Sheet
        Sheet sheet = ExcelReader.getSheet(wb, 0);

        // 遍历Sheet
        List<Object[]> list = ExcelReader.listFromSheet(sheet);
        Log.d("TAG",list.toString());
    }
}
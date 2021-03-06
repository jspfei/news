package com.demo.administrator.mustardenglish.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */

public class Common {

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static final String LIB_PATH = "file:///android_asset";
    public static final String STUDENT_INFO_XLS_PATH = LIB_PATH + "/sentence" + POINT + OFFICE_EXCEL_2003_POSTFIX;
    public static final String STUDENT_INFO_XLSX_PATH = LIB_PATH + "/sentence" + POINT + OFFICE_EXCEL_2010_POSTFIX;
    public static final String NOT_EXCEL_FILE = " : Not the Excel file!";
    public static final String PROCESSING = "Processing...";
}

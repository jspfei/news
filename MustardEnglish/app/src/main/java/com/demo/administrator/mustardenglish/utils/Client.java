package com.demo.administrator.mustardenglish.utils;

import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.common.Common;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */

public class Client {
    public static void main(String[] args) throws IOException {
              String excel2003_2007 = Common.STUDENT_INFO_XLS_PATH;
              String excel2010 = Common.STUDENT_INFO_XLSX_PATH;
              // read the 2003-2007 excel
           /*   List<Sentence> list = new ReadExcel().readExcel(excel2003_2007);
              if (list != null) {
                      for (Sentence student : list) {
                              System.out.println("id. : " + student.getId() + ", cn : " + student.getCn() + ", en : " + student.getEn() );
                          }
                  }*/
              System.out.println("======================================");
              // read the 2010 excel
             /* List<Sentence> list1 = new ReadExcel().readExcel(excel2010);
              if (list1 != null) {
                      for (Sentence student : list1) {
                              System.out.println("id. : " + student.getId() + ", cn : " + student.getCn() + ", en : " + student.getEn());
                          }
                  }*/
          }
      }

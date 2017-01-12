package com.demo.administrator.mustardenglish.utils;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

/**
 * Created by Administrator on 2017/1/8.
 */

public class StringUtil {
    /**
     * 简单按照 空格分解
     * @value  content
     * */
    public static List<String> splitSentence(String conent){

        List<String> data = new ArrayList<String>();

        String [] temp = null;
        temp = conent.split(" ");
        for(int i = 0;i<temp.length;i++){
            String test = temp[i];
            data.add(test);
        }
       /* data.add(conent.substring(0, conent.indexOf(" ")));
        for(int i=0;i<conent.length();i++){
            if(" ".equals(""+conent.charAt(i))){

                int index =conent.indexOf(" ",i+1);
                if(index==-1)
                    index=conent.length();
                String test =conent.substring(i+1, index);
                data.add(test);
            }
        }*/
        return data;
    }
}

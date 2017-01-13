package com.demo.administrator.mustardenglish.handler;

import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */

public class SentenceDataHandler {

    ////将英文按照" " 分割开
    public static List<String> getSentenceStrList(Sentence sentence) {

        List<String> data = StringUtil.splitSentence(sentence.getEn());

        return data;
    }
    //将字符串数组 打乱顺序 重新返回
    public static  List<String>  shuffleSentenceList(List<String> list) {
        List<String> list1 = new ArrayList<String>();
        for(String str:list){
            list1.add(str);
        }
        Collections.shuffle(list1);

        return list1;
    }
}

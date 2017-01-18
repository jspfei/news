package com.demo.administrator.mustardenglish.handler;

import android.content.Context;

import com.demo.administrator.mustardenglish.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * assets  下文件名
 * Created by Administrator on 2017/1/14.
 */

public class RescoureDataHandler {

    public static List<String> getShuffleRescoureList(Context context){
        List<String> rescureList = new ArrayList<String>();
        String[] str = context.getResources().getStringArray(R.array.resource_id);
        for(int i = 0;i<str.length;i++){
            String id = str[i];
            rescureList.add(id);
        }
        Collections.shuffle(rescureList);
        return rescureList;
    }
}

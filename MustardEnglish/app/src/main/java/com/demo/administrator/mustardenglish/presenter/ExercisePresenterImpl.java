package com.demo.administrator.mustardenglish.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.handler.AsyncReadTxtHandler;
import com.demo.administrator.mustardenglish.handler.SentenceDataHandler;
import com.demo.administrator.mustardenglish.utils.StringUtil;
import com.demo.administrator.mustardenglish.view.ExerciseView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */

public class ExercisePresenterImpl implements ExercisePresenter {

    private static final String TAG = "ExercisePresenterImpl";
    private ExerciseView mExerciseView;
    private Context context;
    public ExercisePresenterImpl(ExerciseView exerciseView,Context context){
        this.mExerciseView = exerciseView;
        this.context = context;
    }

    @Override
    public void readAssetsTxt(String filename) {
        //异步加载数据
        AsyncReadTxtHandler asyncReadTxtHandler =  new AsyncReadTxtHandler(context, new AsyncReadTxtHandler.CallBackListener() {
            @Override
            public void callBack(List<Sentence> values) {
                mExerciseView.setSentenceList(values);
            }
        });
        asyncReadTxtHandler.readAssetsTxt(filename);
    }
    //将 句子中的英文句子 解析成单个单词返回 List
    @Override
    public List<String> getSentenceStrList(Sentence sentence) {
        return  SentenceDataHandler.getSentenceStrList(sentence);
    }

    @Override
    public void validateSentence(List<String> list, String txt ,int index) {
        if(list.get(index)!=null && txt.equals( list.get(index))){
            mExerciseView.showInputSentence(" "+txt);
            mExerciseView.setCurrentInputIndex(index+1);
            if((index+1) == list.size()){
                mExerciseView.showToast(context.getResources().getString(R.string.success_str));
                mExerciseView.setIsWinner(true);
                // 延迟2秒
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mExerciseView.nextGame();
                    }
                },2000);
            }

        }else if(list.get(index)==null){
            Log.d("TAG","---------------null");
        }
        else{
            mExerciseView.showToast(context.getResources().getString(R.string.again_str));
        }
    }

    @Override
    public   List<String>  shuffleSentenceList(List<String> list) {
        return SentenceDataHandler.shuffleSentenceList(list);
    }
}

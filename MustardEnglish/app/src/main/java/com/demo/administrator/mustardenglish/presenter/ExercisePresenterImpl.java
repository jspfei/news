package com.demo.administrator.mustardenglish.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.administrator.mustardenglish.bean.Sentence;
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
        ReadTxtTask readTxtTask = new ReadTxtTask(context);
        readTxtTask.execute(filename);
    }
    class ReadTxtTask extends AsyncTask<String,Void,List<Sentence>> {
        private Context context;
        ReadTxtTask(Context context){
            this.context = context;
        }
        /**
         * 运行在UI线程中，在调用doInBackground()之前执行
         */
        @Override
        protected void onPreExecute() {//Toast.makeText(context,"开始执行",Toast.LENGTH_SHORT).show();
        }
        /**
         * 后台运行的方法，可以运行非UI线程，可以执行耗时的方法
         */
        @Override
        protected List<Sentence>  doInBackground(String... params) {
            List<Sentence> sentenceList = new ArrayList<Sentence>();
            try{
                InputStream inputStream = context.getResources().getAssets().open(params[0]+".txt");
                InputStreamReader isReadr = new InputStreamReader(inputStream,"gbk");
                BufferedReader reader = new BufferedReader(isReadr);
                String out = "";
                int index = 0;
                while((out=reader.readLine())!=null){
                     // Log.d("读取到的文件信息:",out);
                    String[] outs = out.split("#");
                    Sentence sentence =new Sentence();
                    sentence.setId(index);
                    sentence.setEn(outs[0]);
                    sentence.setCn(outs[1]);
                    sentenceList.add(sentence);

                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return sentenceList;
        }
        /**
         * 运行在ui线程中，在doInBackground()执行完毕后执行
         */
        @Override
        protected void onPostExecute(List<Sentence>  values) {
             mExerciseView.setSentenceList(values);
        }
    }
    @Override
    public List<Sentence> getSentenceList() {
        List<Sentence> sentenceList = new ArrayList<Sentence>();
        Sentence sentence = new Sentence();
        sentence.setId(1);
        sentence.setCn("这个句子多了一个字。");
        sentence.setEn("There is one word too many in this sentence.");
        sentenceList.add(sentence);
        return sentenceList;
    }

    //将 句子中的英文句子 解析成单个单词返回 List

    @Override
    public List<String> getSentenceStrList(Sentence sentence) {

        List<String> data = StringUtil.splitSentence(sentence.getEn());

        return data;
    }

    @Override
    public void validateSentence(List<String> list, String txt ,int index) {
        if(txt .equals( list.get(index))){
            mExerciseView.showInputSentence(" "+txt);
            mExerciseView.setCurrentInputIndex(index+1);
            if((index+1) == list.size()){
                mExerciseView.showToast("胜利！");

                // 延迟2秒
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mExerciseView.nextGame();
                    }
                },2000);
               /* TimerTask task = new TimerTask(){

                    public void run(){

                        //execute the task

                    }

                };

                Timer timer = new Timer();

                timer.schedule(task, delay);*/
            }

        }else{
            mExerciseView.showToast("输入错误！");
        }
    }

    @Override
    public   List<String>  shuffleSentenceList(List<String> list) {
        List<String> list1 = new ArrayList<String>();
        for(String str:list){
            list1.add(str);
        }
        Collections.shuffle(list1);

        return list1;
    }
}

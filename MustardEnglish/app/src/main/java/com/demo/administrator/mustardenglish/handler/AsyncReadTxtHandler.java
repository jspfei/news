package com.demo.administrator.mustardenglish.handler;

import android.content.Context;
import android.os.AsyncTask;
import android.telecom.Call;

import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.presenter.ExercisePresenterImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */

public class AsyncReadTxtHandler {
    private Context context;
    private CallBackListener callBackListener;
    public AsyncReadTxtHandler(Context context,CallBackListener callBackListener){
        this.context = context;
        this.callBackListener = callBackListener;
    }
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
            callBackListener.callBack(values);
        }
    }


    public interface CallBackListener{
        void callBack(List<Sentence>  values);
    }
}

package com.demo.administrator.mustardenglish.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.handler.AsyncReadTxtHandler;
import com.demo.administrator.mustardenglish.handler.SentenceDataHandler;
import com.demo.administrator.mustardenglish.view.MagicFightingView;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */

public class MagicFightingPresenterImpl implements MagicFightingPresenter {
    private MagicFightingView magicFightingView;
    private Context context;
    public MagicFightingPresenterImpl(Context context,MagicFightingView magicFightingView){
        this.magicFightingView = magicFightingView;
        this.context = context;
    }

    @Override
    public void readTxt(String filename) {
        //异步加载数据
      AsyncReadTxtHandler asyncReadTxtHandler =  new AsyncReadTxtHandler(context, new AsyncReadTxtHandler.CallBackListener() {
            @Override
            public void callBack(List<Sentence> values) {
                magicFightingView.setSentenceList(values);
            }
        });
        asyncReadTxtHandler.readAssetsTxt(filename);
    }
    @Override
    public void validateSentence(List<String> list, String txt ,int index) {
        if(list.get(index)!=null && txt.equals( list.get(index))){
            magicFightingView.showInputSentence(" "+txt);
            magicFightingView.setCurrentInputIndex(index+1);
            if((index+1) == list.size()){
              //  magicFightingView.showToast(context.getResources().getString(R.string.success_str));
                magicFightingView.setCurrentInputEnd(true);
                // 延迟2秒
                magicFightingView.nextMagicTxt();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },2000);
            }

        }else if(list.get(index)==null){
            Log.d("TAG","---------------null");
        }
        else{
            magicFightingView.showToast(context.getResources().getString(R.string.again_str));
        }
    }

    //生成怪物血量 4 -7
    @Override
    public int getMonsterBlood() {
        return (int) (Math.random() * 3)+4;
    }

    @Override
    public List<String> getSentenceStrList(Sentence sentence) {
        return SentenceDataHandler.getSentenceStrList(sentence);
    }

    @Override
    public List<String> getShuffleSentenceList(List<String> list) {
        return SentenceDataHandler.shuffleSentenceList(list);
    }
}

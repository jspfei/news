package com.demo.administrator.mustardenglish.presenter;

import android.widget.TextView;

import com.demo.administrator.mustardenglish.bean.Sentence;

import java.util.List;


/**
 * Created by Administrator on 2017/1/8.
 */

public interface ExercisePresenter {

    void readAssetsTxt(String filename);
    List<String> getSentenceStrList(Sentence sentence);

    void validateSentence(List<String> list, String txt,int index);
    List<String>  shuffleSentenceList(List<String> list);

}
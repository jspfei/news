package com.demo.administrator.mustardenglish.view;

import com.demo.administrator.mustardenglish.bean.Sentence;

import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */

public interface ExerciseView {
    void showInputSentence(String txt);
    void setCurrentInputIndex(int i);
    void showToast(String txt);
    void setSentenceList(List<Sentence> list);
    void nextGame();
}

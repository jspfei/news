package com.demo.administrator.mustardenglish.view;

import com.demo.administrator.mustardenglish.bean.Sentence;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */

public interface MagicFightingView {
    void setSentenceList(List<Sentence> list);
    void showToast (String txt);
    void showInputSentence(String txt);
    void setCurrentInputIndex(int i);
    void setCurrentInputEnd(Boolean flag);
    void nextMagicTxt();
    void monsterDeadHandler();
}

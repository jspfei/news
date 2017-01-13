package com.demo.administrator.mustardenglish.presenter;

import com.demo.administrator.mustardenglish.bean.Sentence;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */

public interface MagicFightingPresenter {
   void readTxt(String filename);
   List<String> getSentenceStrList(Sentence sentence);
   List<String>  getShuffleSentenceList(List<String> list);
   void validateSentence(List<String> list, String txt,int index);
   int getMonsterBlood();
}

package com.demo.administrator.mustardenglish.presenter;

import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.utils.StringUtil;
import com.demo.administrator.mustardenglish.view.ExerciseView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */

public class ExercisePresenterImpl implements ExercisePresenter {

    private static final String TAG = "ExercisePresenterImpl";
    private ExerciseView mExerciseView;
    public ExercisePresenterImpl(ExerciseView exerciseView){
        this.mExerciseView = exerciseView;
    }
    @Override
    public void setTextViewMul(TextView textView) {
        LinearLayout.LayoutParams
                lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        //----用TextView来显示换行长文本----//

        textView.setLayoutParams(lp);//限制TextView的宽高

        textView.setEllipsize(TextUtils.TruncateAt.END);

        textView.setSingleLine(false);

        textView.setMaxLines(5);
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

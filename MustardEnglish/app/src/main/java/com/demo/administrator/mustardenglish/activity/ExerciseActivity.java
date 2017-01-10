package com.demo.administrator.mustardenglish.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.adapter.MyGridAdapter;
import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.presenter.ExercisePresenter;
import com.demo.administrator.mustardenglish.presenter.ExercisePresenterImpl;
import com.demo.administrator.mustardenglish.view.ExerciseView;

import java.util.Collections;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity implements ExerciseView {

    private ExercisePresenter mExercisePresenter;

    private TextView id_cn_txt,id_en_txt,id_show_input_txt;
    private RelativeLayout id_input_key_rl;
    private List<Sentence> sentenceList = null;
    private Sentence currentSentence = null;
    private List<String> sentenceStrList = null;
    private List<String> shuffleStrList = null;//将 sentenceStrList打乱顺序
    private Context mContext;
    private GridView id_grid_view;
    private int currentInputIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExercisePresenter = new ExercisePresenterImpl(this);
        setContentView(R.layout.activity_exercise);
        mContext = this;
        initView();
        initData();
        showWords();
    }


    private void initView() {
        id_cn_txt =   (TextView) findViewById(R.id.id_cn_txt);
     //   mExercisePresenter.setTextViewMul(id_cn_txt);
        id_en_txt =   (TextView) findViewById(R.id.id_en_txt);
      //  mExercisePresenter.setTextViewMul(id_en_txt);
        id_show_input_txt =   (TextView) findViewById(R.id.id_show_input_txt);
       // mExercisePresenter.setTextViewMul(id_show_input_txt);

        id_input_key_rl = (RelativeLayout) findViewById(R.id.id_input_key_rl);

        id_grid_view = (GridView) findViewById(R.id.id_grid_view);
    }


    private void initData() {
        sentenceList = mExercisePresenter.getSentenceList();
        if(sentenceList == null || sentenceList.size() == 0){
            Toast.makeText(mContext,"没有练习的句子",Toast.LENGTH_SHORT).show();
            return;
        }
        currentSentence = sentenceList.get(0);

        id_cn_txt.setText(currentSentence.getCn());
        id_en_txt.setText(currentSentence.getEn());
        id_show_input_txt.setText("");


        sentenceStrList = mExercisePresenter.getSentenceStrList(currentSentence);
        shuffleStrList = mExercisePresenter.shuffleSentenceList(sentenceStrList);
        id_grid_view.setAdapter( new MyGridAdapter(this,shuffleStrList));
        id_grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String text = shuffleStrList.get(i);
                mExercisePresenter.validateSentence(sentenceStrList,text,currentInputIndex);
            }
        });

    }
    private void showWords(){

    }

    @Override
    public void showInputSentence(String txt) {
        String str = id_show_input_txt.getText().toString();
        id_show_input_txt.setText(str+txt);

    }

    @Override
    public void setCurrentInputIndex(int i) {
        currentInputIndex = i;
    }

    @Override
    public void showToast(String txt) {
        Toast.makeText(mContext,txt,Toast.LENGTH_SHORT).show();
    }
}

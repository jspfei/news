package com.demo.administrator.mustardenglish.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.adapter.MyGridAdapter;
import com.demo.administrator.mustardenglish.bean.Branch;
import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.presenter.ExercisePresenter;
import com.demo.administrator.mustardenglish.presenter.ExercisePresenterImpl;
import com.demo.administrator.mustardenglish.view.ExerciseView;
import com.demo.administrator.mustardenglish.widget.ButtonM;

import java.util.Collections;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity implements ExerciseView ,View.OnClickListener{

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
    private String res_id;
    private int gameIndex = 0 ;//列表总index
    private Button nextBtn;

    private ImageButton id_back_ib;
    private TextView id_title_tv;
    private TextView id_number_current_tv;
    private TextView id_number_total_tv;

    private LinearLayout id_en_area_ll;//英语显示区
    private Boolean isShowPrompt = true;
    private Button id_open_prompt;
    private Branch mBranch;
    private Boolean isWin = false;
    private Boolean isGameOver = false;
    private Button id_restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBranch =(Branch) getIntent().getSerializableExtra("subbranch"); //"b_1"
        res_id = mBranch.getBranch_class();

        setContentView(R.layout.activity_exercise);
        mContext = this;
        mExercisePresenter = new ExercisePresenterImpl(this,mContext);
        initView();
        read();
        initData();
    }

    private void read(){
        mExercisePresenter.readAssetsTxt(res_id);//"1_1.txt"
    }
    private void initView() {
        id_cn_txt =   (TextView) findViewById(R.id.id_cn_txt);
        id_en_txt =   (TextView) findViewById(R.id.id_en_txt);
        id_show_input_txt =   (TextView) findViewById(R.id.id_show_input_txt);

        id_input_key_rl = (RelativeLayout) findViewById(R.id.id_input_key_rl);

        id_grid_view = (GridView) findViewById(R.id.id_grid_view);
        nextBtn = (Button) findViewById(R.id.id_next);
        nextBtn.setOnClickListener(this);
        id_back_ib = (ImageButton) findViewById(R.id.id_back_ib);
        id_back_ib.setOnClickListener(this);

        id_title_tv = (TextView) findViewById(R.id.id_title_tv);
        id_number_current_tv = (TextView) findViewById(R.id.id_number_current_tv);
        id_number_total_tv = (TextView) findViewById(R.id.id_number_total_tv);
        id_en_area_ll = (LinearLayout) findViewById(R.id.id_en_area_ll);
        id_open_prompt=(Button)findViewById(R.id.id_open_prompt);
        id_open_prompt.setOnClickListener(this);
        id_restart = (Button) findViewById(R.id.id_restart);
        id_restart.setOnClickListener(this);
        id_restart.setVisibility(View.GONE);
    }
    //初始化
    private void initData() {
        setTitle(mBranch.getBranch_title());
        isShowEnglishPrompt();
    }

    @Override
    public void nextGame(){
        if(sentenceList.size()!=0 && !isGameOver){
            sentenceStrList =null;
            shuffleStrList =null;
            currentInputIndex =0 ;
            gameIndex++;
            setCurrentNumber(gameIndex);
            isWin = false;
            if(gameIndex<sentenceList.size()){
                setInitializationGame(gameIndex);
            }else
            {
                isGameOver = true;
                showReStartButtonVisible(isGameOver);
                showToast(getResources().getString(R.string.next_constitution_str));
            }
        }
    }
    private void showReStartButtonVisible(Boolean flag){
        if(flag){
            id_restart.setVisibility(View.VISIBLE);
        }else{
            id_restart.setVisibility(View.GONE);
        }
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
        Toast toast =  Toast.makeText(mContext,txt,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,100);
        toast.show();
    }

    @Override
    public void setIsWinner(Boolean win) {
        isWin = win;
    }

    @Override
    public void setSentenceList(List<Sentence> list) {
        sentenceList = list;
        if(sentenceList == null || sentenceList.size() == 0){
            Toast.makeText(mContext,getResources().getString(R.string.no_magic_str),Toast.LENGTH_SHORT).show();
            return;
        }
        setTotalNumber(sentenceList.size());
        gameIndex = 0;
        setInitializationGame(gameIndex);
    }
    private void setTitle(String value){
        id_title_tv .setText(value);

    }
    private void setCurrentNumber(int i){
        id_number_current_tv .setText(""+i);
    }
    private void setTotalNumber(int i){
        id_number_total_tv .setText(""+i);
    }
    private void setInitializationGame(int currentId){
        currentSentence = sentenceList.get(currentId);
        id_cn_txt.setText(currentSentence.getCn());
        id_en_txt.setText(currentSentence.getEn());
        id_show_input_txt.setText("");


        sentenceStrList = mExercisePresenter.getSentenceStrList(currentSentence);
        shuffleStrList = mExercisePresenter.shuffleSentenceList(sentenceStrList);
        id_grid_view.setAdapter( new MyGridAdapter(this,shuffleStrList));
        id_grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!isGameOver){
                    if(!isWin ){
                        String text = shuffleStrList.get(i);

                        mExercisePresenter.validateSentence(sentenceStrList,text,currentInputIndex);
                    }else{
                        showToast(getResources().getString(R.string.success_str));
                    }
                }else{
                    showToast(getResources().getString(R.string.next_constitution_str));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_back_ib:
                finish();
                break;
            case R.id.id_next:
                nextGame();
                break;
            case R.id.id_open_prompt:
                isShowEnglishPrompt();
                break;
            case R.id.id_restart:
                restartGame();
                break;
        }
    }
    private void restartGame(){
        gameIndex= 0;
        currentInputIndex = 0;
        isWin = false;
        isGameOver = false;
        read();
        initData();
        showReStartButtonVisible(isGameOver);
    }
    //是否显示英语提示
    private void isShowEnglishPrompt(){
        if(isShowPrompt){
            id_en_area_ll.setVisibility(View.VISIBLE);
            id_open_prompt.setText(getResources().getString(R.string.close_prompt));
        }else{
            id_en_area_ll.setVisibility(View.GONE);
            id_open_prompt.setText(getResources().getString(R.string.open_prompt));
        }
        isShowPrompt = !isShowPrompt;
    }
}

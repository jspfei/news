package com.demo.administrator.mustardenglish.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.adapter.MyGridAdapter;
import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.presenter.MagicFightingPresenter;
import com.demo.administrator.mustardenglish.presenter.MagicFightingPresenterImpl;
import com.demo.administrator.mustardenglish.view.MagicFightingView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by admin on 2017/1/13.
 */

public class MagicFightingActivity extends Activity implements MagicFightingView{
    private List<Sentence> sentenceList = null;
    private List<Sentence> sentenceAllList = null;
    private GridView id_grid_view;
    private MagicFightingPresenter magicFightingPresenter;
    private Sentence currentSentence;
    private int currentIndex = 0;
    private int gameIndex = 0;
    private List<String> sentenceStrList;
    private List<String> shuffleStrList;
    private TextView id_prompt_label_tv;
    private TextView id_magic_txt_label_tv;
    private Boolean isCurrentInputEnd = false;
    private ScrollView id_magic_area;
    private int monsterMaxBlood;
    private Boolean isGameOver = false;
    private RatingBar id_monster_blood;
    private int currentListStartIndex=0;//在sentenceList中取出数据的开始下标
    private ImageView id_monster_iv;//怪物
    private List<String> allRescoureIdList = null; //所有资源ID List 打乱顺序
    private int currentRescourseIndex = 0; //当前应用的资源下标；
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_magic_fight);
        magicFightingPresenter = new MagicFightingPresenterImpl(this,this);
        allRescoureIdList = magicFightingPresenter.getAllRecoursesIds();
        initView();
        initData();
        readData();
    }

    private void initData() {
        sentenceAllList = new ArrayList<Sentence>();
        sentenceList = new ArrayList<Sentence>();
    }

    private void initView() {
        id_grid_view = (GridView) findViewById(R.id.id_grid_view);
        id_prompt_label_tv =(TextView) findViewById(R.id.id_prompt_label_tv);
        id_magic_txt_label_tv = (TextView) findViewById(R.id.id_magic_txt_label_tv);
        id_magic_area = (ScrollView)findViewById(R.id.id_magic_area);
        id_monster_blood = (RatingBar)findViewById(R.id.id_monster_blood);
        id_monster_iv = (ImageView) findViewById(R.id.id_monster_iv);
    }

    private void readData(){
        magicFightingPresenter.readTxt(allRescoureIdList.get(currentRescourseIndex));//"1_1.txt"
    }

    @Override
    public void setSentenceList(List<Sentence> list) {
        sentenceAllList.addAll(list);
        setCurrentFightData();
    }
    private void startGame(){
        monsterMaxBlood = magicFightingPresenter.getMonsterBlood();
        id_monster_blood.setNumStars(monsterMaxBlood);
        monsterBloodShow();
        initGame();
    }
    private void initGame(){
        isGameOver =false;
        isCurrentInputEnd =false;
        cleanMagicTxt();
        sentenceList = new ArrayList<Sentence>();
        currentIndex = 0;
        gameIndex = 0;
    }
    private void monsterBloodShow(){
        id_monster_blood.setRating(monsterMaxBlood);
    }
    private void setCurrentFightData(){
        startGame();

        if((currentListStartIndex+monsterMaxBlood)<sentenceAllList.size()){ //取得的数据已经未超出当前数据数量
            sentenceList.addAll(sentenceAllList.subList(currentListStartIndex,currentListStartIndex+monsterMaxBlood));
            currentListStartIndex = currentListStartIndex+ monsterMaxBlood;
              if(sentenceList == null || sentenceList.size() == 0){
                Toast.makeText(this,getResources().getString(R.string.no_magic_str),Toast.LENGTH_SHORT).show();
                return;
            }
            gameIndex = 0;
            setInitializationGame(gameIndex);
        }else if((currentListStartIndex + monsterMaxBlood)-sentenceAllList.size()>=0){
            currentRescourseIndex ++ ;
            if(currentRescourseIndex == allRescoureIdList.size()){
                showToast(getResources().getString(R.string.game_over));
                return;
            }
            if((currentListStartIndex+monsterMaxBlood)-sentenceAllList.size()==0){
                currentListStartIndex = sentenceAllList.size();
            }else{
                int index = (currentListStartIndex+monsterMaxBlood)-sentenceAllList.size();
                currentListStartIndex = sentenceAllList.size()-index;
            }
            magicFightingPresenter.readTxt(allRescoureIdList.get(currentRescourseIndex));//"1_1.txt"
        }
    }

    private void setInitializationGame(int index){
        //当前输入语句
        currentSentence = sentenceList.get(index);
        //设置显示汉语提示
        id_prompt_label_tv.setText(currentSentence.getCn());
        //
        sentenceStrList = magicFightingPresenter.getSentenceStrList(currentSentence);
        shuffleStrList = magicFightingPresenter.getShuffleSentenceList(sentenceStrList);//将 sentenceStrList打乱顺序

        id_grid_view.setAdapter( new MyGridAdapter(this,shuffleStrList));
        id_grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!isGameOver){
                    if(!isCurrentInputEnd){
                        String text = shuffleStrList.get(i);
                        magicFightingPresenter.validateSentence(sentenceStrList,text,currentIndex);
                    }else{

                    }
                }else{
                    initGame();
                }
            }
        });
    }
    @Override
    public void showToast(String txt) {
        Toast toast =  Toast.makeText(this,txt,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,100);
        toast.show();
    }

    private void scrollToEnd(){
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                id_magic_area.fullScroll(ScrollView.FOCUS_DOWN);
            }
        },2000);

    }

    @Override
    public void showInputSentence(String txt) {
        String str = id_magic_txt_label_tv.getText().toString();
        id_magic_txt_label_tv.setText(str+txt);
    }


    @Override
    public void setCurrentInputIndex(int i) {
        currentIndex = i;
    }

    @Override
    public void setCurrentInputEnd(Boolean flag) {
        isCurrentInputEnd = flag;
        //下一個怪物出場
    }

    public void verticalRun( final  View view)
    {
        ValueAnimator animator = ValueAnimator.ofFloat(0, view.getX()-
               100);
        animator.setTarget(view);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                view.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }
    @Override
    public void nextMagicTxt() {
        if(sentenceList.size()!=0&& !isGameOver){

            monsterMaxBlood --;
            monsterBloodShow();
            //输入显示区换行
            nextLine();
            if(isCurrentInputEnd){//当期行输入完成 移动到最底层
                scrollToEnd();
            }
            sentenceStrList =null;
            shuffleStrList =null;
            currentIndex =0 ;
            gameIndex++;
            isCurrentInputEnd = false;
            if(gameIndex<sentenceList.size()){
                setInitializationGame(gameIndex);
            }else
            {
                isGameOver = true;
                magicFightingPresenter.showMonsterDead();
            }
        }
    }
    @Override
    public void monsterDeadHandler(){
        magicFightingPresenter.propertyValuesHolder(id_monster_iv);
        setCurrentFightData();
    }
    private void cleanMagicTxt(){
        id_magic_txt_label_tv.setText("");
    }
    private void nextLine(){
        String str = id_magic_txt_label_tv.getText().toString();
        id_magic_txt_label_tv.setText(str+"\n");
    }

}

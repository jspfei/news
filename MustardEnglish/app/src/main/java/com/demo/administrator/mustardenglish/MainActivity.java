package com.demo.administrator.mustardenglish;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.demo.administrator.mustardenglish.activity.ExerciseActivity;
import com.demo.administrator.mustardenglish.activity.MagicAcademyActivity;
import com.demo.administrator.mustardenglish.activity.MagicFightingActivity;
import com.demo.administrator.mustardenglish.activity.WebViewActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
        context = this;

    }


    private void intView() {
        findViewById(R.id.id_exercise_enter_btn).setOnClickListener(this);
        findViewById(R.id.id_magic_fight_btn).setOnClickListener(this);
        findViewById(R.id.id_web_view_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_exercise_enter_btn:
                Intent intent = new Intent(MainActivity.this, MagicAcademyActivity.class);
                startActivity(intent);
                break;
            case R.id.id_magic_fight_btn:
                Intent intent1 = new Intent(MainActivity.this, MagicFightingActivity.class);
                startActivity(intent1);
                break;
            case R.id.id_web_view_btn:
                Intent intent2 = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent2);
                break;
        }
    }


}

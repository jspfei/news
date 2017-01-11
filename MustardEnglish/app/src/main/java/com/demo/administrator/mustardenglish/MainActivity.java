package com.demo.administrator.mustardenglish;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.demo.administrator.mustardenglish.activity.ExerciseActivity;
 


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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_exercise_enter_btn:
                Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(intent);
                break;
        }
    }
}

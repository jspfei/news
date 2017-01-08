package com.demo.administrator.mustardenglish;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.demo.administrator.mustardenglish.activity.ExerciseActivity;
import com.demo.administrator.mustardenglish.bean.Sentence;
import com.demo.administrator.mustardenglish.utils.ReadExcel;
import com.demo.administrator.mustardenglish.utils.ReadExcelUtils;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
        context = this;
        try {
            readExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //    ReadExcelUtils.readExcel(this);
    }
    private void readExcel() throws IOException {
        List<Sentence> list1 = new ReadExcel().readExcel(context,"sentence.xlsx");
        if (list1 != null) {
            for (Sentence student : list1) {
                System.out.println("id. : " + student.getId() + ", cn : " + student.getCn() + ", en : " + student.getEn());
            }
        }
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

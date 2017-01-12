package com.demo.administrator.mustardenglish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.adapter.BranchAdapter;
import com.demo.administrator.mustardenglish.bean.Branch;
import com.demo.administrator.mustardenglish.presenter.SubCollegePresenter;
import com.demo.administrator.mustardenglish.presenter.SubCollegePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/1/11.
 */

public class SubCollegeActivity extends Activity {
    private ListView id_title_class_list;
    private BranchAdapter branchAdapter = null;
    private String branch_class;
    private List<Branch> branchList;
    private Branch mBranch;
    private TextView id_branch_title;
    private ImageButton id_back_ib;
    private SubCollegePresenter subCollegePresenter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_academy);
        mBranch =(Branch) getIntent().getSerializableExtra("branch"); //"b_1"
        branch_class = mBranch.getBranch_class();
        subCollegePresenter = new SubCollegePresenterImpl(this);

        id_branch_title = (TextView)findViewById(R.id.id_branch_title);
        id_branch_title.setText(mBranch.getBranch_title());

        id_title_class_list = (ListView) findViewById(R.id.id_title_class_list);
        branchList =subCollegePresenter.getBranchData(branch_class);
        branchAdapter = new BranchAdapter(this,branchList);
        id_title_class_list.setAdapter(branchAdapter);
        id_title_class_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Branch branch = branchList.get(position);
                Intent intent = new Intent(SubCollegeActivity.this,ExerciseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("subbranch", branch);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        id_back_ib = (ImageButton) findViewById(R.id.id_back_ib);
        id_back_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

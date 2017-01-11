package com.demo.administrator.mustardenglish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.adapter.BranchAdapter;
import com.demo.administrator.mustardenglish.bean.Branch;

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
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_academy);
        branch_class = getIntent().getStringExtra("branch_class"); //"b_1"

        id_title_class_list = (ListView) findViewById(R.id.id_title_class_list);
        branchList = getBranchData();
        branchAdapter = new BranchAdapter(this,branchList);
        id_title_class_list.setAdapter(branchAdapter);
        id_title_class_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Branch branch = branchList.get(position);
                Intent intent = new Intent(SubCollegeActivity.this,ExerciseActivity.class);
                intent.putExtra("resources_id", branch.getBranch_class());//assets 下资源ID
                startActivity(intent);
            }
        });
    }
    private List<Branch> getBranchData(){
        List<Branch> data = new ArrayList<Branch>();
        String[] branchs = getSubBranch(branch_class);
        for(int i = 0 ; i<branchs.length;i++){
            Branch branch = new Branch();
            branch.setBranch_id(i+1);
            branch.setBranch_title(branchs[i]);
            branch.setBranch_class(branch_class+"_"+String.valueOf(i+1));//组合 b_1_1
            data.add(branch);
        }
        return data;
    }
    private String[] getSubBranch(String branch_class){
        String[] branchs = new String[]{};
        if(branch_class.equals("b_1")){
            branchs = getResources().getStringArray(R.array.b_1);
        }
        return branchs;
    }
}

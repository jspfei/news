package com.demo.administrator.mustardenglish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.adapter.BranchAdapter;
import com.demo.administrator.mustardenglish.bean.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/1/11.
 */

public class MagicAcademyActivity extends Activity {
    private ListView id_title_class_list;
    private BranchAdapter branchAdapter = null;
    private List<Branch> branchList;
    private ImageButton id_back_ib;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_academy);

        id_title_class_list = (ListView) findViewById(R.id.id_title_class_list);
        branchList = getBranchData();
        branchAdapter = new BranchAdapter(this,branchList);
        id_title_class_list.setAdapter(branchAdapter);
        id_title_class_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Branch branch = branchList.get(position);
                Intent intent = new Intent(MagicAcademyActivity.this,SubCollegeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("branch", branch);
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
    private List<Branch> getBranchData(){
        List<Branch> data = new ArrayList<Branch>();
        String[] branchs = getResources().getStringArray(R.array.branch_title);
        for(int i = 0 ; i<branchs.length;i++){
            Branch branch = new Branch();
            branch.setBranch_id(i+1);
            branch.setBranch_title(branchs[i]);
            branch.setBranch_class("b_"+Integer.valueOf(i+1));//文件下标从1开始 故下标加1
            data.add(branch);
        }
        return data;
    }
}

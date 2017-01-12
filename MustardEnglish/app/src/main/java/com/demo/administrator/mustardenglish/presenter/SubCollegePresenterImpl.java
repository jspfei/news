package com.demo.administrator.mustardenglish.presenter;

import android.content.Context;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.bean.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/1/12.
 */

public class SubCollegePresenterImpl implements SubCollegePresenter {
    private Context context;
    public SubCollegePresenterImpl(Context context){
        this.context = context;
    }
    @Override
    public List<Branch> getBranchData(String branch_class) {
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

    //为每个大分类建立对应 arrays
    private String[] getSubBranch(String branch_class) {
        String[] branchs = new String[]{};
        if(branch_class.equals("b_1")){
            branchs = context.getResources().getStringArray(R.array.b_1);
        }else if(branch_class.equals("b_2")){
            branchs = context.getResources().getStringArray(R.array.b_2);
        }
        return branchs;
    }
}

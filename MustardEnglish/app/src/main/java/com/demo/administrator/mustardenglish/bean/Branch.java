package com.demo.administrator.mustardenglish.bean;

/**
 * Created by admin on 2017/1/11.
 */

public class Branch {
    private int branch_id; //分类ID
    private String branch_title;//分类名称
    private String branch_class;//分类类别 b_1

    public String getBranch_class() {
        return branch_class;
    }

    public void setBranch_class(String branch_class) {
        this.branch_class = branch_class;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_title() {
        return branch_title;
    }

    public void setBranch_title(String branch_title) {
        this.branch_title = branch_title;
    }
}

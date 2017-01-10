package com.demo.administrator.mustardenglish.bean;

import org.litepal.crud.DataSupport;

/**
 * 定义 句子数据模型
 * Created by Administrator on 2017/1/8.
 */

public class Sentence extends DataSupport {
    private int id;//id
    private String cn;//中文
    private String en;//英文

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}

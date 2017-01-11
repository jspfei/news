package com.demo.administrator.mustardenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.administrator.mustardenglish.R;
import com.demo.administrator.mustardenglish.bean.Branch;

import java.util.List;

/**
 * Created by admin on 2017/1/11.
 */

public class BranchAdapter extends BaseAdapter {
    private Context context;
    private List<Branch> list;

    public BranchAdapter(Context context,List<Branch> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if(convertView == null){
            holderView = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_branch_item,null);
            holderView.id_branch_title_tv = (TextView) convertView.findViewById(R.id.id_branch_title_tv);
            convertView.setTag(holderView);
        }else {
            holderView = (HolderView) convertView.getTag();
        }
        if(list.size()!= 0){
            Branch branch = list.get(position);
            holderView.id_branch_title_tv.setText(branch.getBranch_title());
        }
        return convertView;
    }

    static class HolderView{
        TextView id_branch_title_tv;
    }
}

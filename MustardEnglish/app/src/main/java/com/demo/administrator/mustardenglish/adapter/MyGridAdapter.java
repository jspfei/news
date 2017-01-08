package com.demo.administrator.mustardenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.administrator.mustardenglish.R;

import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */

public class MyGridAdapter extends BaseAdapter {
    LayoutInflater inflater = null;
    List<String> list;

    private OnTouchBlankPositionListener mTouchBlankPosListener;
    public MyGridAdapter(Context context,List<String> list){
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    /**
     * 设置GridView的空白区域的触摸事件
     *
     * @param listener
     */
    public void setOnTouchBlankPositionListener(
            OnTouchBlankPositionListener listener) {
        mTouchBlankPosListener = listener;
    }

    public interface OnTouchBlankPositionListener {
        void onTouchBlank(String value);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null || view.getTag() == null){
            view = inflater.inflate(R.layout.grid_view_item,null);
            holder = new ViewHolder();
            holder.textView = (TextView)view.findViewById(R.id.id_word_txt);
            holder.id_show_grid_view_ll = (LinearLayout) view.findViewById(R.id.id_show_grid_view_ll) ;
            view.setTag(holder);
        }else{

            holder = (ViewHolder)view.getTag();
        }


        if(list !=null || list.size()!=0){
            holder.textView.setText(list.get(i));
          /*  holder.id_show_grid_view_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTouchBlankPosListener.onTouchBlank(list.get(i));
                }
            });*/
        }
        return view;
    }

    public  class ViewHolder{
        LinearLayout id_show_grid_view_ll;
        TextView textView;
    }
}

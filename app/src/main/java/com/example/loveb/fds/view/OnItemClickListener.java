package com.example.loveb.fds.view;

/**
 * Created by 何建钦 on 2017/12/10.
 */

import android.view.View;
import android.view.ViewGroup;

public interface OnItemClickListener<T>
{
    void onItemClick(ViewGroup parent, View view, T t, int position);
    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}

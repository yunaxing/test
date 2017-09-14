package com.qkxmall.mall.define.defines;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sunshine on 01/18/2016.
 */
public class La extends LayoutInflater {
    protected La(Context context) {
        super(context);
    }

    protected La(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return null;
    }

    @Override
    public View inflate(int resource, ViewGroup root, boolean attachToRoot) {
        return super.inflate(resource, root, attachToRoot);
    }

    @Override
    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        return super.onCreateView(name, attrs);
    }
}

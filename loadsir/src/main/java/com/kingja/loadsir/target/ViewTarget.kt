package com.kingja.loadsir.target;

import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadLayout;

/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ViewTarget implements ITarget {

    @Override
    public boolean equals(Object target) {
        return target instanceof View;
    }

    @Override
    public LoadLayout replaceView(Object target, Callback.OnReloadListener onReloadListener) {
        final   View oldContent = (android.view.View) target;
        final  ViewGroup contentParent = (ViewGroup) (oldContent.getParent());
        int childIndex = 0;
        final  int childCount = contentParent == null ? 0 : contentParent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (contentParent.getChildAt(i) == oldContent) {
                childIndex = i;
                break;
            }
        }
        if (contentParent != null) {
            contentParent.removeView(oldContent);
        }
        final ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        final LoadLayout loadLayout = new LoadLayout(oldContent.getContext(), onReloadListener);
        loadLayout.setupSuccessLayout(new SuccessCallback(oldContent, oldContent.getContext(), onReloadListener));
        if (contentParent != null) {
            contentParent.addView(loadLayout, childIndex, oldLayoutParams);
        }
        return loadLayout;
    }
}

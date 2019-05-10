/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by pocketEos on 2018/1/29.
 * 规避当一个界面之中有多个TextView设置了相同属性，有且只有一个控件会滚动显示，其他TextView控件则不会滚动显示。
 */

public class ScrollText extends android.support.v7.widget.AppCompatTextView {
    public ScrollText(Context context) {
        super(context);
    }
    public ScrollText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ScrollText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //返回textview是否处在选中的状态
    //而只有选中的textview才能够实现滚动效果
    @Override
    public boolean isFocused() {
        return true;
    }
}

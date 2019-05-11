/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.mine;

import android.os.Bundle;
import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseFragment;


public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {
    @Override
    public MinePresenter initPresenter() {
        return new MinePresenter(getActivity());
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_mine;
    }
}

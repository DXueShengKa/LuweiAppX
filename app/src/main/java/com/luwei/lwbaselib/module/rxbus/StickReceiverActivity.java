package com.luwei.lwbaselib.module.rxbus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.luwei.base.IPresent;
import com.luwei.base.LwBaseActivity;
import com.luwei.lwbaselib.R;
import com.luwei.rxbus.RxBus;


/**
 * Created by Mr_Zeng
 *
 * @date 2019/3/4
 */
public class StickReceiverActivity extends LwBaseActivity {
    Button mBtnReceiveStick;
    TextView mTvContent;

    @Override
    public void initView(Bundle savedInstanceState) {
        findView();
        setClickForView();

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_stick_receiver;
    }

    @Override
    public IPresent newP() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unregister(this);
    }

    public void onViewClicked() {
        mTvContent.setText("");
        RxBus.getInstance()
                .register(this)
                .ofStickType(StickEvent.class)
                .subscribe(stickEvent -> {
                    mTvContent.setText(stickEvent.getContent().toString());
                    //事件清除
                    RxBus.getInstance().removeStick(stickEvent);
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    throwable.printStackTrace();
                });
    }
    private void findView(){
        mBtnReceiveStick = findViewById(R.id.btn_receive_stick);
        mTvContent = findViewById(R.id.tv_content);
    }

    private void setClickForView(){
        findViewById(R.id.btn_receive_stick).setOnClickListener(v -> onViewClicked());
    }

}
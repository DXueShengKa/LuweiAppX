package com.luwei.lwbaselib.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.luwei.base.IPresent;
import com.luwei.base.LwBaseActivity;
import com.luwei.lwbaselib.R;
import com.luwei.util.forresult.SimpleForResult;


/**
 * Toast 的使用示例
 */
public class SimpleForResultActivity extends LwBaseActivity {

    TextView mTvSimpleText;

    public void onViewClicked() {
        // 简化调用 startActivityForResult 及避免在 onActivityResult 中处理繁琐的结果
        SimpleForResult simpleForResult = new SimpleForResult(this);
        simpleForResult.startForResult(SimpleForResult2Activity.class)
                .subscribe((resultInfo) -> {
                    if (resultInfo.getResultCode() == RESULT_OK) {
                            ToastUtils.showLong("获取Result为RESULT_OK");
                            mTvSimpleText.setText("获取Result为RESULT_OK");

                    }
                });
    }

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
        return R.layout.activity_simple_for_result;
    }

    @Override
    public IPresent newP() {
        return null;
    }


    private void findView(){
        mTvSimpleText = findViewById(R.id.tv_simple_text);
    }

    private void setClickForView(){
        findViewById(R.id.btn_open_for_result).setOnClickListener(v -> onViewClicked());
    }

}
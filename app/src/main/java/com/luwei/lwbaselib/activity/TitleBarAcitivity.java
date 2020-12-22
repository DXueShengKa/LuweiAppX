package com.luwei.lwbaselib.activity;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.luwei.lwbaselib.R;
import com.luwei.ui.view.TitleBar;


/**
 * @author licheng
 */
public class TitleBarAcitivity extends AppCompatActivity {

    TitleBar mTb1;
    TitleBar mTb2;
    TitleBar mTb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_bar_acitivity);
        findView();

        mTb2.setLeftClickListener(new TitleBar.OnLeftClickListener() {
            @Override
            public void leftClick() {
                ToastUtils.showShort("点击左部");
            }
        });
        mTb2.setLeftText("测试");
        mTb2.setTitleText("点击左部");
        mTb2.setTitleTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        mTb2.setTitleTextSize(12);
    }
    private void findView(){
        mTb1 = findViewById(R.id.tb_1);
        mTb2 = findViewById(R.id.tb_2);
        mTb3 = findViewById(R.id.tb_3);
    }

}
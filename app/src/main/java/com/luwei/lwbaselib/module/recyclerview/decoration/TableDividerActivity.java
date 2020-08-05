package com.luwei.lwbaselib.module.recyclerview.decoration;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.luwei.lwbaselib.R;
import com.luwei.recyclerview.decoration.GridSpaceDecoration;
import com.luwei.recyclerview.decoration.TableDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_Zeng
 *
 * @date 2018/11/27
 */
public class TableDividerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        initView();
    }

    private void initView() {
        RecyclerView rvTest = findViewById(R.id.rv_test);
        findViewById(R.id.btn_orientation).setVisibility(View.GONE);
        rvTest.setAdapter(new SampleAdapter(createList()));
        GridLayoutManager manager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (i == 4 || i == 13) {
                    return 3;
                }
                if (i == 12) {
                    return 2;
                }
                return 1;
            }
        });
        rvTest.setLayoutManager(manager);
        GridSpaceDecoration decoration = new TableDecoration(getResources().getColor(R.color.colorPrimary), 10);
        rvTest.addItemDecoration(decoration);

    }

    private List<String> createList() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataList.add("第" + i + "个");
        }
        return dataList;
    }


}

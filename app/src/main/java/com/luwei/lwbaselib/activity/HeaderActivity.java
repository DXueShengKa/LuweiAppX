package com.luwei.lwbaselib.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.luwei.base.IPresent;
import com.luwei.base.LwBaseActivity;
import com.luwei.lwbaselib.R;
import com.luwei.lwbaselib.bean.ImageViewInfo;
import com.luwei.util.forresult.SimpleForResult;
import com.luwei.util.imageloader.ImageLoaderUtils;
import com.previewlibrary.GPreviewBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import me.nereo.image_selector.PictureIntentHelper;
import me.nereo.image_selector.MultiImageSelector;
import me.nereo.image_selector.MultiImageSelectorActivity;
import me.nereo.image_selector.utils.FileUtils;

public class HeaderActivity extends LwBaseActivity {

    public static final int REQUEST_SELECT_CODE = 1111;
    ImageView ivHeader;
    Button btnCamera;
    Button btnOpen1;
    Button btnOpen2;
    private File mTmpFile;

    SimpleForResult simpleForResult;
    private String mHeadUrl;

    private File mZoomOutFile;

    @Override
    public void initView(Bundle savedInstanceState) {
        findView();
        setClickForView();
        simpleForResult = new SimpleForResult(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_header;
    }

    @Override
    public IPresent newP() {
        return null;
    }


    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header:
                ImagePreView(view);
                break;
            case R.id.btn_camera:
                openCamera();
                break;
            case R.id.btn_open1:
                openImageSelect();
                break;
            case R.id.btn_open2:
                openImageSelect2();
                break;
            case R.id.btn_open3:
                openImageSelect3();
                break;
        }
    }

    /**
     * 图片预览
     *
     * @param view
     */
    private void ImagePreView(View view) {
        if (TextUtils.isEmpty(mHeadUrl)) {
            return;
        }

        // 设置图片信息
        ImageViewInfo imageViewInfo = new ImageViewInfo(mHeadUrl);
        // 设置图片的位置信息
        Rect bounds = new Rect();
        view.getGlobalVisibleRect(bounds);
        imageViewInfo.setBounds(bounds);
        // 预览（单个图片）
        GPreviewBuilder.from(this)
                .setSingleFling(true)
                .setSingleData(imageViewInfo)
                .start();
    }


    /**
     * 打开图库，方式 1
     */
    public void openImageSelect() {
        // todo：打开摄像头、读写权限
        // todo:在 AndroidManifest.xml 中添加  <activity android:name="me.nereo.image_selector.MultiImageSelectorActivity"/>
//        打开图库方式 1：
        MultiImageSelector.create(this)
                .single()
                .showCamera(true)
                .start(this, REQUEST_SELECT_CODE);

    }

    /**
     * 打开图库，方式 2
     * 处理结果,不需要在 onActivityResult 中处理结果
     */
    public void openImageSelect2() {
        // todo：打开摄像头、读写权限
        // todo:在 AndroidManifest.xml 中添加  <activity android:name="me.nereo.image_selector.MultiImageSelectorActivity"/>
        simpleForResult.startForResult(PictureIntentHelper.getSelectSingleImageIntent(this, false))
                .subscribe(resultInfo -> {
                    if (resultInfo.getResultCode() == RESULT_OK) {
                        List<String> urls = resultInfo.getData().getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        if (urls != null && urls.size() > 0) {
                            mHeadUrl = urls.get(0);
                            ImageLoaderUtils.getInstance().loadCircleImage(this, ivHeader, mHeadUrl);
                        }
                    }
                });
    }



    /**
     * 打开图库，选择多图
     * 处理结果,不需要在 onActivityResult 中处理结果
     */
    public void openImageSelect3() {
        // todo：打开摄像头、读写权限
        // todo:在 AndroidManifest.xml 中添加  <activity android:name="me.nereo.image_selector.MultiImageSelectorActivity"/>
        simpleForResult.startForResult(PictureIntentHelper.getSelectMultiImageIntent(this, false,3))
                .subscribe(resultInfo -> {
                    if (resultInfo.getResultCode() == RESULT_OK) {
                        List<String> urls = resultInfo.getData().getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        if (urls != null && urls.size() > 0) {
                            mHeadUrl = urls.get(0);
                            ImageLoaderUtils.getInstance().loadCircleImage(this, ivHeader, mHeadUrl);
                        }
                    }
                });
    }



    /**
     * 打开摄像头
     */
    private void openCamera() {
        try {
            mTmpFile = FileUtils.createTmpFile(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        simpleForResult.startForResult(PictureIntentHelper.getOpenCameraIntent(this, mTmpFile))
                .subscribe((resultInfo -> {
                    if (resultInfo.getResultCode() == RESULT_OK) {
                        mHeadUrl = mTmpFile.getAbsolutePath();
                        ImageLoaderUtils.getInstance().loadCircleImage(this, ivHeader, mHeadUrl);
                        // 裁剪(如果没有要求可裁剪，也可以不要）
                        startPictureZoom(mTmpFile);
                    }
                }));
    }

    /**
     * 裁剪
     *
     * @param srcFile
     */
    public void startPictureZoom(File srcFile) {
        try {
            mZoomOutFile = FileUtils.createTmpFile(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        simpleForResult.startForResult(PictureIntentHelper.getPhotoZoomIntent(this, srcFile, mZoomOutFile))
                .subscribe(resultInfo -> {
                    if (mZoomOutFile != null && mZoomOutFile.exists()) {
                        mHeadUrl = mZoomOutFile.getAbsolutePath();
                        ImageLoaderUtils.getInstance().loadCircleImage(this, ivHeader, mHeadUrl);
                        Log.i(TAG, "startPictureZoom: ");
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_SELECT_CODE) {
            List<String> urls = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (urls != null && urls.size() > 0) {
                mHeadUrl = urls.get(0);
                ImageLoaderUtils.getInstance().loadCircleImage(this, ivHeader, mHeadUrl);
            }
        }
    }
    private void findView(){
        ivHeader = findViewById(R.id.iv_header);
        btnCamera = findViewById(R.id.btn_camera);
        btnOpen1 = findViewById(R.id.btn_open1);
        btnOpen2 = findViewById(R.id.btn_open2);
    }

    private void setClickForView(){
        ivHeader.setOnClickListener(this::onViewClicked);
        btnCamera.setOnClickListener(this::onViewClicked);
        btnOpen1.setOnClickListener(this::onViewClicked);
        btnOpen2.setOnClickListener(this::onViewClicked);
        findViewById(R.id.btn_open3).setOnClickListener(this::onViewClicked);
    }

}
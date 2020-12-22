package com.luwei.lwbaselib.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.luwei.lwbaselib.R;
import com.luwei.util.imageloader.ImageLoaderConfig;
import com.luwei.util.imageloader.ImageLoaderUtils;


/**
 * 显示图片框架使用的 activity
 */
public class ImageActivity extends AppCompatActivity {

    ImageView mIvNormal;
    ImageView mIvCircle;
    ImageView mIvFromDrawable;
    ImageView mIvRound;
    ImageView mIvGif;
    ImageView mIvBlur;
    ImageView mIvBlur1;
    ImageView mIvBlurBefor;
    ImageView mIvMask;

    //    private String urlPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540821255682&di=0fc90d9ffd5a8b5f0588369f7c17c607&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130827%2F2531170_141548474000_2.jpg";
    private String urlPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540878104990&di=43ab375e5c37598078e9e55ca8e35288&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-10-12%2F59df06a898fbb.jpg";
    private String gitUrlPath = "http://b.hiphotos.baidu.com/image/pic/item/ac345982b2b7d0a2a5f98d86c0ef76094b369afd.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        findView();
        setTitle("图片框架使用示例");


        //使用方式
        ImageLoaderUtils.getInstance().loadImage(this, mIvNormal, urlPath);
        ImageLoaderUtils.getInstance().loadCircleImage(this, mIvCircle, urlPath);
        ImageLoaderUtils.getInstance().loadRoundedImage(this, mIvRound, urlPath, 20);
        ImageLoaderUtils.getInstance().loadGifImage(this, mIvGif, gitUrlPath);
        ImageLoaderUtils.getInstance().loadImageFromDrawable(this, mIvFromDrawable, R.mipmap.sunyizhen);

        ImageLoaderUtils.getInstance().loadMarkImage(this, mIvMask, urlPath, R.mipmap.mask_starfish);
        ImageLoaderUtils.getInstance().loadImage(this, mIvBlurBefor, urlPath);
        ImageLoaderUtils.getInstance().loadBlurImage(this, mIvBlur, urlPath, 10);
        ImageLoaderUtils.getInstance().loadBlurImage(this, mIvBlur1, urlPath, 25);


        //如何在Application中全局定义ImageLoaderUtils的属性
        ImageLoaderConfig.Builder builder = new ImageLoaderConfig.Builder();
        builder.setErrorPicRes(R.mipmap.default_image);
        builder.setPlacePicRes(R.color.black_000);
        ImageLoaderConfig config = new ImageLoaderConfig(builder);
        ImageLoaderUtils.getInstance().setLoaderConfig(config);


//        Glide.with(this)
//                .load(urlPath)
//                .apply(RequestOptions.bitmapTransform(new GlideBlurformation(this,10)))
//                .into(mIvBlur);

    }
    private void findView(){
        mIvBlur = findViewById(R.id.iv_blur);
        mIvMask = findViewById(R.id.iv_mask);
        mIvBlur1 = findViewById(R.id.iv_blur1);
        mIvFromDrawable = findViewById(R.id.iv_from_drawable);
        mIvBlurBefor = findViewById(R.id.iv_blur_befor);
        mIvCircle = findViewById(R.id.iv_circle);
        mIvGif = findViewById(R.id.iv_gif);
        mIvRound = findViewById(R.id.iv_round);
        mIvNormal = findViewById(R.id.iv_normal);
    }

}
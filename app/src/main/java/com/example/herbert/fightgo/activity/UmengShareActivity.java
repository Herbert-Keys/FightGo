package com.example.herbert.fightgo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.herbert.fightgo.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UmengShareActivity extends AppCompatActivity {

    @BindView(R.id.qqLogin)
    Button qqLogin;
    @BindView(R.id.wxLogin)
    Button wxLogin;
    @BindView(R.id.wbLogin)
    Button wbLogin;
    @BindView(R.id.qqShare)
    Button qqShare;
    @BindView(R.id.wxShare)
    Button wxShare;
    @BindView(R.id.wbShare)
    Button wbShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umeng_share);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.qqLogin, R.id.wxLogin, R.id.wbLogin, R.id.qqShare, R.id.wxShare, R.id.wbShare})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qqLogin:
                LoginQQ();
                break;
            case R.id.wxLogin:
                break;
            case R.id.wbLogin:
                break;
            case R.id.qqShare:
                shareQQ();
                break;
            case R.id.wxShare:
                break;
            case R.id.wbShare:
                break;
        }
    }

    private void LoginQQ() {
        UMShareAPI.get(this).getPlatformInfo(UmengShareActivity.this, SHARE_MEDIA.QQ, authListener);
    }

    private void shareQQ() {
        UMImage image = new UMImage(UmengShareActivity.this, R.mipmap.aa);//资源文件
        UMImage thumb =  new UMImage(this, R.mipmap.ic_launcher);
        image.setThumb(thumb);
        //new ShareAction(UmengShareActivity.this).setPlatform(SHARE_MEDIA.QQ).withText("hello").share();
        UMWeb web = new UMWeb("http://www.baidu.com");
        web.setTitle("This is music title");//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription("my description");//描述
        ShareBoardConfig config = new ShareBoardConfig();//新建ShareBoardConfig               config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);//设置位置
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);
        config.setCancelButtonVisibility(true);
        config.setTitleText("分享链接了").setTitleTextColor(Color.RED).setCancelButtonVisibility(true).setShareboardBackgroundColor(Color.GREEN);
        ShareAction shareAction = new ShareAction(UmengShareActivity.this)
                .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .withMedia(web);
        shareAction.open(config);
        shareAction.share();

    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Log.e("data",data.get("uid")+"---"+data.get("name")+"---"+data.get("gender")+"---"+data.get("iconurl"));
            Toast.makeText(UmengShareActivity.this, "成功了"+data.get("uid"), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(UmengShareActivity.this, "失败：" + t.getMessage(),                                  Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(UmengShareActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(UmengShareActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(UmengShareActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(UmengShareActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };
}

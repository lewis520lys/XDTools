package com.xdkj.xd.xdtools;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 */

public class WelcomeActivity extends AutoLayoutActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndPermission.with(this)
                .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .requestCode(200)
                .callback(listener)
                .start();

    }
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if(requestCode == 200) {
                Intent intent =new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == 200) {
                // 第二种：用自定义的提示语。
                AndPermission.defaultSettingDialog(WelcomeActivity.this, 400)
                        .setTitle("权限申请失败")
                        .setMessage("您拒绝了我们必要的一些权限，已经没法愉快的玩耍了，请在设置中授权！")
                        .setPositiveButton("好，去设置")
                        .show();
            }
        }
    };
}

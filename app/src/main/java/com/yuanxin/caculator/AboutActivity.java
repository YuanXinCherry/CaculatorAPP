package com.yuanxin.caculator;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by 袁鑫 on 2017/4/3.
 */

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        PackageManager manager = getPackageManager();
        PackageInfo info=null;

        try {
            info=manager.getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String Version=info==null?getString(R.string.Unknown):info.versionName;
        String msg=String.format(getString(R.string.Version_Info),Version);

        TextView ver=(TextView)findViewById(R.id.version_info);
        ver.setText(msg);
    }
}

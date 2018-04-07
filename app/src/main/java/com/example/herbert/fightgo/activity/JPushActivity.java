package com.example.herbert.fightgo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.herbert.fightgo.R;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class JPushActivity extends AppCompatActivity {


    @BindView(R.id.bt_setAlia)
    Button btSetAlia;
    @BindView(R.id.bt_setTag)
    Button btSetTag;
    @BindView(R.id.bt_addAction)
    Button btAddAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpush);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_setAlia, R.id.bt_setTag,R.id.bt_addAction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_setAlia:
                JPushInterface.setAlias(this, "aa", new TagAliasCallback() {
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        Toast.makeText(JPushActivity.this, i + "---" + s, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.bt_setTag:
                break;
            case R.id.bt_addAction:


                break;
        }
    }


}

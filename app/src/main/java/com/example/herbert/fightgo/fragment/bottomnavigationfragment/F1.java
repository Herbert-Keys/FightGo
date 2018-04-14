package com.example.herbert.fightgo.fragment.bottomnavigationfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.herbert.fightgo.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/3/29.
 */

public class F1 extends Fragment implements View.OnClickListener {
    View v;
    Button bt_login;
    Button bt_download;
    TextView textView;
    ImageView imgV;
    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.f1, container, false);
        bt_login = v.findViewById(R.id.bt_login);
        bt_download=v.findViewById(R.id.bt_downlaod);
        bt_login.setText("登录");
        bt_login.setOnClickListener(this);
        bt_download.setOnClickListener(this);
        textView = v.findViewById(R.id.tv_f1);
        imgV=v.findViewById(R.id.imgV);
        return v;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                login();
                break;
            case R.id.bt_downlaod:
                download();
                break;
            default:
                break;
        }

    }

    private void download() {
        String desPath= Environment.getExternalStorageDirectory()+"/download";
        String picName="aa.pdf";
        OkGo.<File>get("http://192.168.1.169:8080/ZN/assets/aa.pdf")
                .tag(this)
                .execute(new FileCallback(desPath,picName) {
                    @Override
                    public void onSuccess(Response<File> response) {
                        //textView.setText(response.body().toString());
                        Glide.with(context).load(new File(response.body().toString())).into(imgV);
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        textView.setText((String.valueOf(new DecimalFormat("0.00").format(progress.fraction*100)))+"%");
                        textView.invalidate();
                    }
                });
    }

    private void login() {
        OkGo.<String>get("http://192.168.1.169:8080/ZN/business/userLogin!login.action?loginName=guest&passWord=guest")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jo = new JSONObject(response.body());
                            textView.setText(jo.getString("uid"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

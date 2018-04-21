package com.example.herbert.fightgo.bean;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.herbert.fightgo.IMyAidlI;
import com.example.herbert.fightgo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AidlActivity extends AppCompatActivity {

    @BindView(R.id.tv_aidl)
    TextView tvAidl;
    @BindView(R.id.bt_aidl)

    Button btAidl;
    IMyAidlI myAidlI;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myAidlI = IMyAidlI.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myAidlI = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        ButterKnife.bind(this);
        Intent intent = new Intent(this, MyAidlService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

    }

    @OnClick(R.id.bt_aidl)
    public void onViewClicked() {
        Person person=new Person("张三",18);
        try {
            myAidlI.addPerson(person);
            List<Person> personList = myAidlI.getPersonList();
            tvAidl.setText(personList.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

package com.example.herbert.fightgo.bean;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.herbert.fightgo.IMyAidlI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */

public class MyAidlService extends Service {
    private List<Person> list=new ArrayList();
    private IBinder mIbinder= new IMyAidlI.Stub(){

       @Override
       public void addPerson(Person person) throws RemoteException {

       }

       @Override
       public List<Person> getPersonList() throws RemoteException {
           return null;
       }
   };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIbinder;
    }
}

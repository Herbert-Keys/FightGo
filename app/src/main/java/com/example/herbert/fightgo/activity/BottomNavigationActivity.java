package com.example.herbert.fightgo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.herbert.fightgo.fragment.bottomnavigationfragment.F1;
import com.example.herbert.fightgo.fragment.bottomnavigationfragment.F2;
import com.example.herbert.fightgo.fragment.bottomnavigationfragment.F3;
import com.example.herbert.fightgo.R;

public class BottomNavigationActivity extends AppCompatActivity {



    private F1 f1;
    private F2 f2;
    private F3 f3;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.e("f1","f1");
                    if(f1==null){
                        f1=new F1();
                    }
                    FragmentTransaction fragmentTransaction1= getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.fragmentlayout,f1);
                    fragmentTransaction1.commit();

                    return true;
                case R.id.navigation_dashboard:
                    Log.e("f2","f2");
                    if(f2==null){
                        f2=new F2();
                    }
                    FragmentTransaction fragmentTransaction2= getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.fragmentlayout,f2);
                    fragmentTransaction2.commit();

                    return true;
                case R.id.navigation_notifications:
                    Log.e("f3","f3");
                    if(f3==null){
                        f3=new F3();
                    }
                    FragmentTransaction fragmentTransaction3= getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.fragmentlayout,f3);
                    fragmentTransaction3.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottion_navigation);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        f1=new F1();
        fragmentTransaction.add(R.id.fragmentlayout,f1);
        fragmentTransaction.commit();


    }

}

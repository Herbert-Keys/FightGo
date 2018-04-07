package com.example.herbert.fightgo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.herbert.fightgo.Model.RecyclerItemModel;
import com.example.herbert.fightgo.activity.BottomNavigationActivity;
import com.example.herbert.fightgo.activity.FullscreenActivity;
import com.example.herbert.fightgo.activity.JPushActivity;
import com.example.herbert.fightgo.activity.ScrollingActivity;
import com.example.herbert.fightgo.activity.TabLayoutActivity;
import com.example.herbert.fightgo.activity.UmengShareActivity;
import com.example.herbert.fightgo.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    ActionBarDrawerToggle toggle;
    @BindView(R.id.recycview)
    RecyclerView recycview;
    private List<RecyclerItemModel> data=new ArrayList<>();
    Toolbar toolbar;
    private int height;
    private int width;
    FloatingActionButton fab;
    Button bt_login;
    DrawerLayout drawer;
    ProgressDialog  progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        requestPemissions();
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        height=getWindowManager().getDefaultDisplay().getHeight();
        width=getWindowManager().getDefaultDisplay().getWidth();
        fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        drawer= (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button bt_toSrcollingActivity = findViewById(R.id.bt_toSrcollingActivity);
        Button bt_bottomNavigationActivity = findViewById(R.id.bt_bottomNavigationActivity);
        Button bt_FullScreenActivity = findViewById(R.id.bt_FullScreenActivity);
        bt_toSrcollingActivity.setOnClickListener(this);
        bt_bottomNavigationActivity.setOnClickListener(this);
        bt_FullScreenActivity.setOnClickListener(this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recycview.setLayoutManager(gridLayoutManager);
        initRecyclerData();
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(data, this);
        myRecyclerViewAdapter.setOnRecyclerViewItemClickListenner(new MyRecyclerViewAdapter.OnRecyclerViewItemClickListenner() {
            @Override
            public void onItemClick(View view, int position) {
                switch (data.get(position).getTitle()){
                    case "ScrollingActivity":
                        startActivity(new Intent(MainActivity.this,ScrollingActivity.class));
                        break;
                    case "FullSreenActivity":
                        startActivity(new Intent(MainActivity.this,FullscreenActivity.class));
                        break;
                    case "BottomNavigationActivity":
                        startActivity(new Intent(MainActivity.this,BottomNavigationActivity.class));
                        break;
                    case "TabLayout":
                        startActivity(new Intent(MainActivity.this,TabLayoutActivity.class));
                        break;
                    case "UmengShareActivity":
                        startActivity(new Intent(MainActivity.this,UmengShareActivity.class));
                        break;
                    case "JPushActivity":
                        startActivity(new Intent(MainActivity.this, JPushActivity.class));
                        break;
                }
            }

        });
        recycview.setAdapter(myRecyclerViewAdapter);
        recycview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

        initDialog();
    }

    private void initDialog() {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("正在加载。。");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    private void initRecyclerData() {
        data.add(new RecyclerItemModel("ScrollingActivity",R.mipmap.red));
        data.add(new RecyclerItemModel("FullSreenActivity",R.mipmap.green));
        data.add(new RecyclerItemModel("BottomNavigationActivity",R.mipmap.yellow));
        data.add(new RecyclerItemModel("TabLayout",R.mipmap.black));
        data.add(new RecyclerItemModel("UmengShareActivity",R.mipmap.red));
        data.add(new RecyclerItemModel("JPushActivity",R.mipmap.green));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                progressDialog.dismiss();
                return true;
            case R.id.action_popup_window:
                showPW();
                return true;
            case android.R.id.home:
                toggle.onOptionsItemSelected(item);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPW() {
        final View inflate = LayoutInflater.from(this).inflate(R.layout.popup_layout, null, false);
        bt_login=inflate.findViewById(R.id.bt_login);
        final TextView username = inflate.findViewById(R.id.username);
        final TextView password = inflate.findViewById(R.id.password);
        final PopupWindow popupWindow=new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setWidth(600);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
        popupWindow.setOutsideTouchable(false);
        //popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.animTranslate);
        //popupWindow.showAsDropDown(fab,100,0);
        popupWindow.showAtLocation(toolbar, Gravity.CENTER, 0, 0);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("aa".equals(username.getText().toString())&&"123".equals(password.getText().toString())){
                    Snackbar.make(drawer,"登陆成功",Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    }).show();
                }else{
                    Snackbar.make(drawer,"登陆失败",Snackbar.LENGTH_SHORT).setAction("算了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    }).show();
                }

            }
        });

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_toSrcollingActivity:
                startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
                break;
            case R.id.bt_bottomNavigationActivity:
                startActivity(new Intent(MainActivity.this, BottomNavigationActivity.class));
                break;
            case R.id.bt_FullScreenActivity:
                startActivity(new Intent(MainActivity.this, FullscreenActivity.class));
                break;
            default:
                break;
        }

    }

    private void requestPemissions() {
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

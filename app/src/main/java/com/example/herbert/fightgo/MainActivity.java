package com.example.herbert.fightgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.herbert.fightgo.Model.RecyclerItemModel;
import com.example.herbert.fightgo.activity.BottomNavigationActivity;
import com.example.herbert.fightgo.activity.FullscreenActivity;
import com.example.herbert.fightgo.activity.ScrollingActivity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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


    }

    private void initRecyclerData() {
        data.add(new RecyclerItemModel("ScrollingActivity",R.mipmap.red));
        data.add(new RecyclerItemModel("FullSreenActivity",R.mipmap.green));
        data.add(new RecyclerItemModel("BottomNavigationActivity",R.mipmap.yellow));
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
                return true;

            case android.R.id.home:
                toggle.onOptionsItemSelected(item);
                break;
        }

        return super.onOptionsItemSelected(item);
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
}

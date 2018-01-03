package com.mert.secretofcolors;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 1);
            }
        }

        HomeFragment home=new HomeFragment();
        final FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main,home).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //renkdizi=asd.setDiziMod2();
            HomeFragment home=new HomeFragment();
            final FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,home).commit();

        }

        else if (id == R.id.nav_camera) { //mod1
            Mod1 mod1=new Mod1();
            final FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,mod1).commit();
        } else if (id == R.id.nav_camera1) {
            Mod4 mod4=new Mod4();
            final FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,mod4).commit();

        }else if (id == R.id.nav_gallery) {
            Mod2 mod2=new Mod2();
            final FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,mod2).commit();

        } else if (id == R.id.nav_mod3) {
            Mod3 mod3=new Mod3();
            final FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,mod3).commit();

        } else if (id== R.id.nav_oyla){
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        } else if (id== R.id.nav_hakkinda){
            Intent intent = new Intent(this,Hakkimizda.class);
            startActivity(intent);
        } else if (id== R.id.nav_cikis){
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

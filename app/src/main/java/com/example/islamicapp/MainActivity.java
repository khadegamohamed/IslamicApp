package com.example.islamicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.Menu;

import com.example.islamicapp.data.prayersNotification.AzanPreyerUtil;
import com.example.islamicapp.data.prayersNotification.RegisterPrayerTimesWorker;
import com.example.islamicapp.databinding.ActivityMainBinding;

import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
     private  ActivityMainBinding binding;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.quranIndexFragment,R.id.azkarHomeFragment2,R.id.prayerTimesFragment)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController = Navigation
                .findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
       AzanPreyerUtil.registerPrayer(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
      NavController navController =Navigation.
              findNavController(this,R.id.nav_host_fragment_content_main);
return NavigationUI.navigateUp(navController,appBarConfiguration)
        || super.onSupportNavigateUp();
    }
}
package com.uc.sejarahkita_mobile.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.uc.sejarahkita_mobile.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    NavHostFragment navHostFragment;
    NavController navController;
    Toolbar toolbar;

    boolean clickedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.bottom_nav_menu);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.gameFragment, R.id.leaderboardFragment, R.id.profileFragment).build();
//        NavigationUI.setupActionBarWithNavController(this, navHostFragment.getNavController(), appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navHostFragment.getNavController());

        navHostFragment.getNavController().addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.gameFragment || destination.getId() == R.id.leaderboardFragment || destination.getId() == R.id.profileFragment) {
                navigationView.setVisibility(View.VISIBLE);
            } else if (destination.getId() == R.id.countdownFragment) {
//                toolbar.setVisibility(View.GONE);
                navigationView.setVisibility(View.GONE);
            } else {
                navigationView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navHostFragment.getNavController().navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (clickedOnce) {
            super.onBackPressed();
            return;
        }
        this.clickedOnce = true;
        Snackbar.make(findViewById(android.R.id.content), "Double Click to Exit", Snackbar.LENGTH_LONG)
                .show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                clickedOnce = false;
            }
        }, 2000);
    }
}
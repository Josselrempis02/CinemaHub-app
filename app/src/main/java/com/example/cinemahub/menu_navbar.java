package com.example.cinemahub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class menu_navbar extends AppCompatActivity {
    private static final String TAG = "menu_navbar";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;

    FirebaseAuth auth;
    TextView emailView;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_navbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);

        // Set default fragment
        if (savedInstanceState == null) {
            loadFragment(new Home());
            navigationView.setCheckedItem(R.id.home);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment selectedFragment = null;

                if (id == R.id.home) {
                    Toast.makeText(menu_navbar.this, "Home Selected", Toast.LENGTH_SHORT).show();
                    selectedFragment = new Home();
                } else if (id == R.id.profile) {
                    Toast.makeText(menu_navbar.this, "Profile Selected", Toast.LENGTH_SHORT).show();
                    selectedFragment = new Profile();
                } else if (id == R.id.logout) {
                    Toast.makeText(menu_navbar.this, "Logout Selected", Toast.LENGTH_SHORT).show();
                    auth.signOut();
                    Intent intent = new Intent(menu_navbar.this, Login.class);
                    startActivity(intent);
                    finish(); // Close the current activity
                }

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        View headerView = navigationView.getHeaderView(0);
        emailView = headerView.findViewById(R.id.user_email);

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            String email = user.getEmail();
            Log.d(TAG, "User email: " + email);
            emailView.setText(email);
        }
    }

    void loadFragment(Fragment fragment) {
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e) {
            Log.e(TAG, "Error loading fragment", e);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

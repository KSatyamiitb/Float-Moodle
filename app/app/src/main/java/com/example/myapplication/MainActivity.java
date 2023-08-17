package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static DBHelper DB;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TabLayout mTabLayout;
    TabItem study_plan,assignment,exam,lecture;


    private final String[] PAGE_TITLES = new String[]{
            "Study Plan",
            "Assignment",
            "Exam",
            "Lecture"
    };

    private final Fragment[] PAGES = new Fragment[]{
            new studyplan(),
            new assignment(),
            new exam(),
            new lecture()
    };

    private ViewPager pager;

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(pager);
        study_plan = findViewById(R.id.study_plan);
        assignment = findViewById(R.id.assignment);
        exam = findViewById(R.id.exam);
        lecture = findViewById(R.id.lecture);

        DB = new DBHelper(this);

        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


    }

    public void insert(View view){
        Intent insertIntent = new Intent(this, Feed.class);
        startActivity(insertIntent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.Home){
            Toast.makeText(this, "Home Dashboard", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent calendarIntent = new Intent(this, Calendar.class);
            startActivity(calendarIntent);
        }
        return false;
    }


    public class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }
    }
}
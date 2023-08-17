package com.example.myapplication;

        import androidx.annotation.NonNull;
        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;

        import android.annotation.SuppressLint;
        import android.app.usage.UsageEvents;
        import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Color;
        import android.icu.text.SimpleDateFormat;
        import android.os.Build;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.github.sundeepk.compactcalendarview.CompactCalendarView;
        import com.github.sundeepk.compactcalendarview.domain.Event;
        import com.google.android.material.navigation.NavigationView;

        import java.text.DateFormat;
        import java.text.ParseException;
        import java.util.ConcurrentModificationException;
        import java.util.Date;
        import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)

public class Calendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView selected_date,studyplan_count,assignment_count,exam_count,lecture_count,total_count;
    public static CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    String TAG = "Calendar";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        selected_date = findViewById(R.id.selected_date);
        studyplan_count = findViewById(R.id.studyplan_count);
        assignment_count = findViewById(R.id.assignment_count);
        exam_count = findViewById(R.id.exam_count);
        lecture_count = findViewById(R.id.lecture_count);
        total_count = findViewById(R.id.total_count);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        Date d = java.util.Calendar.getInstance().getTime();
        actionBar.setTitle(dateFormatMonth.format(d));
        DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(d);
        selected_date.setText(strDate);

        Cursor c;
        c = MainActivity.DB.read();
        if(c.getCount() != 0) {
            while(c.moveToNext()){
                try {
                    Long millis = new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(2)).getTime();
                    Event ev = new Event(Color.BLUE,millis,"Study Plan");
                    compactCalendar.addEvent(ev);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(dateClicked);

                selected_date.setText(strDate);
                studyplan_count.setText("Study Plan_________________"+ MainActivity.DB.studyplan_count_date(strDate));
                assignment_count.setText("Assignment________________"+ MainActivity.DB.assignment_count_date(strDate));
                exam_count.setText("Exam______________________"+ MainActivity.DB.exam_count_date(strDate));
                lecture_count.setText("Lecture____________________"+ MainActivity.DB.lecture_count_date(strDate));
                total_count.setText("Total______________________"+ MainActivity.DB.count_date(strDate));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
                onDayClick(firstDayOfNewMonth);
            }
        });

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.Home){
            Toast.makeText(this, "Home Dashboard", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Calendar", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
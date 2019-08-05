package beni.simulatorpremi.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import beni.simulatorpremi.R;

public class ResultAstor extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    ScrollView scrollView;
    TextView teksaja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_astor);
        scrollView =  (ScrollView) findViewById(R.id.sv);
        teksaja = (TextView) findViewById(R.id.teksaja);

        Intent intent = getIntent();
        String tr = intent.getExtras().getString("tr");
        teksaja.setText(tr);
    }
}

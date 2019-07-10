package beni.simulatorpremi.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;

import beni.simulatorpremi.R;

public class ResultAstor extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_astor);
        tabLayout = (TabLayout) findViewById(R.id.tabresult);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter =  new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentMaxPremi(), "MaxPremi");
        adapter.AddFragment(new FragmentMinPremi(), "MinPremi");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}

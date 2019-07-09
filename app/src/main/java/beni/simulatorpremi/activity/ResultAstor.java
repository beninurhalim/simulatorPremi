package beni.simulatorpremi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import beni.simulatorpremi.R;
import beni.simulatorpremi.util.SharedPrefManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultAstor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultsimulation);
    }
}
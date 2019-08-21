package beni.simulatorpremi.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import beni.simulatorpremi.R;
import beni.simulatorpremi.activity.Aspri.AspriSimulation;
import beni.simulatorpremi.activity.Astor.Astorsimulation;
import beni.simulatorpremi.util.SharedPrefManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuButton extends Activity {
     Button intentastor;
     Button intentaspri;
     ViewGroup popupview;
     ImageButton ibtn;
     ImageButton ibtn2;
     ScrollView scrollAstor;
     ScrollView scrollAspri;
     TextView astordes;

     Animation anim;

    @BindView(R.id.tvResultNama)
    TextView tvResultNama;
    @BindView(R.id.btnLogout)
    ImageButton btnLogout;

    SharedPrefManager sharedPrefManager;
    boolean doubleBackToExitPressedOnce = false;

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_button);
        popupview = (ViewGroup) findViewById(R.id.con);
        scrollAstor=(ScrollView) findViewById(R.id.scrollastor);
        scrollAstor.setVisibility(View.INVISIBLE);
        scrollAspri=(ScrollView) findViewById(R.id.scrollaspri);
        scrollAspri.setVisibility(View.INVISIBLE);
        astordes=(TextView) findViewById(R.id.astordes1);
        ibtn = (ImageButton) findViewById(R.id.btnastor) ;
        ButterKnife.bind(this);
        sharedPrefManager = new SharedPrefManager(this);

        anim = AnimationUtils.loadAnimation(this,R.xml.animasi);

        tvResultNama.setText(sharedPrefManager.getSPNama());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(MenuButton.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        ibtn =(ImageButton) findViewById(R.id.btnastor);
        ibtn2 =(ImageButton) findViewById(R.id.btnaspri);

        intentastor =(Button) findViewById(R.id.lanjut);
        intentastor.setVisibility(View.INVISIBLE);

        intentaspri =(Button) findViewById(R.id.lanjut2);
        intentaspri.setVisibility(View.INVISIBLE);
//        popupview = LayoutInflater.from(this).inflate(R.layout.popup_astor, null);

            ibtn.setOnClickListener(new View.OnClickListener(){
                boolean visible;
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v){
                    intentastor.setBackgroundColor(Color.parseColor("#d3263e"));
//                    ibtn.setBackgroundColor(Color.parseColor("#80D86C6C"));
//                    ibtn2.setBackgroundColor(Color.parseColor("#11D86C6C"));
                    TransitionManager.beginDelayedTransition(popupview);
                    visible = !visible;
                    scrollAspri.setVisibility(View.INVISIBLE);
                    intentaspri.setVisibility(View.INVISIBLE);
                    scrollAstor.setVisibility(View.VISIBLE);
                    intentastor.setVisibility(View.VISIBLE);
                    ibtn.startAnimation(anim);
                    ibtn2.clearAnimation();
                    intentastor.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){

                            Intent i = new Intent(MenuButton.this, Astorsimulation.class);
                            startActivity(i);
                            }
                        });
                    }
                });


        ibtn2.setOnClickListener(new View.OnClickListener(){
            boolean visible;
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v){
                intentaspri.setBackgroundColor(Color.parseColor("#18b11f"));
//                ibtn2.setBackgroundColor(Color.parseColor("#80D86C6C"));
//                ibtn.setBackgroundColor(Color.parseColor("#11D86C6C"));
                TransitionManager.beginDelayedTransition(popupview);
                visible = !visible;
                scrollAstor.setVisibility(View.INVISIBLE);
                intentastor.setVisibility(View.INVISIBLE);
                scrollAspri.setVisibility(View.VISIBLE);
                intentaspri.setVisibility(View.VISIBLE);
                ibtn2.startAnimation(anim);
                ibtn.clearAnimation();
                intentaspri.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){

                        Intent i = new Intent(MenuButton.this, AspriSimulation.class);
                        startActivity(i);
                    }
                });
            }
        });





    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
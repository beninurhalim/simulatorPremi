package beni.simulatorpremi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import beni.simulatorpremi.R;
import beni.simulatorpremi.util.SharedPrefManager;
import butterknife.BindView;
import butterknife.ButterKnife;

//import android.service.autofill.OnClickAction;

public class MenuButton extends Activity {

     Button btn;
     Button btn2;
     TextView txt;
     Button intentastor;
     Button intentastor2;
     View popupview;
     ImageButton ibtn;
     ImageButton ibtn2;
     ScrollView scroll;
     TextView astordes;

    @BindView(R.id.tvResultNama)
    TextView tvResultNama;
    @BindView(R.id.btnLogout)
    ImageButton btnLogout;

    SharedPrefManager sharedPrefManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_button);
        scroll=(ScrollView) findViewById(R.id.scroll);
        astordes=(TextView) findViewById(R.id.astordes1);
        ButterKnife.bind(this);
        sharedPrefManager = new SharedPrefManager(this);

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
        txt =(TextView) findViewById(R.id.deskripsi);
        intentastor =(Button) findViewById(R.id.lanjut);
        intentastor.setEnabled(false);
//        popupview = LayoutInflater.from(this).inflate(R.layout.popup_astor, null);

            ibtn.setOnClickListener(new View.OnClickListener(){

                {
                    intentastor.setEnabled(true);
                }
            @Override
            public void onClick(View v){


                txt.setText("Ästor adalah asuransi bla bla");
                intentastor.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){

                        Intent i = new Intent(MenuButton.this,Astorsimulation.class);
                        startActivity(i);
                        }
                    });
                }
            });


        ibtn2.setOnClickListener(new View.OnClickListener(){

            {
                intentastor.setEnabled(true);
            }
            @Override
            public void onClick(View v){

                txt.setText("Ästor adalah asuransi bla bla bla");
                intentastor.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){

                        Intent i = new Intent(MenuButton.this,SplashScreen.class);
                        startActivity(i);
                    }
                });
            }
        });





    }

}
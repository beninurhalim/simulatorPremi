package beni.simulatorpremi.activity.Aspri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import beni.simulatorpremi.R;
import beni.simulatorpremi.fragment.Astor.FragMaxAstor;
import beni.simulatorpremi.fragment.Astor.FragMinAstor;

public class ResultAspri extends AppCompatActivity {
    private static FragmentManager fragmentManager;
    TextView MainData,totalPremi, Meninggal, Cacat, Medis, Evakuasi,Rawatinap;

    String paket;
    String kelas;
    String nsm;
    String periode;
    String totalpremi, meninggal,cacat,medis,evakuasi,rawatinap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_aspri);
        fragmentManager = getSupportFragmentManager();//Get Fragment Manager

        MainData = (TextView) findViewById(R.id.mainData);
        totalPremi = (TextView) findViewById(R.id.totalpremi);
        Meninggal = (TextView) findViewById(R.id.meninggal);
        Cacat = (TextView) findViewById(R.id.cacattetap);
        Medis = (TextView) findViewById(R.id.medis);
        Evakuasi = (TextView) findViewById(R.id.evakuasi);
        Rawatinap = (TextView) findViewById(R.id.rawatinap);
//        rincianPremi = (ScrollView) findViewById(R.id.rincianPremi);

        getDataIntent();

    }

    void getDataIntent(){

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("Rp ###,###,###,###", symbols);

        Intent intent = getIntent();
        paket = intent.getExtras().getString("paket");
        kelas = intent.getExtras().getString("kelas");
        periode = intent.getExtras().getString("periode");
        nsm = df.format(intent.getExtras().getInt("nsm"));
//        meninggal = df.format(intent.getExtras().getString("meninggal"));
//        rincian = intent.getExtras().getString("rincian");
        totalpremi = intent.getExtras().getString("totalpremi");
        meninggal = intent.getExtras().getString("meninggal");
        medis = intent.getExtras().getString("medis");
        cacat = intent.getExtras().getString("cacat");
        evakuasi = intent.getExtras().getString("evakuasi");
        rawatinap = intent.getExtras().getString("rawatinap");

        MainData.setText(": "+paket +"\n" +
                         ": "+ kelas +"\n" +
                         ": "+ nsm +"\n" +
                         ": "+ periode +"\n");

        totalPremi.setText(totalpremi);
        Meninggal.setText(meninggal);
        Cacat.setText(cacat);
        Medis.setText(medis);
        Evakuasi.setText(evakuasi);
        Rawatinap.setText(rawatinap);
    }
}
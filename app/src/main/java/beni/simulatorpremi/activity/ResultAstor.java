package beni.simulatorpremi.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import beni.simulatorpremi.R;

public class ResultAstor extends AppCompatActivity implements View.OnClickListener{
    private static Button btnMin, btnMax;
    private static FragmentManager fragmentManager;
    TextView MainData;

    String zona;
    String hk;
    String tahun;
    String tra;
    String trb;
    String tr;
    String tr1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_astor);

        fragmentManager = getSupportFragmentManager();//Get Fragment Manager

        //Find Ids
        btnMin = (Button) findViewById(R.id.bfmin);
        btnMax = (Button) findViewById(R.id.bfmax);
        MainData = (TextView) findViewById(R.id.mainData);

        //Implement Click Listener
        btnMin.setOnClickListener(this);
        btnMax.setOnClickListener(this);

        getDataIntent();
        muncul();

//        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
//        tx.replace(R.id.fragmentContainer, new FragMinPremi());
//        tx.commit();
    }

    void muncul(){
        Fragment argumentFragment = new FragMinPremi();//Get Fragment Instance
        Bundle data = new Bundle();//Use bundle to pass data
        data.putString("trb", trb);//put string, int, etc in bundle with a key value
        data.putString("tr", tr);//put string, int, etc in bundle with a key value
        argumentFragment.setArguments(data);//Finally set argument bundle to fragment

        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, argumentFragment).commit();
    }

    void getDataIntent(){

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("Rp ###,###,###,###", symbols);

        Intent intent = getIntent();
        hk = df.format(intent.getExtras().getInt("hk"));
        tahun = intent.getExtras().getString("tahun");
        zona = intent.getExtras().getString("zona");
        tra = intent.getExtras().getString("tra");
        trb = intent.getExtras().getString("trb");
        tr = intent.getExtras().getString("tr");
        tr1 = intent.getExtras().getString("tr1");

        MainData.setText("Harga Kendaraan\t\t\t"+": "+hk +"\n" +
                         "Tahun Pembuatan \t\t"+": "+ tahun +"\n" +
                         zona);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bfmin:
                Fragment argumentFragment = new FragMinPremi();//Get Fragment Instance
                Bundle data = new Bundle();//Use bundle to pass data
                data.putString("trb", trb);//put string, int, etc in bundle with a key value
                data.putString("tr", tr);//put string, int, etc in bundle with a key value
                argumentFragment.setArguments(data);//Finally set argument bundle to fragment

                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, argumentFragment).commit();//now replace the argument fragment

                //Replace default fragment
//                fm.beginTransaction().replace(R.id.fragmentContainer, new FragMinPremi()).commit();
                break;
            case R.id.bfmax:

                Fragment argumentFragment1 = new FragMaxPremi();//Get Fragment Instance
                Bundle data1 = new Bundle();//Use bundle to pass data
                data1.putString("tra", tra);//put string, int, etc in bundle with a key value
                data1.putString("tr1", tr1);//put string, int, etc in bundle with a key value
                argumentFragment1.setArguments(data1);//Finally set argument bundle to fragment

                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, argumentFragment1).commit();//now replace the argument fragment

                /**  Note: If you are passing argument in fragment then don't use below code always replace fragment instance where we had set bundle as argument as we had done above else it will give exception  **/
//                   fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new FragMaxPremi()).commit();
                break;
        }

    }
}
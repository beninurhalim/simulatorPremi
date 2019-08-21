package beni.simulatorpremi.activity.Aspri;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import beni.simulatorpremi.model.Aspri.aspriModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import beni.simulatorpremi.R;
import beni.simulatorpremi.model.Aspri.dataAspri;
import beni.simulatorpremi.util.api.BaseApiService;
import beni.simulatorpremi.util.api.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AspriSimulation extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    int iEvakuasiF, iCacatTetapF, iPerawatanMedisF,year, year2, iKelas;
    double nilaiSantunan;
    String meninggal, cacat, medis, evakuasi, rawatinap,res;
    String sKelas,rincian,TotalPremi;

    TextView lemparA,lemparB;
    EditText SDate, EDate;
//    private TextInputEditText NilaiSantunanMax;
    EditText NilaiSantunanMax;
    Spinner ProductID;
    Spinner Kelas;
    CheckBox EvakuasiF, CacatTetapF, PerawatanMedisF;
    Button submit;
    DatePickerDialog.OnDateSetListener mDateSetListeners, mDateSetListenerf;
    ProgressDialog loading;
    dataAspri respon;

    BaseApiService mApiService;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asprisimulation);

        SDate = (EditText) findViewById(R.id.sDate);
        SDate.setShowSoftInputOnFocus(false);
        EDate = (EditText) findViewById(R.id.eDate);
        EDate.setShowSoftInputOnFocus(false);
//        NilaiSantunanMax = (TextInputEditText) findViewById(R.id.nilaiSantunanMax);
        NilaiSantunanMax = (EditText) findViewById(R.id.nilaiSantunanMax);
        NilaiSantunanMax.addTextChangedListener(onTextChangedListener());
        ProductID = (Spinner) findViewById(R.id.productId);
        Kelas = (Spinner) findViewById(R.id.kelas);
        EvakuasiF = (CheckBox) findViewById(R.id.evakuasiF);
        CacatTetapF = (CheckBox) findViewById(R.id.cacatTetapF);
        PerawatanMedisF = (CheckBox) findViewById(R.id.perawatanMedisF);
        submit = (Button) findViewById(R.id.submit);
        lemparA = (TextView) findViewById(R.id.lempara);
        lemparB = (TextView) findViewById(R.id.lemparb);

        findViewById(R.id.cons).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

        EDate.setEnabled(false);
        tempData();
        Klik();
    }

    void ValidasiKirim(){
//        CHECHBOX
        if (EvakuasiF.isChecked()) {
            iEvakuasiF = 1;
        } else {
            iEvakuasiF = 0;
        }
        if (CacatTetapF.isChecked()) {
            iCacatTetapF = 1;
        } else {
            iCacatTetapF = 0;
        }
        if (PerawatanMedisF.isChecked()) {
            iPerawatanMedisF = 1;
        } else {
            iPerawatanMedisF = 0;
        }
//      Validasi Spinner
        sKelas =Kelas.getSelectedItem().toString();
        if (sKelas.equals("Kelas I")) {
            iKelas = 1;
        } else if (sKelas.equals("Kelas II")) {
            iKelas = 2;
        } else if (sKelas.equals("Kelas III")){
            iKelas = 3;
        }else if (sKelas.equals("Kelas IV")){
            iKelas = 4;
        }else {
            iKelas = 5;
        }

    }

    void Klik(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidasiKlik();
            }
        });
    }

    void saveData(){
        aspriModel am = new aspriModel(
                SDate.getText().toString(),
                EDate.getText().toString(),
                Integer.parseInt((NilaiSantunanMax).getText().toString().replaceAll(",", "")),
//                Float.parseFloat((NilaiSantunanMax).getText().toString().replaceAll(",","")),
                ProductID.getSelectedItem().toString(),
                Integer.toString(iKelas),
                iEvakuasiF,
                iCacatTetapF,
                iPerawatanMedisF
        );

        mApiService = UtilsApi.getAPIService2(); // meng-init yang ada di package apihelper
        Call<aspriModel> call = mApiService.postAspri(am);
        //calling API
        call.enqueue(new Callback<aspriModel>() {
            @Override
            public void onResponse(Call<aspriModel> call, Response<aspriModel> response) {
                try {
                    loading.dismiss();
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat df = new DecimalFormat("Rp ###,###,###,###", symbols);
                    rincian = "";
                    TotalPremi = "";
                    meninggal = "";
                    if (response.isSuccessful()){
                        loading.dismiss();
                        respon = response.body().getData();
                        TotalPremi = "Total Premi : "+df.format(respon.getTotalPremi())+"\n";
                        meninggal = df.format(respon.getSi_meninggal());
                        cacat = df.format(respon.getSi_cacat());
                        medis = df.format(respon.getSi_medis());
                        evakuasi = df.format(respon.getSi_evakuasi());
                        rawatinap = df.format(respon.getSi_rawatinap());
//                    rincian = "Total Premi : "+df.format(respon.getTotalPremi())+"\n";
//                    rincian += "Premi Meninggal : "+df.format(respon.getPremi_Meninggal())+"\n";
//                    rincian += "Premi Cacat : "+df.format(respon.getPremi_Cacat())+"\n";
//                    rincian += "Premi Biaya Medis : "+df.format(respon.getPremi_BiayaMedis())+"\n";
//                    rincian += "Premi Evakuasi : "+df.format(respon.getPremi_Evakuasi())+"\n";
//                    rincian += "Premi Rawat Inap : "+df.format(respon.getPremi_RawatInap())+"\n";
                        res = response.body().getAlerts().getMessage();
                        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();

                    }
                    lemparA.setText(TotalPremi);

                    final String paket = ProductID.getSelectedItem().toString();
                    final String kelas = Kelas.getSelectedItem().toString();
                    String nsm = NilaiSantunanMax.getText().toString().replaceAll(",", "");
                    final int nilaiSm = Integer.parseInt(nsm);
                    final String date = SDate.getText().toString() +" \t-\t "+ EDate.getText().toString();

                    lemparA.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lemparA.setText("");
                        }
                    }, 2000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(AspriSimulation.this, ResultAspri.class);

                            i.putExtra("paket", paket);
                            i.putExtra("kelas", kelas);
                            i.putExtra("nsm", nilaiSm);
                            i.putExtra("periode", date);
                            i.putExtra("meninggal", meninggal);
                            i.putExtra("cacat", cacat);
                            i.putExtra("medis", medis);
                            i.putExtra("evakuasi", evakuasi);
                            i.putExtra("rawatinap", rawatinap);
//                        i.putExtra("rincian", lemparA.getText().toString());
                            i.putExtra("totalpremi", lemparA.getText().toString());

                            loading.dismiss();
                            startActivity(i);
                        }
                    }, 500);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),response.body().getAlerts().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<aspriModel> call, Throwable t) {
                Log.e("ASPRI",t.getMessage());
                Toast.makeText(getApplicationContext(), "Koneksi Bermasalah atau\nNilai Santunan harus bilangan bulat", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    void ValidasiKlik(){
        String S = SDate.getText().toString();
        String nsm = NilaiSantunanMax.getText().toString();
        String dnsm = NilaiSantunanMax.getText().toString().replaceAll(",","");
        try {
            int insm = Integer.parseInt(dnsm);
            if (nsm.isEmpty()){
                Toast.makeText(getApplicationContext(), "Mohon isi tanggal mulai", Toast.LENGTH_SHORT).show();
            }else if (S.isEmpty()){
                Toast.makeText(getApplicationContext(), "Isi Nilai Santunan", Toast.LENGTH_SHORT).show();
            }else if (insm > 1000000000){
                Toast.makeText(getApplicationContext(), "Nilai Santunan Max 1 Milyar", Toast.LENGTH_SHORT).show();
            }else{
                ValidasiKirim();
                loading=ProgressDialog.show(this,null,"Harap Tunggu", true, false);
                saveData();
            }
        }catch (NumberFormatException ex){
            Toast.makeText(getApplicationContext(), "Data belum terisi / Nilai Santunan Salah", Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(getApplicationContext(), insm, Toast.LENGTH_SHORT).show();

    }

    void tempData(){

//        GetCalendar
        SDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        AspriSimulation.this,
                        mDateSetListeners,
                        year, month, day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListeners = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                year2 = year + 1;
                String date2 = month + "/" + day + "/" + year2;
                SDate.setText(date);
                EDate.setText(date2);
            }
        };

        EDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR)+1;
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AspriSimulation.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerf,
                        year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListenerf = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                EDate.setText(date);
            }
        };
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                NilaiSantunanMax.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    NilaiSantunanMax.setText(formattedString);
                    NilaiSantunanMax.setSelection(NilaiSantunanMax.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                NilaiSantunanMax.addTextChangedListener(this);
            }
        };
    }

}

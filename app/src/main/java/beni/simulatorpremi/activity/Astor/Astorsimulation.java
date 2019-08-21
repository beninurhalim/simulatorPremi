package beni.simulatorpremi.activity.Astor;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beni.simulatorpremi.model.Astor.additionalModel;
import beni.simulatorpremi.model.Astor.kendaraanModel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import beni.simulatorpremi.R;
import beni.simulatorpremi.util.api.BaseApiService;
import beni.simulatorpremi.util.api.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Astorsimulation extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    int sFlood, sEQ, sSRCC, sTS, sZona, sTjh, sTjhp, sPAP, sPAD, nilaitjh, nilaiPA, year, year2;
    String RUsage, res;
    kendaraanModel respon;
    Spinner VehicleType;
    TextInputEditText ManufactureYear, tjh_amount, PA_amount;
    EditText SDate;
    EditText EDate;
    DatePickerDialog.OnDateSetListener mDateSetListeners;
    DatePickerDialog.OnDateSetListener mDateSetListenerf;
    Spinner Zona;
    TextInputEditText TSI;
    Spinner Coverages;
    CheckBox tjh, flood, tjh_passanger, EQ, SRCC, TS, PAPassager, PADriver;
    SeekBar seekBar1;
    TextView  lemparA,lemparB,lemparMin,lemparMax;
    Button lanjut;
    Button sbutton;
    RadioGroup Usage;
    RadioButton rbPribadi, rbKomersil;
    RadioButton radioButton;
    BaseApiService mApiService;
    String tr="";
    String tr1="";
    String trA="";
    String trB="";
    String r="";
    String zzona;
    ProgressDialog loading;
    int TahunIni = Calendar.getInstance().get(Calendar.YEAR);
    String sTahunIni = Integer.toString(TahunIni);


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astorsimulation);

        ManufactureYear = (TextInputEditText) findViewById(R.id.ManufactureYear);
        VehicleType = (Spinner) findViewById(R.id.VehicleType);
        SDate = (EditText) findViewById(R.id.SDate);
        SDate.setShowSoftInputOnFocus(false);
        EDate = (EditText) findViewById(R.id.EDate);
        EDate.setShowSoftInputOnFocus(false);
      //  seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        tjh = (CheckBox) findViewById(R.id.tjh);
        tjh_amount = (TextInputEditText) findViewById(R.id.tjh_amount);
        tjh_amount.addTextChangedListener(onTextChangedListener());
        tjh_passanger = (CheckBox) findViewById(R.id.tjh_passanger);
        sbutton = (Button) findViewById(R.id.sbutton);
        lanjut = (Button) findViewById(R.id.lanjut);
        Usage = (RadioGroup) findViewById(R.id.Usage);
        rbPribadi = (RadioButton) findViewById(R.id.rbPribadi);
        rbKomersil = (RadioButton) findViewById(R.id.rbKomersial);
        Coverages = (Spinner) findViewById(R.id.Coverages);
        Zona = (Spinner) findViewById(R.id.Zona);
        TSI = (TextInputEditText) findViewById(R.id.TSI);
        TSI.addTextChangedListener(onTextChangedListener());
        flood = (CheckBox) findViewById(R.id.flood);
        EQ = (CheckBox) findViewById(R.id.EQ);
        SRCC = (CheckBox) findViewById(R.id.SRCC);
        TS = (CheckBox) findViewById(R.id.TS);
        lemparA = (TextView) findViewById(R.id.lempara);
        lemparB = (TextView) findViewById(R.id.lemparb);
        lemparMin = (TextView) findViewById(R.id.lemparMin);
        lemparMax = (TextView) findViewById(R.id.lemparMax);
        PA_amount = (TextInputEditText) findViewById(R.id.PA_amount);
        PA_amount.addTextChangedListener(onTextChangedListener());
        PAPassager = (CheckBox) findViewById(R.id.PAPassanger);
        PADriver = (CheckBox) findViewById(R.id.PADriver);
        ManufactureYear.setText(sTahunIni);

        findViewById(R.id.cons).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });
//        seekBar1.setEnabled(false);

//        tjh_amount.setEnabled(false);
        PA_amount.setEnabled(false);
//        tjh_passanger.setEnabled(false);
        tempData();

        Klik();

    }

    void Klik() {
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidasiKlik();
            }
        });
    }

    void ValidasiKlik(){
        String S = SDate.getText().toString();
        String TK = ManufactureYear.getText().toString();
        String TS = TSI.getText().toString();
        int TKI = Integer.parseInt(ManufactureYear.getText().toString());
        int hasil = TahunIni-TKI;
        String TahunKendaraan = ManufactureYear.getText().toString();

        try {
            if (TK.equals("")) {
                Toast.makeText(getApplicationContext(), "Isi Tahun Kendaraan (MAX 10 Tahun)", Toast.LENGTH_SHORT).show();
            }else if (TKI > TahunIni) {
                Toast.makeText(getApplicationContext(),"Maksimal Kendaraan Tahun "+TahunIni, Toast.LENGTH_SHORT).show();
            }else if (TahunIni - TKI >= 50) {
                Toast.makeText(getApplicationContext(),"Mobil Lu Tua Amat Tahun "+TK, Toast.LENGTH_SHORT).show();
            }else if (TahunIni - TKI >= 11) {
                Toast.makeText(getApplicationContext(),"Tidak Bisa Karena Usia Kendaraan Anda "+hasil+" Tahun", Toast.LENGTH_SHORT).show();
            }else if (S.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Isi Tanggal Periode", Toast.LENGTH_SHORT).show();
            }else if(TS.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Mohon Isi TSI", Toast.LENGTH_SHORT).show();
            }else{
                validasi();
                loading=ProgressDialog.show(this,null,"Harap Tunggu", true, false);
                saveData();
            }
        }catch (Exception ex){
            loading.dismiss();
            Toast.makeText(getApplicationContext(),"Isi Nilai PA Penumpang atau Pengemudi",Toast.LENGTH_SHORT).show();
        }



    }
    void validasi() {

//        CHECHBOX
        if (flood.isChecked()) {
            sFlood = 1;
        } else {
            sFlood = 0;
        }
        if (EQ.isChecked()) {
            sEQ = 1;
        } else {
            sEQ = 0;
        }
        if (SRCC.isChecked()) {
            sSRCC = 1;
        } else {
            sSRCC = 0;
        }
        if (TS.isChecked()) {
            sTS = 1;
        } else {
            sTS = 0;
        }
        if (tjh.isChecked()) {
            sTjh = 1;
        } else {
            sTjh = 0;
        }
        if (PADriver.isChecked()) {
            sPAD = 1;
        } else {
            sPAD = 0;
        }
        if (PAPassager.isChecked()) {
            sPAP = 1;
        } else {
            sPAP = 0;
        }
        if (tjh_passanger.isChecked()) {
            sTjhp = 1;
        } else {
            sTjhp = 0;
        }

//        SPINNER

        zzona =Zona.getSelectedItem().toString();
        if (zzona.equals("Zona I (Sumatera dan Kepulauan di sekitarnya)")) {
            sZona = 1;
        } else if (zzona.equals("Zona II (DKI Jakarta, Jawa Barat,dan Banten)")) {
            sZona = 2;
        } else {
            sZona = 3;
        }


//        RADIO GROUP
        int pilih = Usage.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(pilih);
        if (radioButton.getText() == "Pribadi") {
            RUsage = "Pribadi";
        } else if (radioButton.getText() == "Komersial") {
            RUsage = "Komersil";
        }
    }

    void tempData() {
        VehicleType.setPrompt("Jenis Kendaraan");
        PA_amount.setText("0");

        tjh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (tjh.isChecked()) {
                    tjh_amount.setEnabled(true);
                    tjh_passanger.setEnabled(true);
                    tjh_amount.setText("10000000");
                } else {
                    tjh_amount.setEnabled(false);
                    tjh_passanger.setEnabled(false);
                    tjh_amount.setText("0");
                }
            }
        });
        PADriver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (PADriver.isChecked()) {
                    PA_amount.setEnabled(true);
//                    PA_amount.setText("");
                }else if(PAPassager.isChecked()){
                    PA_amount.setEnabled(true);
                }else {
                    PA_amount.setEnabled(false);
                    PA_amount.setText("0");
                }
            }
        });
        PAPassager.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (PAPassager.isChecked()) {
                    PA_amount.setEnabled(true);
//                    PA_amount.setText("");
                }else if(PADriver.isChecked()){
                    PA_amount.setEnabled(true);
                }else {
                    PA_amount.setEnabled(false);
                    PA_amount.setText("0");
                }
            }
        });
//        PA_amount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PA_amount.setText("");
//            }
//        });

        SDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Astorsimulation.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                        Astorsimulation.this,
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

    private void saveData() {
        additionalModel additional = new additionalModel(
                sFlood,
                sEQ,
                sSRCC,
                sTS,
                Integer.parseInt((PA_amount).getText().toString().replaceAll(",","")),
                sPAD,
                sPAP,
                sTjh,
                Integer.parseInt((tjh_amount).getText().toString().replaceAll(",","")),
                sTjhp
        );

        kendaraanModel km = new kendaraanModel(
                VehicleType.getSelectedItem().toString(),
                ManufactureYear.getText().toString(),
                SDate.getText().toString(),
                EDate.getText().toString(),
                Coverages.getSelectedItem().toString().split(","),
                radioButton.getText().toString(),
                Integer.toString(sZona),
                Integer.parseInt((TSI).getText().toString().replaceAll(",", "")),
                true,
                10,
                additional
        );

        mApiService = UtilsApi.getAPIService2(); // meng-init yang ada di package apihelper

        Call<kendaraanModel> call = mApiService.postKendaraan(km);
        //calling API
        call.enqueue(new Callback<kendaraanModel>() {
            @Override
            public void onResponse(Call<kendaraanModel> call, Response<kendaraanModel> response) {
                try {
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat df = new DecimalFormat("Rp ###,###,###,###", symbols);
                    if (response != null) {
                        loading.dismiss();
                        respon = response.body();
                        res = response.body().getAlerts().getMessage();
                        int array = respon.getdResponse().getPremiDetail().toArray().length;

                        String premiDetail = response.body().getdResponse().getPremiDetail().toString();

                        DecimalFormat format = new DecimalFormat();
                        format.setDecimalSeparatorAlwaysShown(false);
                        double tpmin = respon.getdResponse().getTotalPremiMin();
                        double tpmax = respon.getdResponse().getTotalPremiMax();
                        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();

                        try {
                            JSONArray jsonArray = new JSONArray(premiDetail);
                            tr = "";
                            tr1 = "";
                            trA = "";
                            trB = "";

//                        PremiMinimal
                            for(int i=0; i<array ; i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                tr +=  "Tahun Ke    \n";
                                tr +=  "Coverage    \n";
                                tr +=  "Rate Bawah  \n";
                                tr +=  "Sum Insured \n";
                                tr +=  "Premi Bawah \n";
                                tr +=  "TJH3  \n";
                                tr +=  "TJH Penumpang  \n";
                                tr +=  "PA Driver \n";
                                tr +=  "PA Passanger \n";
                                tr +=  "Banjir Min \n";
                                tr +=  "TSI Min \n";
                                tr +=  "SRCC Min \n";
                                tr +=  "EQ Min \n";
                                tr +=  "Total Premi Bawah \n\n";

                                tr1 +=  "Tahun Ke    \n";
                                tr1 +=  "Coverage    \n";
                                tr1 +=  "Rate Atas  \n";
                                tr1 +=  "Sum Insured \n";
                                tr1 +=  "Premi Atas \n";
                                tr1 +=  "TJH3  \n";
                                tr1 +=  "TJH Penumpang  \n";
                                tr1 +=  "PA Driver \n";
                                tr1 +=  "PA Passanger \n";
                                tr1 +=  "Banjir Max \n";
                                tr1 +=  "TSI Max \n";
                                tr1 +=  "SRCC Max \n";
                                tr1 +=  "EQ Max \n";
                                tr1 +=  "Total Premi Atas \n\n";

                                trB += ": "+jsonObject.getInt("TahunKe") + "\n";
                                trB += ": "+jsonObject.getString("Coverage") + "\n";
                                trB += ": "+jsonObject.getString("Rate_Bawah")+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("SI"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("Premi_Bawah"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("PremiTJH3"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("PremiTJHPenumpang"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("PremiPADriver"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("PremiPAPassanger"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("PremiBanjirMin"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("PremiTSMin"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("PremiSRCCMin"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("PremiEQMin"))+ "\n";
                                trB += ": "+df.format(jsonObject.getInt("TotalPremiBawah"))+ "\n\n";

                                trA += ": "+jsonObject.getInt("TahunKe") + "\n";
                                trA += ": "+jsonObject.getString("Coverage") + "\n";
                                trA += ": "+jsonObject.getString("Rate_Atas")+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("SI"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("Premi_Atas"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("PremiTJH3"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("PremiTJHPenumpang"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("PremiPADriver"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("PremiPAPassanger"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("PremiBanjirMax"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("PremiTSMax"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("PremiSRCCMax"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("PremiEQMax"))+ "\n";
                                trA += ": "+df.format(jsonObject.getInt("TotalPremiAtas"))+ "\n\n";

//                            Toast.makeText(getApplicationContext(),"Jumlah Tahun " + array, Toast.LENGTH_SHORT).show();
                            }
//                        lemparB.append(trB);
                            lemparA.setText(trA);
                            lemparB.setText(trB);
                            lemparMin.setText(tr);
                            lemparMax.setText(tr1);
                            lemparA.append("\n");


                            final String tahun = ManufactureYear.getText().toString();
//                        final int hargaKendaraan = Integer.parseInt(TSI.getText().toString());
                            String harken = TSI.getText().toString().replaceAll(",", "");
                            final int hargaKendaraan = Integer.parseInt(harken);


                            lemparA.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    lemparA.setText("");
                                }
                            }, 2000);

                            final int itpmin = (int) tpmin;
                            final int itpmax = (int) tpmax;
//                        Pindah Intent
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(Astorsimulation.this,ResultAstor.class);
                                    i.putExtra("tra", lemparA.getText().toString());
                                    i.putExtra("trb", lemparB.getText().toString());
                                    i.putExtra("tr", lemparMin.getText().toString());
                                    i.putExtra("tr1", lemparMax.getText().toString());
                                    i.putExtra("hk",hargaKendaraan);
                                    i.putExtra("tahun",tahun);
                                    i.putExtra("zona",zzona);
                                    i.putExtra("tpmin",itpmin);
                                    i.putExtra("tpmax",itpmax);

                                    loading.dismiss();
                                    startActivity(i);
                                }
                            }, 500);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }else {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<kendaraanModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
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
                if (TSI.getText().hashCode() == s.hashCode()){
                    TSI.removeTextChangedListener(this);
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
                        TSI.setText(formattedString);
                        TSI.setSelection(TSI.getText().length());
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }

                    TSI.addTextChangedListener(this);
                }else if(tjh_amount.getText().hashCode() == s.hashCode()){
                    tjh_amount.removeTextChangedListener(this);
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
                        tjh_amount.setText(formattedString);
                        tjh_amount.setSelection(tjh_amount.getText().length());
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }

                    tjh_amount.addTextChangedListener(this);
                }else if (PA_amount.getText().hashCode() == s.hashCode()){
                    PA_amount.removeTextChangedListener(this);

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
                        PA_amount.setText(formattedString);
                        PA_amount.setSelection(PA_amount.getText().length());
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }
                    PA_amount.addTextChangedListener(this);
                }


            }
        };
    }
}
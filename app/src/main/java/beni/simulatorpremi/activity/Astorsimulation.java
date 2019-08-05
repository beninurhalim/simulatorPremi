package beni.simulatorpremi.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import beni.simulatorpremi.model.additionalModel;
import beni.simulatorpremi.model.kendaraanModel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;

import beni.simulatorpremi.R;
import beni.simulatorpremi.util.SharedPrefManager;
import beni.simulatorpremi.util.api.BaseApiService;
import beni.simulatorpremi.util.api.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Astorsimulation extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    int sFlood, sEQ, sSRCC, sTS, sZona, sTjh, nilaitjh;
    String RUsage, res;
    kendaraanModel respon;
    Spinner VehicleType;
    TextInputEditText ManufactureYear;
    EditText SDate;
    EditText EDate;
    DatePickerDialog.OnDateSetListener mDateSetListeners;
    DatePickerDialog.OnDateSetListener mDateSetListenerf;
    Spinner Zona;
    TextInputEditText TSI;
    Spinner Coverages;
    CheckBox tjh, flood, tjh_passanger, EQ, SRCC, TS;
    SeekBar seekBar1;
    TextView tjh_amount, lempar;
    Button lanjut;
    Button sbutton;
    RadioGroup Usage;
    RadioButton rbPribadi, rbKomersil;
    RadioButton radioButton;
    BaseApiService mApiService;
    String tr="";
    String r="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astorsimulation);



        ManufactureYear = (TextInputEditText) findViewById(R.id.ManufactureYear);
        VehicleType = (Spinner) findViewById(R.id.VehicleType);
        SDate = (EditText) findViewById(R.id.SDate);
        EDate = (EditText) findViewById(R.id.EDate);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        tjh = (CheckBox) findViewById(R.id.tjh);
        tjh_amount = (TextView) findViewById(R.id.tjh_amount);
        tjh_passanger = (CheckBox) findViewById(R.id.tjh_passanger);
        sbutton = (Button) findViewById(R.id.sbutton);
        lanjut = (Button) findViewById(R.id.lanjut);
        Usage = (RadioGroup) findViewById(R.id.Usage);
        rbPribadi = (RadioButton) findViewById(R.id.rbPribadi);
        rbKomersil = (RadioButton) findViewById(R.id.rbKomersial);
        Coverages = (Spinner) findViewById(R.id.Coverages);
        Zona = (Spinner) findViewById(R.id.Zona);
        TSI = (TextInputEditText) findViewById(R.id.TSI);
        flood = (CheckBox) findViewById(R.id.flood);
        EQ = (CheckBox) findViewById(R.id.EQ);
        SRCC = (CheckBox) findViewById(R.id.SRCC);
        TS = (CheckBox) findViewById(R.id.TS);
        lempar = (TextView) findViewById(R.id.lempar);

        seekBar1.setEnabled(false);
//        seekBar2.setEnabled(false);
        tempData();
        Klik();
    }

    void Klik() {
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();
                saveData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(Astorsimulation.this,ResultAstor.class);

                        i.putExtra("tr", lempar.getText().toString());
//                        i.putExtra("tr", tr);
                        startActivity(i);
                    }
                }, 1000);

            }
        });
    }

    void validasi() {

//        CHECHBOX
        if (flood.isChecked()) {
            sFlood = 1;
        } else {
            sFlood = 0;
        }
        if (EQ.isChecked()) {
//            EQ.setText("1");
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

//        SPINNER
        String zzona = Zona.getSelectedItem().toString();

        if (zzona == "Zona I (Sumatera dan Kepulauan di sekitarnya)") {
            sZona = 1;
            Toast.makeText(getApplicationContext(),zzona,Toast.LENGTH_SHORT).show();
        } else if (zzona == "Zona II (DKI Jakarta, Jawa Barat,dan Banten)") {
            sZona = 2;
            Toast.makeText(getApplicationContext(),zzona,Toast.LENGTH_SHORT).show();
        } else {
            sZona = 3;
            Toast.makeText(getApplicationContext(),zzona,Toast.LENGTH_SHORT).show();
        }


//        RADIO GROUP
        int pilih = Usage.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(pilih);
        if (radioButton.getText() == "Pribadi") {
            RUsage = "Pribadi";
        } else if (radioButton.getText() == "Komersial") {
            RUsage = "Komersial";
        }
    }

    void tempData() {
        VehicleType.setPrompt("Jenis Kendaraan");

        tjh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (tjh.isChecked()) {
                    seekBar1.setEnabled(true);
                } else {
                    seekBar1.setEnabled(false);
                }
            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                DecimalFormat decimalFormat = new DecimalFormat("Rp ###,###,###,###", symbols);
                String prezzo = decimalFormat.format(Integer.parseInt(String.valueOf(progress * 100000)));
                nilaitjh = Integer.parseInt(String.valueOf(progress * 100000));
                tjh_amount.setText(prezzo);
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sb) {

            }
        });


        SDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Astorsimulation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListeners,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListeners = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                int year2 = year + 1;
                String date2 = month + "/" + day + "/" + year2;
                SDate.setText(date);
                EDate.setText(date2);
            }
        };

        EDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Astorsimulation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerf,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
                10000000,
                1,
                1,
                nilaitjh,
                sTjh,
                1
        );

        kendaraanModel km = new kendaraanModel(
                VehicleType.getSelectedItem().toString(),
                ManufactureYear.getText().toString(),
                SDate.getText().toString(),
                EDate.getText().toString(),
                Coverages.getSelectedItem().toString().split(","),
                radioButton.getText().toString(),
                Integer.toString(sZona),
                Integer.parseInt((TSI).getText().toString()),
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

                if (response != null) {
                    respon = response.body();
                    res = response.body().getAlerts().getMessage();
                    int array = respon.getdResponse().getPremiDetail().toArray().length;

                    String premiDetail = response.body().getdResponse().getPremiDetail().toString();

                    String tpmin = respon.getdResponse().getTotalPremiMin();
                    String tpmax = respon.getdResponse().getTotalPremiMax();
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();


                    try {
                        JSONArray jsonArray = new JSONArray(premiDetail);
                        tr = "Total Premi Min : " + tpmin + "\n";
                        tr += "Total Premi Max : " + tpmax + "\n\n";
                        System.out.println(tr);
                        for(int i=0; i<array ; i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            tr += "Tahun Ke    : "+jsonObject.getInt("TahunKe") + "\n";
                            tr += "Coverage    : "+jsonObject.getString("Coverage") + "\n";
                            tr += "Premi Atas  : "+ jsonObject.getString("Premi_Atas")+ "\n";
                            tr += "Premi Bawah      : "+ jsonObject.getString("Premi_Bawah")+ "\n";
                            tr += "Premi Banjir Min : "+ jsonObject.getString("PremiBanjirMin")+ "\n";
                            tr += "Premi Banjir Max : "+ jsonObject.getString("PremiBanjirMax")+ "\n";
                            tr += "Premi TSI Min : "+ jsonObject.getString("PremiTSMin")+ "\n";
                            tr += "Premi TSI Max : "+ jsonObject.getString("PremiTSMax")+ "\n";
                            tr += "Premi SRCC Min : "+ jsonObject.getString("PremiSRCCMin")+ "\n";
                            tr += "Premi SRCC Max : "+ jsonObject.getString("PremiSRCCMax")+ "\n";
                            tr += "Premi EQ Min : "+ jsonObject.getString("PremiEQMin")+ "\n";
                            tr += "Premi EQ Max : "+ jsonObject.getString("PremiEQMax")+ "\n";
                            tr += "Sum Insured : "+ jsonObject.getString("SI")+ "\n";
                            tr += "Rate Atas : "+ jsonObject.getString("Rate_Atas")+ "\n";
                            tr += "Rate Bawah : "+ jsonObject.getString("Rate_Bawah")+ "\n";
                            tr += "Total Premi Bawah : "+ jsonObject.getString("TotalPremiBawah")+ "\n";
                            tr += "Total Premi Atas : "+ jsonObject.getString("TotalPremiAtas")+ "\n\n";

                            System.out.println(tr);
//                            Toast.makeText(getApplicationContext(),"Jumlah Tahun " + array, Toast.LENGTH_SHORT).show();
                        }
                        lempar.append(tr);
                        lempar.append("\n");
                        lempar.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                lempar.setText("");
                            }
                        }, 2000);

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
                        e.printStackTrace();

                    }
                }else {
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<kendaraanModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "EROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
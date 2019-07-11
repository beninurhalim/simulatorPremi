package beni.simulatorpremi.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;

import beni.simulatorpremi.R;
import beni.simulatorpremi.util.api.BaseApiService;
import beni.simulatorpremi.util.api.RetrofitClient;
import beni.simulatorpremi.util.api.UtilsApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Astorsimulation extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Spinner VehicleType;
    TextInputEditText ManufactureYear;
    @BindView(R.id.SDate)
    EditText SDate;
    @BindView(R.id.EDate)
    EditText EDate;
    DatePickerDialog.OnDateSetListener mDateSetListeners;
    DatePickerDialog.OnDateSetListener mDateSetListenerf;
    @BindView(R.id.Zona)
    Spinner Zona;
    @BindView(R.id.tlo)
    Spinner tlo;
    @BindView(R.id.TSI)
    TextInputEditText TSI;
    @BindView(R.id.Coverages)
    RadioGroup Coverages;
    CheckBox tjh;
    SeekBar seekBar1;
    SeekBar seekBar2;
    CheckBox tjh_passanger;
    TextView tjh_amount;
    TextView tjhp_amount;
    Button lanjut;
    @BindView(R.id.sbutton)
    Button sbutton;

    BaseApiService mApiService2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astorsimulation);

        ManufactureYear = (TextInputEditText) findViewById(R.id.ManufactureYear);
        VehicleType =(Spinner) findViewById(R.id.VehicleType);
        SDate = (EditText) findViewById(R.id.SDate);
        EDate = (EditText) findViewById(R.id.EDate);
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar1.setEnabled(false);
        seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        seekBar2.setEnabled(false);
        tjh_amount = (TextView) findViewById(R.id.tjh_amount);
        tjhp_amount = (TextView) findViewById(R.id.tjhp_amount);
        tjh = (CheckBox) findViewById(R.id.tjh);
        tjh_passanger = (CheckBox) findViewById(R.id.tjh_passanger);
        sbutton = (Button) findViewById(R.id.sbutton);
        lanjut = (Button) findViewById(R.id.lanjut);

        tempData();
        saveData();
        Klik();
    }



    void Klik(){
        sbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveData();
//                Toast.makeText(getApplicationContext(), VehicleType.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(Astorsimulation.this,ResultAstor.class);
                startActivity(i);
            }
        });
    }

    void tempData(){
        VehicleType.setPrompt("Jenis Kendaraan");

        tjh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    seekBar1.setEnabled(true);
                }
                else
                {
                    seekBar1.setEnabled(false);
                }
            }
        });

        tjh_passanger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    seekBar2.setEnabled(true);
                }
                else
                {
                    seekBar2.setEnabled(false);
                }
            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                DecimalFormat decimalFormat = new DecimalFormat("Rp ###,###,###,###", symbols);
                String prezzo = decimalFormat.format(Integer.parseInt(String.valueOf(progress*100000)));
                tjh_amount.setText(prezzo);
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sb) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb2, int progress, boolean fromUser) {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                DecimalFormat decimalFormat = new DecimalFormat("Rp ###,###,###,###", symbols);
                String prezzo = decimalFormat.format(Integer.parseInt(String.valueOf(progress*100000)));
                tjhp_amount.setText(prezzo);
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb2) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sb2) {

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
                        year,month,day);
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
                        year,month,day);
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

    private void saveData(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("VehicleType", VehicleType.getSelectedItem().toString());
        jsonObject.addProperty("ManufactureYear", ManufactureYear.getText().toString());
        jsonObject.addProperty("SDate", SDate.getText().toString());
        jsonObject.addProperty("EDate", EDate.getText().toString());

//        ButterKnife.bind(this);
//        mContext = this;
        mApiService2 = UtilsApi.getAPIService2(); // meng-init yang ada di package apihelper

//        RetrofitClient service = getc.create(ApiService.class);
//        Call<PostResponse> call = service.postData(jsonObject);
        Call<BaseApiService.PostResponse> call = mApiService2.postData(jsonObject);
        //calling the apiVehicleType
        call.enqueue(new Callback<BaseApiService.PostResponse>() {
            @Override
            public void onResponse(Call<BaseApiService.PostResponse> call, Response<BaseApiService.PostResponse> response) {
                //hiding progress dialog
//                loading.dismiss();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Post submitted Title: "+response.body().getVehicleType()+" Body: "+response.body().getManufactureYear()+" EndData: "+response.body().getSDate()+response.body().getEDate(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseApiService.PostResponse> call, Throwable t) {
//                loading.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beni.simulatorpremi.model.additionalModel;
import beni.simulatorpremi.model.kendaraanModel;
import beni.simulatorpremi.model.responeJson;
import beni.simulatorpremi.model.dataResponse;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import beni.simulatorpremi.R;
import beni.simulatorpremi.util.SharedPrefManager;
import beni.simulatorpremi.util.api.BaseApiService;
import beni.simulatorpremi.util.api.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Astorsimulation extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    int sFlood,sEQ,sSRCC,sTS,sZona,sTjh;
    SharedPrefManager sharedPrefManager;
    String RUsage;
    ProgressDialog loading;
    Spinner VehicleType;
    TextInputEditText ManufactureYear;
    EditText SDate;
    EditText EDate;
    DatePickerDialog.OnDateSetListener mDateSetListeners;
    DatePickerDialog.OnDateSetListener mDateSetListenerf;
    Spinner Zona;
    TextInputEditText TSI;


    Spinner Coverages;
    CheckBox tjh,flood,tjh_passanger,EQ,SRCC,TS;
    SeekBar seekBar1;
    TextView tjh_amount;
    Button lanjut;
    Button sbutton;
    RadioGroup Usage;
    RadioButton rbPribadi,rbKomersil;
    RadioButton radioButton;

    BaseApiService mApiService;
    private int nilaitjh;


    TextView results;
    String data = "";
    // URL of object to be parsed
    String JsonURL = "http://services.jp.co.id/api/rate/astor";
//    String JsonURL = "https://api.myjson.com/bins/1anjap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astorsimulation);

        ManufactureYear = (TextInputEditText) findViewById(R.id.ManufactureYear);
        VehicleType     =(Spinner) findViewById(R.id.VehicleType);
        SDate           = (EditText) findViewById(R.id.SDate);
        EDate           = (EditText) findViewById(R.id.EDate);
        seekBar1        = (SeekBar)findViewById(R.id.seekBar1);
        tjh             = (CheckBox) findViewById(R.id.tjh);
        tjh_amount      = (TextView) findViewById(R.id.tjh_amount);
        tjh_passanger   = (CheckBox) findViewById(R.id.tjh_passanger);
        sbutton         = (Button) findViewById(R.id.sbutton);
        lanjut          = (Button) findViewById(R.id.lanjut);
        Usage           = (RadioGroup) findViewById(R.id.Usage);
        rbPribadi       = (RadioButton) findViewById(R.id.rbPribadi);
        rbKomersil      = (RadioButton) findViewById(R.id.rbKomersial);
        Coverages       = (Spinner) findViewById(R.id.Coverages);
        Zona            = (Spinner) findViewById(R.id.Zona);
        TSI             = (TextInputEditText) findViewById(R.id.TSI);
        flood           = (CheckBox) findViewById(R.id.flood);
        EQ              = (CheckBox) findViewById(R.id.EQ);
        SRCC            = (CheckBox) findViewById(R.id.SRCC);
        TS              = (CheckBox) findViewById(R.id.TS);


        seekBar1.setEnabled(false);
//        seekBar2.setEnabled(false);
        tempData();
        Klik();
    }

    void tes(){
        // This string will hold the results

        // Defining the Volley request queue that handles the URL request concurrently
        RequestQueue requestQueue;

        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        // Creating the JsonObjectRequest class called obreq, passing required parameters:
        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new com.android.volley.Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int i = 1;

                            if (i < 2) {
                                JSONObject obj = response.getJSONObject("data");
//
                                // Retrieves the string labeled "colorName" and "description" from
                                //the response JSON Object
                                //and converts them into javascript objects
                                String premiMin = obj.getString("TotalPremiMin");
                                String coverage = obj.getString("TotalPremiMax");

                                // Adds strings from object to the "data" string
                                data += "Premi Minimum: " + premiMin +
                                        "nDescription : " + coverage;

                                // Adds the data string to the TextView "results"
                                tjh_amount.setText(data);
                            }
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);
    }

    //OBJECT TO ARRAY



    void Klik(){
        sbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tempData();
                validasi();
                saveData();

//                tes();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Intent i = new Intent(Astorsimulation.this,ResultAstor.class);
//                        startActivity(i);
//                    }
//                }, 2000);
//                Toast.makeText(getApplicationContext(), VehicleType.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    void validasi(){

//        CHECHBOX
        if (flood.isChecked()) {
            sFlood = 1;
        }else{
            sFlood = 0;
        }
        if (EQ.isChecked()) {
//            EQ.setText("1");
            sEQ = 1;
        }else{
            sEQ = 0;
        }
        if (SRCC.isChecked()) {
            sSRCC = 1;
        }else{
            sSRCC = 0;
        }
        if (TS.isChecked()) {
            sTS = 1;
        }else{
            sTS = 0;
        }if (tjh.isChecked()) {
            sTjh = 1;
        }else{
            sTjh = 0;
        }

//        SPINNER
        if (Zona.getSelectedItem() == "Zona I (Sumatera dan Kepulauan di sekitarnya)"){
            sZona = 1;
        }else if (Zona.getSelectedItem() == "Zona II (DKI Jakarta, Jawa Barat,dan Banten)"){
            sZona = 2;
        }else{
            sZona = 3;
        }



//        RADIO GROUP
        int pilih = Usage.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(pilih);
        if (radioButton.getText()=="Pribadi"){
                RUsage = "Pribadi";
        }else if (radioButton.getText()=="Komersial"){
                RUsage = "Komersial";
        }
    }

    void tempData(){
        VehicleType.setPrompt("Jenis Kendaraan");

        tjh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(tjh.isChecked()) {
                    seekBar1.setEnabled(true);
                }
                else
                {
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
                String prezzo = decimalFormat.format(Integer.parseInt(String.valueOf(progress*100000)));
                nilaitjh = Integer.parseInt(String.valueOf(progress*100000));
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

                if(response.isSuccessful()){

                    kendaraanModel dr = response.body();
//                    List<premiDetail> resultList = response.getResult();

                    Toast.makeText(Astorsimulation.this, "Status "+dr.getAlerts().getMessage(),Toast.LENGTH_SHORT).show();
//                      Toast.makeText(Astorsimulation.this, "Status "+response.body().getdResponse().getPremiDetail(),Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "POST: "+response.body().getV ehicleType()+" Body: "+response.body().getManufactureYear()+" EndData: "+response.body().getSDate()+response.body().getEDate(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<kendaraanModel> call, Throwable t) {
//                loading.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}


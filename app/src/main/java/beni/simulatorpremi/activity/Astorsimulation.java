package beni.simulatorpremi.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;

//import androidx.appcompat.app.AppCompatActivity;
import beni.simulatorpremi.R;

public class Astorsimulation extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText mDisplayDates;
    private EditText mDisplayDatef;
    private TextView valuetxt;
    private TextView valuetxt2;
    private DatePickerDialog.OnDateSetListener mDateSetListeners;
    private DatePickerDialog.OnDateSetListener mDateSetListenerf;
    private   SeekBar sb;
    private   SeekBar sb2;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private Spinner typevehicle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astorsimulation);

        mDisplayDates = (EditText) findViewById(R.id.dates);
        mDisplayDatef = (EditText) findViewById(R.id.datef);
        sb = (SeekBar)findViewById(R.id.seekBar1);
        sb.setEnabled(false);
        sb2 = (SeekBar)findViewById(R.id.seekBar2);
        sb2.setEnabled(false);
        valuetxt2 = (TextView) findViewById(R.id.valuetxt2);
        valuetxt = (TextView) findViewById(R.id.valuetxt);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        typevehicle =(Spinner) findViewById(R.id.typevehicle);


        typevehicle.setPrompt("Jenis Kendaraan");

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               if(isChecked) {
                   sb.setEnabled(true);
               }
               else
               {
                   sb.setEnabled(false);
               }

            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    sb2.setEnabled(true);
                }
                else
                {
                    sb2.setEnabled(false);
                }

            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                DecimalFormat decimalFormat = new DecimalFormat("Rp ###,###,###,###", symbols);
                String prezzo = decimalFormat.format(Integer.parseInt(String.valueOf(progress*100000)));
                 valuetxt.setText(prezzo);
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sb) {

            }
        });

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb2, int progress, boolean fromUser) {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                DecimalFormat decimalFormat = new DecimalFormat("Rp ###,###,###,###", symbols);
                String prezzo = decimalFormat.format(Integer.parseInt(String.valueOf(progress*100000)));
                valuetxt2.setText(prezzo);

            }

            @Override
            public void onStartTrackingTouch(SeekBar sb2) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sb2) {

            }
        });

        mDisplayDates.setOnClickListener(new View.OnClickListener() {
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
                mDisplayDates.setText(date);
                mDisplayDatef.setText(date2);
            }
        };

        mDisplayDatef.setOnClickListener(new View.OnClickListener() {
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
                mDisplayDatef.setText(date);
            }
        };


    }

}

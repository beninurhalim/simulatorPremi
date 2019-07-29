package beni.simulatorpremi.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import beni.simulatorpremi.R;
import beni.simulatorpremi.util.api.BaseApiService;
import beni.simulatorpremi.util.api.UtilsApi;

public class FragmentMaxPremi extends Fragment {
    BaseApiService mApiService2;
    View view;
    ProgressDialog loading;
    TextView txtDKendaraan;
    Context mContext;
    Astorsimulation astorsimulation;
    String NEW_LINE = "\n";

    // Will show the string "data" that holds the results
    TextView results;
    String result;
    String data = "";
    // URL of object to be parsed
//    String JsonURL = "http://services.jp.co.id/api/rate/astor";
    String JsonURL = "https://api.myjson.com/bins/1brf95";

    private static final String KEY_EMPLOYEE_ID = "employee_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DOB = "dob";
    private static final String KEY_DESIGNATION = "designation";
    private static final String KEY_CONTACT_NUMBER = "contact_number";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SALARY = "salary";
    private static final String KEY_PREMI_ATAS = "Premi_Atas";
    private static final String KEY_PREMI_BAWAH = "Premi_Bawah";


    public FragmentMaxPremi(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        view =inflater.inflate(R.layout.maxpremi_fragment,container,false);
        mApiService2 = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        // Casts results into the TextView found within the main layout XML with id jsonData
        results = (TextView) view.findViewById(R.id.jsonData);

//        getData();
        loadJsonArray();
//      test();
        return view;
    }

    void getData(){
        RequestQueue requestQueue;

            requestQueue = Volley.newRequestQueue(this.getContext());

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,null,

                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int i = 1;

                            if (i < 2) {
                                JSONObject obj1 = response.getJSONObject("alerts");
                                JSONObject obj2 = response.getJSONObject("data");

                                String code = obj1.getString("code");
                                String message = obj1.getString("message");

                                String premiMin = obj2.getString("TotalPremiMin");
                                String premiMax = obj2.getString("TotalPremiMax");


                                data += "Code: " + code +
                                        "\nMessage : " + message+
                                        "\nTotal Premi Min : " + premiMin+
                                        "\nTotal Premi max : " + premiMax;

//                                results.setText(data);
                            }
                        }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        // Handles errors that occur due to Volley
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Volley", "Error");
                        }
                    }
            );
            requestQueue.add(obreq);

    }



    void loadJsonArray(){
        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(this.getContext());
            JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                    (Request.Method.GET, JsonURL, null, new Response.Listener<JSONArray>() {


                        @Override
                        public void onResponse(JSONArray responseArray) {
//                            loading.dismiss();
                            try {
                                StringBuilder textViewData = new StringBuilder();


//                                JSONObject obj2 = responseArray.getJSONObject("0");
                                JSONObject object = new JSONObject(result);
                                JSONArray jarr = object.getJSONArray("PremiDetail");
                                for (int z=0 ; z < jarr.length() ; z++){
                                    JSONObject Jasonobject = jarr.getJSONObject(z);
                                    Integer employeeId = Jasonobject.getInt(KEY_EMPLOYEE_ID);
                                    String name = Jasonobject.getString(KEY_NAME);
                                    String dob = Jasonobject.getString(KEY_DOB);
                                    String designation = Jasonobject.getString(KEY_DESIGNATION);
                                    String contactNumber = Jasonobject.getString(KEY_CONTACT_NUMBER);
                                    String email = Jasonobject.getString(KEY_EMAIL);
                                    String salary = Jasonobject.getString(KEY_SALARY);

                                    //Create String out of the Parsed JSON

                                    textViewData.append("Employee Id: ")
                                            .append(employeeId.toString()).append(NEW_LINE);
                                    textViewData.append("Name: ").append(name).append(NEW_LINE);
                                    textViewData.append("Date of Birth: ").append(dob).append(NEW_LINE);
                                    textViewData.append("Designation: ").append(designation).append(NEW_LINE);
                                    textViewData.append("Contact Number: ").append(contactNumber).append(NEW_LINE);
                                    textViewData.append("Email: ").append(email).append(NEW_LINE);
                                    textViewData.append("Salary: ").append(salary).append(NEW_LINE);
                                    textViewData.append(NEW_LINE);
                                }

                                //Populate textView with the response
                                results.setText(textViewData.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            loading.dismiss();

                            //Display error message whenever an error occurs
                            Toast.makeText(getContext(),
                                    error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

        requestQueue.add(jsArrayRequest);
            // Access the RequestQueue through your singleton class.
//            MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);

    }
    void test() {

        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(this.getContext());

        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (Request.Method.GET, JsonURL, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject response2 = response.getJSONObject(i);
                                String Premi_Atas = response2.getString(KEY_PREMI_ATAS);
                                String Premi_Bawah = response2.getString(KEY_PREMI_BAWAH);
//                                String dob = response.getString(KEY_DOB);
//                                String designation = response.getString(KEY_DESIGNATION);
//                                String contactNumber = response.getString(KEY_CONTACT_NUMBER);
//                                String email = response.getString(KEY_EMAIL);
//                                String salary = response.getString(KEY_SALARY);

                                //Create String out of the Parsed JSON

                                textViewData.append("Name: ").append(Premi_Atas).append(NEW_LINE);
                                textViewData.append("Date of Birth: ").append(Premi_Bawah).append(NEW_LINE);
                                textViewData.append(NEW_LINE);
                            }

                            //Populate textView with the response
                            results.setText(textViewData.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        // Access the RequestQueue through your singleton class.
        requestQueue.add(jsArrayRequest);

                    }


}

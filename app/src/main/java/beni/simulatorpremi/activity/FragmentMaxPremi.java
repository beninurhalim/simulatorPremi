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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


import beni.simulatorpremi.R;
import beni.simulatorpremi.util.SharedPrefManager;
import beni.simulatorpremi.util.api.BaseApiService;
import beni.simulatorpremi.util.api.UtilsApi;

public class FragmentMaxPremi extends Fragment {
    BaseApiService mApiService;
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
    String JsonURL = "http://services.jp.co.id/api/rate/astor";
//    String JsonURL = "https://api.myjson.com/bins/1brf95";


    SharedPrefManager sharedPrefManager;


    public FragmentMaxPremi(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
//        String tes = getArguments().getString("et");

            view =inflater.inflate(R.layout.maxpremi_fragment,container,false);
            mApiService = UtilsApi.getAPIService2(); // meng-init yang ada di package apihelper
            // Casts results into the TextView found within the main layout XML with id jsonData
            results = (TextView) view.findViewById(R.id.jsonData);

//        results.setText(tes);
            test();
        return view;
 }


//    void getData(){
//        RequestQueue requestQueue;
//
//            requestQueue = Volley.newRequestQueue(this.getContext());
//
//        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,null,
//
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            int i = 1;
//
//                            if (i < 2) {
//                                JSONObject obj1 = response.getJSONObject("alerts");
//                                JSONObject obj2 = response.getJSONObject("data");
//
//                                String code = obj1.getString("code");
//                                String message = obj1.getString("message");
//
//                                String premiMin = obj2.getString("TotalPremiMin");
//                                String premiMax = obj2.getString("TotalPremiMax");
//
//
//                                data += "Code: " + code +
//                                        "\nMessage : " + message+
//                                        "\nTotal Premi Min : " + premiMin+
//                                        "\nTotal Premi max : " + premiMax;
//
//                                results.setText(data);
//                            }
//                        }
//                            catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        // Handles errors that occur due to Volley
//                        public void onErrorResponse(VolleyError error) {
//                            Log.e("Volley", "Error");
//                        }
//                    }
//            );
//            requestQueue.add(obreq);
//
//    }


    void test() {
//        sharedPrefManager = new SharedPrefManager(getContext());
//        results.setText(sharedPrefManager.getSPNama());


    }


}

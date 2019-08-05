package beni.simulatorpremi.activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import beni.simulatorpremi.R;
import beni.simulatorpremi.util.api.BaseApiService;
import beni.simulatorpremi.util.api.UtilsApi;

public class FragmentMaxPremi extends Fragment {
    BaseApiService mApiService;
    View view;
    TextView results;

    Astorsimulation as;
    public FragmentMaxPremi(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
//        String tes = getArguments().getString("et");

        view = inflater.inflate(R.layout.maxpremi_fragment, container, false);
        mApiService = UtilsApi.getAPIService2(); // meng-init yang ada di package apihelper
        // Casts results into the TextView found within the main layout XML with id jsonData
        results = (TextView) view.findViewById(R.id.jsonData);
        return view;
    }

}

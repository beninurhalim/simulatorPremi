package beni.simulatorpremi.fragment.Astor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import beni.simulatorpremi.R;

public class FragMinAstor extends Fragment {
    public FragMinAstor() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlayout, container, false);//Inflate Layout
        TextView text = (TextView) view.findViewById(R.id.fragmentText);//Find textview Id
        TextView text2 = (TextView) view.findViewById(R.id.fragmentText2);//Find textview Id

        //Get Argument that passed from activity in "data" key value
        String getArgument = getArguments().getString("trb");
        String ga = getArguments().getString("tr");
        text.setText(ga);//set string over textview
        text2.setText(getArgument);//set string over textview
        return view;//return view
    }
}

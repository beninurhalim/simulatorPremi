package beni.simulatorpremi.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import beni.simulatorpremi.R;

public class FragmentMinPremi extends Fragment {
    View view;
    public FragmentMinPremi() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.minpremi_fragment,container,false);
        return view;
    }
}

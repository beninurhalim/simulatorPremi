package beni.simulatorpremi.activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import beni.simulatorpremi.R;

public class FragmentMaxPremi extends Fragment {
    View view;

    public FragmentMaxPremi(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        view = inflater.inflate(R.layout.maxpremi_fragment, container, false);

        return view;
    }

}

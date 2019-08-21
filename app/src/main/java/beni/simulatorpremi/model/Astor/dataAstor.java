package beni.simulatorpremi.model.Astor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class dataAstor {
    @SerializedName("TotalPremiMin")
    double TotalPremiMin;
    @SerializedName("TotalPremiMax")
    double TotalPremiMax;

    @SerializedName("PremiDetail")
    @Expose
    private ArrayList<Object> PremiDetail = new ArrayList<Object>();

    public double getTotalPremiMin() {
        return TotalPremiMin;
    }

    public void setTotalPremiMin(double totalPremiMin) {
        TotalPremiMin = totalPremiMin;
    }

    public double getTotalPremiMax() {
        return TotalPremiMax;
    }

    public void setTotalPremiMax(double totalPremiMax) {
        TotalPremiMax = totalPremiMax;
    }


    public ArrayList<Object> getPremiDetail() {
        return PremiDetail;
    }

    public void setPremiDetail(ArrayList<Object> premiDetail) {
        PremiDetail = premiDetail;
    }
}



package beni.simulatorpremi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class dataResponse {
    @SerializedName("TotalPremiMin")
    String TotalPremiMin;
    @SerializedName("TotalPremiMax")
    String TotalPremiMax;

    @SerializedName("PremiDetail")
    @Expose
    private ArrayList<Object> PremiDetail = new ArrayList<Object>();

    public String getTotalPremiMin() {
        return TotalPremiMin;
    }

    public void setTotalPremiMin(String totalPremiMin) {
        TotalPremiMin = totalPremiMin;
    }

    public String getTotalPremiMax() {
        return TotalPremiMax;
    }

    public void setTotalPremiMax(String totalPremiMax) {
        TotalPremiMax = totalPremiMax;
    }


    public ArrayList<Object> getPremiDetail() {
        return PremiDetail;
    }

    public void setPremiDetail(ArrayList<Object> premiDetail) {
        PremiDetail = premiDetail;
    }
}



package beni.simulatorpremi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class dataResponse {
    @SerializedName("TotalPremiMin")
    String TotalPremiMin;
    @SerializedName("TotalPremiMax")
    String TotalPremiMax;
//    @SerializedName("PremiDetail")
    premiDetail[] PremiDetail;

    private ArrayList<premiDetail> premiList = new ArrayList<>();
    //getter and setters

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

    public premiDetail[] getPremiDetail() {
        return PremiDetail;
    }
}

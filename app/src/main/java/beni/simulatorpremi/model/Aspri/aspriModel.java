package beni.simulatorpremi.model.Aspri;

import com.google.gson.annotations.SerializedName;

import beni.simulatorpremi.model.Astor.Alerts;
import beni.simulatorpremi.model.Astor.dataAstor;

public class aspriModel {
    private String SDate;
    private String EDate;
    private int NilaiSantunanMax;
    private String ProductID;
    private String Kelas;
    private int EvakuasiF;
    private int CacatTetapF;
    private int PerawatanMedisF;

    public aspriModel(String SDate, String EDate, int nilaiSantunanMax, String productID, String kelas, int evakuasiF, int cacatTetapF, int perawatanMedisF) {
        this.SDate = SDate;
        this.EDate = EDate;
        this.NilaiSantunanMax = nilaiSantunanMax;
        this.ProductID = productID;
        this.Kelas = kelas;
        this.EvakuasiF = evakuasiF;
        this.CacatTetapF = cacatTetapF;
        this.PerawatanMedisF = perawatanMedisF;
    }

    String error;
    @SerializedName("alerts")
    Alerts alerts;

    String total_data;
    dataAspri data;

    public String getError() {
        return error;
    }

    public Alerts getAlerts() {
        return alerts;
    }

    public String getTotal_data() {
        return total_data;
    }

    public dataAspri getData() {
        return data;
    }
}

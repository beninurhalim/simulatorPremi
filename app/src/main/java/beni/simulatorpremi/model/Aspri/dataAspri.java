package beni.simulatorpremi.model.Aspri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class dataAspri {
    double TotalPremi;
    double Premi_Meninggal;
    double Premi_Cacat;
    double Premi_BiayaMedis;
    double Premi_Evakuasi;
    double Premi_RawatInap;
    double SI_Meninggal;
    double SI_Cacat;
    double SI_PerawatanMedis;
    double SI_Evakuasi;
    double SI_RawatInap;

    public double getSi_meninggal() {
        return SI_Meninggal;
    }

    public double getSi_cacat() {
        return SI_Cacat;
    }

    public double getSi_medis() {
        return SI_PerawatanMedis;
    }

    public double getSi_evakuasi() {
        return SI_Evakuasi;
    }

    public double getSi_rawatinap() {
        return SI_RawatInap;
    }

    public double getTotalPremi() {
        return TotalPremi;
    }

    public double getPremi_Meninggal() {
        return Premi_Meninggal;
    }

    public double getPremi_Cacat() {
        return Premi_Cacat;
    }

    public double getPremi_BiayaMedis() {
        return Premi_BiayaMedis;
    }

    public double getPremi_Evakuasi() {
        return Premi_Evakuasi;
    }

    public double getPremi_RawatInap() {
        return Premi_RawatInap;
    }
}



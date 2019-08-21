package beni.simulatorpremi.model.Astor;


import com.google.gson.annotations.SerializedName;


public class kendaraanModel {
    public kendaraanModel string;
    private String VehicleType;
    private String ManufactureYear;
    private String SDate;
    private String EDate;
    private String[] Coverages;
    private String Usage;
    private String Zona;
    private long TSI;
    private Boolean IsDecreasing;
    private int DecreasingPct;
    private additionalModel Additionals;

    public kendaraanModel(String VehicleType, String ManufactureYear, String SDate, String EDate, String[] Coverages, String Usage, String Zona,
                          long TSI, Boolean IsDecreasing, int DecreasingPct, additionalModel Additionals) {
        this.VehicleType = VehicleType;
        this.ManufactureYear = ManufactureYear;
        this.SDate = SDate;
        this.EDate = EDate;
        this.Coverages = Coverages;
        this.Usage = Usage;
        this.Zona = Zona;
        this.TSI = TSI;
        this.IsDecreasing = IsDecreasing;
        this.DecreasingPct = DecreasingPct;
        this.Additionals = Additionals;
    }
    String error;
    @SerializedName("alerts")
    Alerts alerts;

    String total_data;
    dataAstor data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Alerts getAlerts() {
        return alerts;
    }

    public void setAlerts(Alerts alerts) {
        this.alerts = alerts;
    }

    public String getTotal_data() {
        return total_data;
    }

    public void setTotal_data(String total_data) {
        this.total_data = total_data;
    }

    public dataAstor getdResponse() {
        return data;
    }

    public void setdResponse(dataAstor dResponse) {
        this.data = dResponse;
    }

}
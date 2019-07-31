package beni.simulatorpremi.model;


public class responeJson {

    String error;
    Alerts alerts;
    String total_data;
    dataResponse data;

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

    public dataResponse getdResponse() {
        return data;
    }

    public void setdResponse(dataResponse dResponse) {
        this.data = dResponse;
    }
}

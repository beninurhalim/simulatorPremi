package beni.simulatorpremi.model;

public class additionalModel {

//    kendaraanModel Additionals;
    String flood;
    String EQ;
    String SRCC;
    String TS;
    String PA_amount;
    String PADriver;
    String PAPassanger;
    String tjh_amount;
    String tjh;
    String tjh_passanger;

    public additionalModel(String flood, String EQ,
                           String SRCC, String TS, String PA_amount, String PADriver,
                           String PAPassanger, String tjh_amount, String tjh, String tjh_passanger) {
//        Additionals = additionals;
        this.flood = flood;
        this.EQ = EQ;
        this.SRCC = SRCC;
        this.TS = TS;
        this.PA_amount = PA_amount;
        this.PADriver = PADriver;
        this.PAPassanger = PAPassanger;
        this.tjh_amount = tjh_amount;
        this.tjh = tjh;
        this.tjh_passanger = tjh_passanger;
    }

}

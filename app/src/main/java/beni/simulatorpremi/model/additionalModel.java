package beni.simulatorpremi.model;

public class additionalModel {

//    kendaraanModel Additionals;
    int flood;
    int EQ;
    String SRCC;
    String TS;
    String PA_amount;
    int PADriver;
    int PAPassanger;
    String tjh_amount;
    String tjh;
    int tjh_passanger;

    public additionalModel(int flood, int EQ,
                           String SRCC, String TS, String PA_amount, int PADriver,
                           int PAPassanger, String tjh_amount, String tjh, int tjh_passanger) {
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

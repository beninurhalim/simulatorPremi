package beni.simulatorpremi.model.Astor;

public class additionalModel {

//    kendaraanModel Additionals;
    int flood, EQ,SRCC,TS;
    int PA_amount;
    int PADriver;
    int PAPassanger;
    int tjh_amount;
    int tjh;
    int tjh_passanger;


    public additionalModel(int flood, int EQ,
                           int SRCC, int TS, int PA_amount, int PADriver,
                           int PAPassanger, int tjh, int nilaitjh,int tjh_passanger) {
//        Additionals = additionals;
        this.flood = flood;
        this.EQ = EQ;
        this.SRCC = SRCC;
        this.TS = TS;
        this.PA_amount = PA_amount;
        this.PADriver = PADriver;
        this.PAPassanger = PAPassanger;
        this.tjh_amount = nilaitjh;
        this.tjh = tjh;
        this.tjh_passanger = tjh_passanger;
    }

}

package beni.simulatorpremi.model;


public class kendaraanModel {
        private String VehicleType;
        private String ManufactureYear;
        private String SDate;
        private String EDate;
        private String[] Coverages;
        private String Usage;
        private String Zona;
        private int TSI;
        private Boolean IsDecreasing;
        private int DecreasingPct;


        private additionalModel Additionals;
        public kendaraanModel(String VehicleType, String ManufactureYear, String SDate, String EDate,String[] Coverages, String Usage, String Zona,
                              int TSI, Boolean IsDecreasing, int DecreasingPct, additionalModel Additionals){
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

}

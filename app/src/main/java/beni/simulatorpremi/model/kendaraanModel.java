package beni.simulatorpremi.model;

import com.google.gson.annotations.SerializedName;

public class kendaraanModel {
        @SerializedName("VehicleType")
        private String VehicleType;
        @SerializedName("ManufactureYear")
        private String ManufactureYear;
        @SerializedName("SDate")
        private String SDate;
        @SerializedName("EDate")
        private String EDate;
        @SerializedName("Coverages")
        private String[] Coverages;
        private String Usage;
        private String Zona;
        private String TSI;
        private Boolean IsDecreasing;
        private String DecreasingPct;


        public kendaraanModel(String VehicleType, String ManufactureYear, String SDate, String EDate,String[] Coverages, String Usage, String Zona,
                              String TSI, Boolean IsDecreasing, String DecreasingPct){
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

        }

        public void setVehicleType(String VehicleType){
            this.VehicleType = VehicleType;
        }
        public String getVehicleType(){
            return VehicleType;
        }
        public void setManufactureYear(String ManufactureYear){
            this.ManufactureYear = ManufactureYear;
        }
        public String getManufactureYear(){
            return ManufactureYear;
        }
        public void setSDate(String SDate){
            this.SDate = SDate;
        }
        public String getSDate(){
            return SDate;
        }
        public void setEDate(String EDate){
            this.EDate = EDate;
        }
        public String getEDate(){
            return EDate;
        }
        public void setCoverages(String[] Coverages){
            this.Coverages = Coverages;
        }
        public String[] getCoverages(){
            return Coverages;
        }

}

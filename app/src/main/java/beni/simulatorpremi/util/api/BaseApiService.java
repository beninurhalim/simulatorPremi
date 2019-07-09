package beni.simulatorpremi.util.api;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface BaseApiService {


    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

//  register.php
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password);

//    POST DATA SIMULATOR
    @POST("rate")
    Call<PostResponse> postData(
        @Body JsonObject body);

    class PostResponse{
        @SerializedName("VehicleType")
        private String VehicleType;
        @SerializedName("ManufactureYear")
        private String ManufactureYear;
        @SerializedName("SDate")
        private String SDate;
        @SerializedName("EDate")
        private String EDate;

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
    }


//    @GET("semuadosen")
//    Call<ResponseDosen> getSemuaDosen();

//    @GET("dosen/{namadosen}")
//    Call<ResponseDosenDetail> getDetailDosen(@Path("namadosen") String namadosen);

//    @GET("matkul")
//    Call<ResponseMatkul> getSemuaMatkul();

    @FormUrlEncoded
    @POST("matkul")
    Call<ResponseBody> simpanMatkulRequest(@Field("nama_dosen") String namadosen,
                                           @Field("matkul") String namamatkul);

    @DELETE("matkul/{idmatkul}")
    Call<ResponseBody> deteleMatkul(@Path("idmatkul") String idmatkul);
}

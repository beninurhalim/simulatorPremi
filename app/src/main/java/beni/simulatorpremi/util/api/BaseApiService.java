package beni.simulatorpremi.util.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import beni.simulatorpremi.model.Astor.kendaraanModel;
import beni.simulatorpremi.model.Aspri.aspriModel;


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


    @POST("astor")
    Call<kendaraanModel> postKendaraan(
            @Body kendaraanModel kendaraan);

    @POST("aspri")
    Call<aspriModel> postAspri(
            @Body aspriModel aspri);
}
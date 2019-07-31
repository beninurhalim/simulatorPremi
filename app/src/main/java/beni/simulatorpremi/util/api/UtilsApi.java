package beni.simulatorpremi.util.api;

public class UtilsApi {

//    public static final String BASE_URL_API = "http://services.jp.co.id/api/rate/";
    public static final String BASE_URL_API = "http://beninurhalim.000webhostapp.com/API-Mahasiswa-master/";
    public static final String BASE_URL_API2 = "http://services.jp.co.id/api/rate/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

    public static BaseApiService getAPIService2(){
        return RetrofitClient.getClient2(BASE_URL_API2).create(BaseApiService.class);
    }
}

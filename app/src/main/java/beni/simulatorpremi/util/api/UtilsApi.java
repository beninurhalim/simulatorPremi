package beni.simulatorpremi.util.api;

/**
 * Created by fariz ramadhan.
 * website : www.farizdotid.com
 * github : https://github.com/farizdotid
 */
public class UtilsApi {

    // 10.0.2.2 ini adalah localhost.
//    public static final String BASE_URL_API = "http://10.0.2.2/mahasiswa/";
    public static final String BASE_URL_API = "http://beninurhalim.000webhostapp.com/API-Mahasiswa-master/";
    public static final String BASE_URL_API2 = "http://services.jp.co.id/rate/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

    public static BaseApiService getAPIService2(){
        return RetrofitClient.getClient2(BASE_URL_API2).create(BaseApiService.class);
    }
}

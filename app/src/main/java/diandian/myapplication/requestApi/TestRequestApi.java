package diandian.myapplication.requestApi;

import org.json.JSONObject;

import java.util.HashMap;

import diandian.myapplication.bean.TestLoginBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by funplus on 2018/5/14.
 */

public interface TestRequestApi {
    @FormUrlEncoded
    @POST("client_api.php?ver=3")
    Observable<TestLoginBean> loginByRxJava(@Header("Authorization") String authorization, @FieldMap HashMap<String,Object>map);

    @FormUrlEncoded
    @POST("client_api.php?ver=3")
    Call<TestLoginBean> loginByCall(@Header("Authorization") String authorization, @FieldMap HashMap<String,Object>map);
}

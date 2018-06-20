package diandian.myapplication.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by funplus on 2018/5/14.
 */

public class TestLoginBean {

    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private TestLogin loginBean;

    public int getStatus() {
        return status;
    }

    public TestLogin getLoginBean() {
        return loginBean;
    }
}

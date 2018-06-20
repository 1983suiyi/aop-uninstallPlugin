package diandian.myapplication.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by funplus on 2018/5/14.
 */

public class TestLogin {

    @SerializedName("fpid")
    private String fpid;
    @SerializedName("session_key")
    private String sessionKey;
    @SerializedName("is_new")
    private int isNew;
    @SerializedName("created_ts")
    private long createdTs;


    public String getFpid() {
        return fpid;
    }

    public long getCreatedTs() {
        return createdTs;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public int getIsNew() {
        return isNew;
    }
}

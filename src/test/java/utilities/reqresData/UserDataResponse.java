package utilities.reqresData;

import java.util.List;

public class UserDataResponse {

    public List<UserData> data;

    public List<UserData> getData() {
        return data;
    }

    public void setData(List<UserData> data) {
        this.data = data;
    }

    public UserDataResponse(List<UserData> data) {
        this.data = data;
    }
}

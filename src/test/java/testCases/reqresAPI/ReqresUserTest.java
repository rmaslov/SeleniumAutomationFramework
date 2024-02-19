package testCases.reqresAPI;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.reqresData.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresUserTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkUserAvatars(){
        String json = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/api/users?page=2")
                .then()
                .extract()
                .body().asString();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        //List<UserData> users = gson.fromJson(json, UserData.class)

        //users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        //Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.io")));
    }

}

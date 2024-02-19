package testCases.reqresAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.LoggerUtils;
import utilities.reqresData.ApiSpecification;
import utilities.reqresData.UserData;
import utilities.reqresData.UserDataResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresUserTest {

    @Test(description = "Checking that user avatars is correct")
    public void checkUserAvatars(){
        LoggerUtils.info("Getting list of users");
        ApiSpecification.installSpecification(ApiSpecification.requestSpecificationWBase("users"), ApiSpecification.responseSpecification200OK());
        String json = given()
                .when()
                .get("?page=2")
                .then()
                .extract()
                .body().asPrettyString();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<UserData> users = gson.fromJson(json, UserDataResponse.class).data;
        LoggerUtils.info("Checking avatar names");
        users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @Test(description = "Checking that user emails is correct")
    public void checkUserEmails(){
        LoggerUtils.info("Getting list of users");
        ApiSpecification.installSpecification(ApiSpecification.requestSpecificationWBase("users"), ApiSpecification.responseSpecification200OK());
        String json = given()
                .when()
                .get("?page=2")
                .then()
                .extract()
                .body().asPrettyString();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<UserData> users = gson.fromJson(json, UserDataResponse.class).data;
        LoggerUtils.info("Checking emails");
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }

}

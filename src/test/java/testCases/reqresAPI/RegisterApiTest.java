package testCases.reqresAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.LoggerUtils;
import utilities.reqresData.ApiSpecification;
import utilities.reqresData.Error;
import utilities.reqresData.Register;
import utilities.reqresData.SuccessRegistration;

import static io.restassured.RestAssured.given;

public class RegisterApiTest {

    @Test(description = "Register user")
    public void successRegister(){
        LoggerUtils.info("Register user");
        ApiSpecification.installSpecification(ApiSpecification.requestSpecificationWBase("register"), ApiSpecification.responseSpecification200OK());
        int id  = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register register = new Register("eve.holt@reqres.in","pistol");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = given()
                .body(gson.toJson(register, Register.class))
                .when()
                .post("")
                .then()
                .extract()
                .asPrettyString();
        SuccessRegistration successRegistration = gson.fromJson(json, SuccessRegistration.class);
        Assert.assertEquals(successRegistration.getId(), id);
        Assert.assertEquals(successRegistration.getToken(), token);

    }

    @Test(description = "Register user unsuccessfully")
    public void unSuccessRegister(){
        LoggerUtils.info("Register user without password");
        ApiSpecification.installSpecification(ApiSpecification.requestSpecificationWBase("register"), ApiSpecification.responseSpecification400BadRequest());
        String errorMsg = "Missing password";
        Register register = new Register("sydney@fife","");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = given()
                .body(gson.toJson(register, Register.class))
                .when()
                .post("")
                .then()
                .extract()
                .asPrettyString();
        Error error = gson.fromJson(json, Error.class);
        Assert.assertEquals(error.getError(), errorMsg);
    }

}

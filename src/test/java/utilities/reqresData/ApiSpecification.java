package utilities.reqresData;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiSpecification {

    public static RequestSpecification requestSpecificationUsers(){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setBasePath("api/users")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification requestSpecificationUnknown(){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setBasePath("api/unknown")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification requestSpecificationRegister(){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setBasePath("api/register")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification requestSpecificationLogin(){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setBasePath("api/login")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecification200OK(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

}

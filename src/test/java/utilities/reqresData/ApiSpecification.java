package utilities.reqresData;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiSpecification {

    public static RequestSpecification requestSpecificationWBase(String basePath){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setBasePath("api/"+basePath)
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

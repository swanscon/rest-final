package com.astontech.resttest;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


public class VehicleApiTestRestAssured {

    @Test
    public void testEndpointShouldReturn200() {
        // not really a test, just validates setup (APP SHOULD BE RUNNING)
        get("/test")
                .then()
                .statusCode(200);
    }

    @Test
    public void whenUsePathParamValidId_thenOK() {
        given().pathParam("id", 1)
                .when().get("/vehicles/{id}")
                .then().statusCode(200);
    }

    @Test
    public void whenUsePathParamInvalidId_thenNOT_FOUND() {
        given().pathParam("id", 9999)
                .when().get("/vehicles/{id}")
                .then().statusCode(404);
    }

    @Test
    public void whenUseQueryParamValidVin_thenOK() {
        given().queryParam("vin", "ABC123")
                .when().get("/vehicles")
                .then().statusCode(200);
    }

    @Test
    public void whenFindByVinAssertModelId() {
        given().queryParam("vin", "ABC123")
                .when().get("/vehicles")
                .then().statusCode(200)
                .assertThat()
                .body("vehicleLicense", equalTo("XYZ987"));
    }

    @Test
    public void getResponseTime() {
        System.out.println("Response time: " +get("/vehicles/").timeIn(TimeUnit.MILLISECONDS)+ " ms");
    }

    @Test
    public void getResponseContentType() {
        System.out.println("Content Type of response: " +get("/vehicles/").then().extract().contentType());
    }

    @Test
    public void saveProductShouldReturnAnID() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleVIN", "CMS716");
        requestBody.put("vehicleLicense", "NTH808");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .post("/vehicles/")
                .then().statusCode(201)
                .assertThat()
                .body("$", hasKey("id"))
                .body("vehicleVIN", equalTo("CMS716"))
                .body("vehicleLicense", equalTo("NTH808"));
    }

    @Test
    public void updateProductShouldReturnUpdatedProduct() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 1);
        requestBody.put("vehicleVIN", "UPD8TD");
        requestBody.put("vehicleLicense", "LIC175");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .put("/vehicles/")
                .then().statusCode(202)
                .assertThat()
                .body("id", equalTo(1))
                .body("vehicleVIN", equalTo("UPD8TD"))
                .body("vehicleLicense", equalTo("LIC175"));
    }

    @Test
    public void deleteProductShouldReturnNoContent() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleVIN", "CMS716");
        requestBody.put("vehicleLicense", "NTH808");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .delete("/vehicles/")
                .then().statusCode(204);
    }

    @Test
    public void deleteProductByIdShouldReturnNoContent() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleVIN", "CMS716");
        requestBody.put("vehicleLicense", "NTH808");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .delete("/vehicles/{id}", 1)
                .then().statusCode(204);
    }

    @Test
    public void patchVehicleWithInvalidIdShouldThrowException() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleVIN", "CMS716");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("id", 9999)
                .when().patch("/vehicles/{id}")
                .then().statusCode(404);
    }

    @Test
    public void patchVehicleWithInvalidFieldNameShouldThrowException() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("dog", "CMS716");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("id", 1)
                .when().patch("/vehicles/{id}")
                .then().statusCode(422);
    }

    @Test
    public void patchVehicleWithValidFieldsShouldUpdateResource() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleVIN", "CMS123");
        requestBody.put("vehicleLicense", "NTH808");
        requestBody.put("vehicleYear", "2021");
        requestBody.put("vehicleColor", "White");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .patch("/vehicles/1")
                .then().statusCode(202)
                .assertThat()
                .body("vehicleVIN", equalTo("CMS123"))
                .body("vehicleLicense", equalTo("NTH808"))
                .body("vehicleYear", equalTo("2021"))
                .body("vehicleColor", equalTo("White"));
    }
}

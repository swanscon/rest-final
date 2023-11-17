package com.astontech.resttest;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class VehicleModelApiTestRestAssured {

    @Test
    public void getResponseTime() {
        System.out.println("Response time: " +get("/vehicle-models/").timeIn(TimeUnit.MILLISECONDS)+ " ms");
    }

    @Test
    public void getResponseContentType() {
        System.out.println("Content Type of response: " +get("/vehicle-models/").then().extract().contentType());
    }

    @Test
    public void saveProductShouldReturnAnID() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleModelName", "Accord");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .post("/vehicle-models/")
                .then().statusCode(201)
                .assertThat()
                .body("$", hasKey("id"))
                .body("vehicleModelName", equalTo("Accord"));
    }

    @Test
    public void updateProductShouldReturnUpdatedProduct() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 1);
        requestBody.put("vehicleModelName", "Accord");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .put("/vehicle-models/")
                .then().statusCode(202)
                .assertThat()
                .body("id", equalTo(1))
                .body("vehicleModelName", equalTo("Accord"));
    }

    @Test
    public void deleteProductShouldReturnNoContent() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleModelName", "Accord");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .delete("/vehicle-models/")
                .then().statusCode(204);
    }

    @Test
    public void deleteProductByIdShouldReturnNoContent() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleModelName", "Accord");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .delete("/vehicle-models/{id}", 1)
                .then().statusCode(204);
    }

    @Test
    public void patchVehicleWithInvalidIdShouldThrowException() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleModelName", "Accord");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("id", 9999)
                .when().patch("/vehicle-models/{id}")
                .then().statusCode(404);
    }

    @Test
    public void patchVehicleWithInvalidFieldNameShouldThrowException() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("dog", "Accord");
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("id", 1)
                .when().patch("/vehicle-models/{id}")
                .then().statusCode(422);
    }

    @Test
    public void patchVehicleWithValidFieldsShouldUpdateResource() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("vehicleModelName", "Accord");
        requestBody.put("vehicleMakeId", 1);
        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .patch("/vehicle-models/1")
                .then().statusCode(202)
                .assertThat()
                .body("vehicleModelName", equalTo("Accord"))
                .body("vehicleMakeId", equalTo(1));
    }
}

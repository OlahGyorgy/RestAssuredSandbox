package com.example.sandbox;

import io.restassured.response.Response;

import java.util.Map;

import static com.example.sandbox.Endpoints.baseUrl;
import static io.restassured.RestAssured.given;

public class Common{

    //----------------------------------GET----------------------------------
    public Response getUrl(String endpoint, Map<String, String> queryParam ){


        return given()
                .relaxedHTTPSValidation()
                .params(queryParam)
                .and()
                .log().everything()
                .when()
                .get(baseUrl+endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }
    public Response getUrl(String endpoint,Map<String, String> headers,Map<String, String> queryParam ){


        return given()
                .relaxedHTTPSValidation()
                .params(queryParam)
                .headers(headers)
                .and()
                .log().all()
                .when()
                .get(baseUrl+endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }

    //----------------------------------POST----------------------------------

    //----------------------------------PUT----------------------------------

    //----------------------------------DELETE----------------------------------
}


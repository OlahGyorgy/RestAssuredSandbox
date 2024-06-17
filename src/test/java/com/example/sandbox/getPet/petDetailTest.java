package com.example.sandbox.getPet;

import com.example.sandbox.Common;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.report.TestListener;

import java.util.Map;
import java.util.TreeMap;

import static com.example.sandbox.util.constans.Tags.SMOKE;

@Listeners(TestListener.class)
public class petDetailTest extends Common {

    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void Test1(){
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("status","available");

        Response  response = getUrl(findByStatus, queryParams);
        Assert.assertEquals(response.getStatusCode(),200,"Invalid response code");

        String id = response.jsonPath().get("find{it.status.equals('available')}.id").toString();

        Response  response2 = getUrl(petById.replace("{petId}",id));
        Assert.assertEquals(response2.getStatusCode(),200,"Invalid response code");
    }
}

package com.telran.scheduler.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAPITests {

    String baseURL = "https://super-scheduler-app.herokuapp.com";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFuYUBnbWFpbC5jb20ifQ.-TcGy8MKAmbYXCuf0I4-NKJxebTcizV60YHxBuVRESA";

    @Test
    public void postRecordTest() throws IOException {
        int result = Request.Post(baseURL + "/api/record")
                .addHeader("Authorization", token)
                .bodyString("{\n" +
                        "  \"breaks\": 1,\n" +
                        "  \"currency\": \"RUB\",\n" +
                        "  \"date\": {\n" +
                        "    \"dayOfMonth\": 5,\n" +
                        "    \"dayOfWeek\": \"Mo\",\n" +
                        "    \"month\": 3,\n" +
                        "    \"year\": 2021\n" +
                        "  },\n" +
                        "  \"hours\": 1.0,\n" +
                        "  \"id\": ,\n" +
                        "  \"timeFrom\": \"09:00\",\n" +
                        "  \"timeTo\": \"12:00\",\n" +
                        "  \"title\": \"Rusaldo\",\n" +
                        "  \"totalSalary\": 36,\n" +
                        "  \"type\": \"Add\",\n" +
                        "  \"wage\": 8\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute().returnResponse().getStatusLine().getStatusCode();
        System.out.println("Status code is " + result);
    }

    @Test
    public void getRecordsPeriodTest() throws IOException {
        String result = Request.Get(baseURL + "/api/recordsPeriod")
                .addHeader("Authorization", token)
                .addHeader("Content-type", "application/json")
                .execute().returnContent().asString();
        System.out.println(result);

        String events = Request.Post(baseURL + "/api/records")
                .addHeader("Authorization", token)
                .addHeader("Content-type", "application/json")
                .bodyString(result, ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        System.out.println(events);
    }

    @Test
    public void loginRegisteredUserTest() throws IOException {
        String response = sendRequestToLogin("/api/login", "ana@gmail.com", "Ana123456");

        //System.out.println(response);
        JsonElement parsed = new JsonParser().parse(response);
        JsonElement token = parsed.getAsJsonObject().get("token");
        System.out.println(token);
        JsonElement status = parsed.getAsJsonObject().get("status");
        Assert.assertEquals(status.toString(), "\"Login success\"");
        JsonElement registration = parsed.getAsJsonObject().get("registration");
        //Assert.assertEquals(registration.toString(),"false");
        Assert.assertFalse(registration.getAsBoolean());
    }

    @Test
    public void getCodeResponseLoginTest() throws IOException {
        String email = "ana@gmail.com";
        String password = "Ana123456";

        int statusCode = Request.Post(baseURL + "/api/login").bodyString("{ \"email\": \"" + email + "\", \"password\": \""
                + password + "\"}", ContentType.APPLICATION_JSON)
                .execute().returnResponse().getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public String sendRequestToLogin(String controller, String email, String password) throws IOException {
        String response = Request.Post(baseURL + controller)
                .bodyString("{ \"email\": \"" + email + "\", \"password\": \""
                        + password + "\"}", ContentType.APPLICATION_JSON).execute()
                .returnContent().asString();
        return response;
    }
}

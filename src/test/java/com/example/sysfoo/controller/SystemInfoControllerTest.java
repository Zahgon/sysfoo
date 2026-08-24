package com.example.sysfoo.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import com.example.sysfoo.service.SystemInfoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SystemInfoControllerTest {

    @InjectMock
    SystemInfoService systemInfoService;

    @Test
    public void getVersionTest() {
        when(systemInfoService.getAppVersion()).thenReturn("1.0.0");
        given()
                .when().get("/version")
                .then()
                .statusCode(200)
                .body(is("1.0.0"));
    }
}

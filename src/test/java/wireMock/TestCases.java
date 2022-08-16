package wireMock;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCases {

    @Test
    public void testOne(){
        RestAssured.
                given().
                    get("http://localhost:8080/get/user/1").
                then().
                    assertThat().
                    statusCode(200).
                    log().all();
    }
    
    @Test
    public void testTwo(){
        RestAssured.
                given().
                    get("http://localhost:8080/get/user/2").
                then().
                    assertThat().
                    statusCode(201).
                    log().all();
    }

}

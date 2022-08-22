package wireMock;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StartServerFromCode {

    // Here we are starting the wiremock server by the code as below
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static WireMockServer server = new WireMockServer(PORT);

    @BeforeClass
    public void initializeServer(){
        System.out.println("Init");

        // Start Wiremok server via code and configure it with required host and post
        server.start();  // starting particular wirMock server
        WireMock.configureFor(HOST, PORT);

        // Now lets do req response mapping
        // Mock-Response first
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(201);
        mockResponse.withStatusMessage("Hello Guys.....!");
        mockResponse.withHeader("Content-Type","text/json");
        mockResponse.withHeader("token","11111");
        mockResponse.withHeader("Set-Cookie", "session_id=9183746787");
        mockResponse.withHeader("Set-Cookie", "split_test_group=B");
        mockResponse.withBody("text to put in the body");


        // Mock-Request - both below format will work
//        WireMock.stubFor(WireMock.get("/emps/1").willReturn(mockResponse));
        WireMock.givenThat(WireMock.get("/emps/1").willReturn(mockResponse));

    }


    @Test
    public void testCode(){
        String testApi = "http://localhost:" + PORT + "/emps/1"; // creating testAPI URL
        System.out.println("Service to be hit: "+ testApi);

        Response response = RestAssured.
                given().
                    get("http://localhost:8080/emps/1").
                then().statusCode(201).log().all().
                    extract().response();

        System.out.println();
        // Assert.assertEquals(response.getStatusCode(), "201");
        Assert.assertEquals(response.getHeader("token"), "11111");
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Hello Guys.....!");
        Assert.assertEquals(response.getCookie("session_id"), "9183746787");
        Assert.assertEquals(response.getCookie("split_test_group"), "B");
        Assert.assertEquals(response.getBody().asString(), "text to put in the body");
    }



    @AfterClass
    public void closeServer(){
        if(server.isRunning() && null != server){
            System.out.println("Shutdown...!");
            server.shutdown();
        }
    }
}

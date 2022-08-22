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

public class ReadResponseFromFile {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String END_POINT = "/readfromfile/index";
    private static WireMockServer server = new WireMockServer(PORT);

    @BeforeClass
    public void initializeServer() {
        System.out.println("Init");

        // Start Wiremok server via code and configure it with required host and post
        server.start();  // starting particular wirMock server
        WireMock.configureFor(HOST, PORT);

        // Now lets do req response mapping
        // Mock-Response first

        //filepath: src/test/resource/__files/json/index.json
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(201);
        mockResponse.withBodyFile("json/index.json");

        //Mocking
        WireMock.stubFor(WireMock.get(END_POINT).willReturn(mockResponse));
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

        Assert.assertEquals(response.jsonPath().get("glossary.title"),"Lord of the Ring");
        Assert.assertEquals(response.jsonPath().get("glossary.GlossaryDiv.GlossList.GlossEntry.GlossDef.GlossSeeAlso[0]"),"GML");
    }

    @AfterClass
    public void closeServer(){
        if(server.isRunning() && null != server){
            System.out.println("Shutdown...!");
            server.shutdown();
        }
    }
}

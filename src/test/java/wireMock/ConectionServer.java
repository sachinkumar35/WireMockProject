package wireMock;

import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class ConectionServer {

	@Test
	  public void wmtest() {
	        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8089));
	        wireMockServer.start();
	        WireMock.configureFor("localhost", 8089);
	        WireMock.stubFor(WireMock.get("/emps/1"));
	        System.out.println("Completed");
	              
	                        
	        wireMockServer.stop();
	    }
}

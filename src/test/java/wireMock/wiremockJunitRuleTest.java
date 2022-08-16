package wireMock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import okhttp3.*;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;

public class wiremockJunitRuleTest {
	
	
	@Rule
    public WireMockRule wir = new WireMockRule(wireMockConfig().port(8080));
    
    @Test
    public void wiremock_rule_test() throws IOException {
        
        //arrange
        //Configure stubs programmatically
        configureStubs();
        
        
        //act
        // we will call request in wiremock through okhttpclient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        @SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://localhost:8080/user/1")
                .method("GET", body)
                .build();
        Response response = client.newCall(request).execute();
        
        
        //assert
        // assert the response for the api call make to okgttpclient
        assertEquals("Welcome to wiremock",response.body().string());
    }

    private void configureStubs() {
        configureFor("localhost",8080);
        stubFor(get(urlEqualTo("/user/1")).willReturn(aResponse().withBody("Welcome to Wiremock")));


    }


}

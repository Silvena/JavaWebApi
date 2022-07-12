import Entities.RateLimit;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static Entities.User.ID;
import static org.testng.AssertJUnit.assertEquals;

public class BodyWithJackson extends BaseClass{
    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }
    @AfterMethod
    public  void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @Test
    public void correctRateLimit() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);
        RateLimit rateLimit = ResponseUtils.unmarshallGeneric(response,RateLimit.class);
        Assert.assertEquals(rateLimit.getCoreLimit(),60);
        Assert.assertEquals(rateLimit.getSearchLimit(),"10");
    }

}

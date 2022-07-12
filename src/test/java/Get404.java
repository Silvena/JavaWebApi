import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class Get404 extends BaseClass{

    CloseableHttpClient client;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @Test
    public void nonExistingUrlReturns404() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonExistingUrl");
        HttpResponse response = client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        assertEquals(actualStatus, 404);
        System.out.println("The status is " + actualStatus);
    }
}


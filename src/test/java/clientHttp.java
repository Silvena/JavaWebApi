import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class clientHttp {

    public static final String BASE_ENDPOINT = "https://api.github.com";
    HttpClient client = HttpClientBuilder.create().build();

    @Test
    public void baseUrlReturn200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        HttpResponse response =  client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatus, 200);
        System.out.println(actualStatus);
    }
}

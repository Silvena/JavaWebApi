import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get401 extends BaseClass {

   CloseableHttpClient client;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @DataProvider
    private Object[][] endpoints(){
        return new Object[][]{
                {"/user"},
                {"/user/followers"},
                {"/notifications"}
        };
    }

    @Test(dataProvider = "endpoints")
    public void userReturns401(String endpoint) throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + endpoint);
        HttpResponse response = client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        assertEquals(actualStatus, 401);
        System.out.println("The status is " + actualStatus);
    }

//    @Test
//    public void userFollowersReturns401() throws IOException {
//        HttpGet get = new HttpGet(BASE_ENDPOINT + "/user/followers");
//        HttpResponse response = client.execute(get);
//        int actualStatus = response.getStatusLine().getStatusCode();
//        assertEquals(actualStatus, 401);
//        System.out.println("The status is " + actualStatus);
//    }
//
//    @Test
//    public void userNotificationsReturns401() throws IOException {
//        HttpGet get = new HttpGet(BASE_ENDPOINT + "/notifications");
//        HttpResponse response = client.execute(get);
//        int actualStatus = response.getStatusLine().getStatusCode();
//        assertEquals(actualStatus, 401);
//        System.out.println("The status is " + actualStatus);
//    }
}


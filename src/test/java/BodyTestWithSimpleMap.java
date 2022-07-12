import Entities.User;
import Entities.NotFound;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static Entities.User.ID;
import static org.testng.AssertJUnit.assertEquals;


public class BodyTestWithSimpleMap extends BaseClass{
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
    public void returnsCorrectLogin() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());
        System.out.println(jsonBody);
        JSONObject jsonObject = new JSONObject(jsonBody);
        String loginValue = (String) getValueFor (jsonObject, User.LOGIN);
        assertEquals(loginValue, "andrejss88");
        String avatarUrlValue = (String) getValueFor (jsonObject, "url");
        System.out.println(avatarUrlValue);
        assertEquals(avatarUrlValue, "https://api.github.com/users/andrejss88");
        Integer avatarNumValue = (Integer) getValueFor (jsonObject, ID);
        System.out.println(avatarNumValue);
        assertEquals(avatarNumValue, Integer.valueOf(11834443));
    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

    @Test
    public void returnsLogin() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);
        User user = ResponseUtils.unmarshallGeneric(response,User.class);
    }


    @Test(enabled = false)
    public void notFoundMessage() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/usersfdsgsg");
        response = client.execute(get);
        NotFound notFoundMessage = ResponseUtils.unmarshallGeneric(response, NotFound.class);
        Assert.assertEquals(notFoundMessage.getMessage(), "Not Found");
    }
}

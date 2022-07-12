import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Delete extends BaseClass{
    @Test
    public void deleteIsSuccessful()throws IOException {
        HttpDelete request = new HttpDelete("https://api.github.com/repos/andrejss88/deleteme");
        request.setHeader(HttpHeaders.AUTHORIZATION, "token lkjfsdh-dfsdkf-gweg-fsgs");
        response = client.execute(request);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 204);

    }
}

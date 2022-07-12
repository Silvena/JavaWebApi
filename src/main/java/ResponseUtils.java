import Entities.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils {
    public static String getHeader(CloseableHttpResponse response, String headerName) {
        //Get All headers
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeaders ="";

        //loop over the header list
        for (Header header : httpHeaders) {
            if (headerName.equalsIgnoreCase(header.getName())){
                returnHeaders = header.getValue();
            }
        }
        // If no header found -throw an exception
        if (returnHeaders.isEmpty()){
            throw new RuntimeException(("Didn't find the header: " + headerName));
        }
        // return the header
        return returnHeaders;
    }

    public static String getHeaderJava8Way(CloseableHttpResponse response, String headerName){
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        Header matchedHeader = httpHeaders.stream()
                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Didn't find the header"));
        return matchedHeader.getValue();
    }

    //boolean
    public static boolean  headerIsPresent(CloseableHttpResponse response, String headerName){
        List<Header> httpHeader = Arrays.asList(response.getAllHeaders());

        return  httpHeader.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }

    public static User unmarshall (CloseableHttpResponse response, Class<User> clazz) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, clazz);
    }

    public static <T> T unmarshallGeneric (CloseableHttpResponse response, Class<T> clazz) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, clazz);
    }
}

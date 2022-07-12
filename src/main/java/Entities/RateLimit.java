package Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RateLimit {
    private int coreLimit;
    private String searchLimit;

    public int getCoreLimit() {
        return coreLimit;
    }

    public String getSearchLimit() {
        return searchLimit;
    }
    @SuppressWarnings("unchecked")
    @JsonProperty("resources")
    private void unmarshallNested(Map<String, Object> resources) {
        Map<String, Integer> core = (Map<String, Integer>) resources.get("core");
        coreLimit= core.get("limit");

        Map<String, Integer> search = (Map<String, Integer>) resources.get("search");
        searchLimit= String.valueOf(search.get("limit"));
        System.out.println(searchLimit);
    }
}

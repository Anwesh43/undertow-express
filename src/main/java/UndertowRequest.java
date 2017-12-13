import java.util.HashMap;
import java.util.Map;

public class UndertowRequest {
    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getQueries() {
        return queries;
    }

    public void setQueries(Map<String, String> queries) {
        this.queries = queries;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }
    private UndertowRequest() {

    }
    public static UndertowRequest newInstance() {
        return new UndertowRequest();
    }
    private Map<String,String> headers = new HashMap<String, String>();
    private Map<String,String> params = new HashMap<String,String>();
    private Map<String,String> queries = new HashMap<String, String>();
    private Map<String,String> body = new HashMap<String, String>();
    private String matchingPath;
    private String actualPath;

    public String getMatchingPath() {
        return matchingPath;
    }

    public void setMatchingPath(String matchingPath) {
        this.matchingPath = matchingPath;
    }

    public String getActualPath() {
        return actualPath;
    }

    public void setActualPath(String actualPath) {
        this.actualPath = actualPath;
    }
}

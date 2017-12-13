import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import io.undertow.util.Methods;

import java.util.*;
public class UndertowRouter implements Router{
    public Map<String,HandlerCallback> postRequests = new HashMap<String,HandlerCallback>();
    public Map<String,HandlerCallback> getRequests = new HashMap<String,HandlerCallback>();
    public void post(String path,HandlerCallback callback) {
        postRequests.put(path,callback);
    }
    public void get(String path,HandlerCallback callback) {
        getRequests.put(path,callback);
    }
    public void route(HttpServerExchange exchange) {
        HttpString method = exchange.getRequestMethod();
        if(method.equals(Methods.POST)) {
            processPostCall(exchange);
        }
        if(method.equals(Methods.GET)) {
            processGetCall(exchange);
        }

    }
    private UndertowRequest processCall(HttpServerExchange exchange,Set<String> pathset) {
        String path = exchange.getRequestPath();
        Map<String,String> params = new HashMap<String, String>();
        String request_path_parts[] =  path.replaceFirst("/","").split("/");
        for(String pathEntry:pathset) {
            String request_parts[] = pathEntry.replaceFirst("/","").split("/");
            boolean found = true;
            if(request_parts.length == request_path_parts.length) {
                for(int i=0;i<request_parts.length;i++) {
                    if(request_parts[i].equals(request_path_parts[i])) {

                    }
                    else if(request_parts.length > 0 && request_parts[i].charAt(0) == ':'){
                        params.put(request_parts[i].replace(":",""),request_path_parts[i]);
                    }
                    else {
                        found = false;
                        break;
                    }
                }
            }
            if(found) {
                UndertowRequest request = UndertowRequest.newInstance();
                request.setParams(params);
                request.setMatchingPath(pathEntry);
                request.setActualPath(path);
                return request;
            }
        }
        return null;
    }
    private void processPostCall(HttpServerExchange exchange) {
        processAndExecuteCall(exchange,postRequests);
    }
    private void execute(HandlerCallback handlerCallback,UndertowRequest req,UndertowResponse response) {
        handlerCallback.callback(req,response);
    }
    private void processAndExecuteCall(HttpServerExchange exchange,Map<String,HandlerCallback> requestMap) {
        UndertowRequest request = processCall(exchange,requestMap.keySet());
        UndertowResponse response = UndertowResponse.newInstance(exchange);
        if(request != null) {
            HandlerCallback callback = requestMap.get(request.getMatchingPath());
            if(callback != null) {
                execute(callback,request,response);
            }
        }
    }
    private void processGetCall(HttpServerExchange exchange) {
        processAndExecuteCall(exchange,getRequests);
    }
}

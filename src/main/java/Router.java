import io.undertow.server.HttpServerExchange;

public interface Router {
    void post(String path,HandlerCallback handlerCallback);
    void get(String path,HandlerCallback handlerCallback);
    void route(HttpServerExchange exchange);
}

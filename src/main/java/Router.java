import io.undertow.server.HttpServerExchange;

public interface Router extends UndertowMiddleware {
    void post(String path,HandlerCallback handlerCallback);
    void get(String path,HandlerCallback handlerCallback);
}

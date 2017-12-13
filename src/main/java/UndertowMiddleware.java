import io.undertow.server.HttpServerExchange;

public interface UndertowMiddleware {
    void callback(HttpServerExchange exchange);
}

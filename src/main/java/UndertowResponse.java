import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class UndertowResponse {
    private HttpServerExchange exchange;
    private UndertowResponse(HttpServerExchange serverExchange) {
        this.exchange = serverExchange;
    }
    public static UndertowResponse newInstance(HttpServerExchange exchange) {
        return new UndertowResponse(exchange);
    }
    public void send(String text) {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE,"text");
        exchange.getResponseSender().send(text);
    }
}

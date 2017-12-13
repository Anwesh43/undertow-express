import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.util.LinkedList;
import java.util.List;

public class UndertowExpress {
    private Undertow.Builder builder;
    private List<UndertowMiddleware> middlewares = new LinkedList<UndertowMiddleware>();
    private UndertowExpress() {
        builder = Undertow.builder();
    }
    public static UndertowExpress newInstance(){
        return new UndertowExpress();
    }
    public void listen(int port,UndertowCallback...callbacks) {
        builder.addHttpListener(port,"localhost").setHandler(new HttpHandler() {
            public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
                for(UndertowMiddleware middleware:middlewares) {
                    middleware.callback(httpServerExchange);
                }
            }
        });
        Undertow undertow = builder.build();
        undertow.start();
        for(UndertowCallback callback:callbacks) {
            callback.callback();
        }
    }
    public void use(UndertowMiddleware middleware) {
        middlewares.add(middleware);
    }
}

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class UndertowDemo {
    public static void main(String args[]) {
        try {
            final Router router = new UndertowRouter();
            router.get("/hello", new HandlerCallback() {
                public void callback(UndertowRequest request, UndertowResponse response) {
                    response.send("hello world");
                }
            });
            router.get("/hello/:name/:age", new HandlerCallback() {
                public void callback(UndertowRequest request, UndertowResponse response) {
                    response.send(String.format("hello %s %s",request.getParams().get("name"),request.getParams().get("age")));
                }
            });
            Undertow server = Undertow.builder().addHttpListener(8000,"localhost").setHandler(new HttpHandler(){
                public void handleRequest(HttpServerExchange exchange) throws Exception {
                    router.route(exchange);
                }
            }).build();
            server.start();
            System.out.println("started server");
        }
        catch(Exception ex) {

        }
    }
}

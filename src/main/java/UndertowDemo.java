import java.util.Map;

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
            router.get("/relationbetween/:person1/:person2/:relation", new HandlerCallback() {
                public void callback(UndertowRequest request, UndertowResponse response) {
                    Map<String,String> params = request.getParams();
                    response.send(String.format("%s and %s are %s",params.get("person1"),params.get("person2"),params.get("relation")));
                }
            });
            UndertowExpress express =  UndertowExpress.newInstance();
            express.use(router);
            express.listen(8000, new UndertowCallback() {
                public void callback() {
                    System.out.println("started server");
                }
            });
        }
        catch(Exception ex) {

        }
    }
}

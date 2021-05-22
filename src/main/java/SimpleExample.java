import static spark.Spark.*;
import static spark.Spark.get;

public class SimpleExample {

    public static void main(String[] args) {

        port(5678);// <- Uncomment this if you want spark to listen to port 5678 instead of the default 4567
                get("/hello", (request, response) -> "Hello World!");

        post("/hello", (request, response) ->

                "Hello World: " + request.body()
        );


        get("/calculadora", (request, response) -> {
            String received;
            String toreturn = null;
            String tokens[];
            float op1,op2;
            received = request.body();
            if(received.contains("+")){
                tokens=received.split("\\+");
                tokens[0].trim();
                tokens[1].trim();
                op1 = Float.parseFloat(tokens[0]);
                op2 = Float.parseFloat(tokens[1]);
                toreturn =String.valueOf(op1+op2);
            }else {
                if (received.contains("-")) {
                    tokens = received.split("\\-");
                    tokens[0].trim();
                    tokens[1].trim();
                    op1 = Float.parseFloat(tokens[0]);
                    op2 = Float.parseFloat(tokens[1]);
                    toreturn = String.valueOf(op1 - op2);
                } else {
                    if (received.contains("*")) {
                        tokens = received.split("\\*");
                        tokens[0].trim();
                        tokens[1].trim();
                        op1 = Float.parseFloat(tokens[0]);
                        op2 = Float.parseFloat(tokens[1]);
                        toreturn = String.valueOf(op1 * op2);
                    } else {
                        if (received.contains("/")) {
                            tokens = received.split("/");
                            tokens[0].trim();
                            tokens[1].trim();
                            op1 = Float.parseFloat(tokens[0]);
                            op2 = Float.parseFloat(tokens[1]);
                            toreturn = String.valueOf(op1/op2);
                        }
                    }
                }
            }
            return "O resultado eh: " + toreturn;
        });


        get("/private", (request, response) -> {
            response.status(401);
            return "Go Away!!!";
        });

        get("/users/:name", (request, response) -> "Selected user: " + request.params(":name"));

        get("/news/:section", (request, response) -> {
            response.type("text/xml");
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><news>" + request.params("section") + "</news>";
        });

        get("/protected", (request, response) -> {
            halt(403, "I don't think so!!!");
            return null;
        });

        get("/redirect", (request, response) -> {
            response.redirect("/news/world");
            return null;
        });

        get("/", (request, response) -> "root");
    }
}
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/login", new LoginHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("Server started on port 8000");
        server.start();
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                // Only allow POST
                String response = "Only POST method is supported";
                exchange.sendResponseHeaders(405, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }

            // Read and decode the request body
            InputStream is = exchange.getRequestBody();
            StringBuilder sb = new StringBuilder();
            int i;
            while ((i = is.read()) != -1) {
                sb.append((char) i);
            }

            String requestBody = sb.toString();
            Map<String, String> params = parseFormData(requestBody);

            String username = params.get("username");
            String password = params.get("password");

            String response;
            if ("admin".equals(username) && "admin".equals(password)) {
                response = "Login successful";
                exchange.sendResponseHeaders(200, response.length());
            } else {
                response = "Invalid credentials";
                exchange.sendResponseHeaders(401, response.length());
            }

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private Map<String, String> parseFormData(String formData) throws IOException {
            Map<String, String> map = new HashMap<>();
            String[] pairs = formData.split("&");
            for (String pair : pairs) {
                String[] parts = pair.split("=");
                if (parts.length == 2) {
                    String key = URLDecoder.decode(parts[0], StandardCharsets.UTF_8.name());
                    String value = URLDecoder.decode(parts[1], StandardCharsets.UTF_8.name());
                    map.put(key, value);
                }
            }
            return map;
        }
    }
}
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginServerJDBC {

    // Update your DB credentials here
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2408";

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/login", new LoginHandler());
        server.setExecutor(null);
        System.out.println("Server started on port 8000");
        server.start();
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                String response = "Only POST method is supported";
                exchange.sendResponseHeaders(405, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }

            // Read and decode form data
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

            if (isValidCredentials(username, password)) {
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

        private boolean isValidCredentials(String username, String password) {
            boolean isValid = false;
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            isValid = true;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Consider proper logging in production
            }
            return isValid;
        }
    }
}

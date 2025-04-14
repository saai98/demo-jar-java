package com.example.demo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class DemoApplication {
    public static void main(String[] args) throws IOException {
        int port = 9191;
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
        
        // Add root context handling
        server.createContext("/", new HelloHandler()); // Added this line for root context
        
        server.createContext("/hello", new HelloHandler());
        server.setExecutor(null);
        System.out.println("Server started on port " + port);
        server.start();
    }

    static class HelloHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello from demo JAR app!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imagedisplay;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import javax.imageio.ImageIO;

public class ImageServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/image", new ImageHandler());
        server.createContext("/", new IndexHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server started");
    }

    static class ImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Get requested image name from query parameter
            String imageName = exchange.getRequestURI().getQuery().split("=")[1];

            // Load image from file
            BufferedImage image = ImageIO.read(new File("C:\\Users\\rasha\\Desktop\\" + imageName + ".png"));

            // Convert image to byte array and send response
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageData = baos.toByteArray();
            exchange.sendResponseHeaders(200, imageData.length);
            OutputStream os = exchange.getResponseBody();
            os.write(imageData);
            os.close();
        }
    }

    static class IndexHandler implements HttpHandler {
/**
 * Handles requests for images.
 * 
 * @param exchange the HttpExchange object representing the client's request and the server's response
 * @throws IOException if an I/O error occurs while processing the request
 */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Serve HTML page with links to the two images
           String response = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "  <head>\n" +
        "    <title>Select the sudoku</title>\n" +
        "    <style>\n" +
        "      body {\n" +
        "        font-family: Arial, sans-serif;\n" +
        "        background-color: #f1f1f1;\n" +
        "      }\n" +
        "      h1 {\n" +
        "        color: #007bff;\n" +
        "        margin-top: 50px;\n" +
        "        text-align: center;\n" +
        "      }\n" +
        "      a {\n" +
        "        display: block;\n" +
        "        text-align: center;\n" +
        "        font-size: 24px;\n" +
        "        color: #fff;\n" +
        "        background-color: #007bff;\n" +
        "        border-radius: 10px;\n" +
        "        padding: 10px;\n" +
        "        width: 200px;\n" +
        "        margin: 0 auto;\n" +
        "        margin-top: 30px;\n" +
        "        text-decoration: none;\n" +
        "      }\n" +
        "      a:hover {\n" +
        "        background-color: #0056b3;\n" +
        "      }\n" +
        "    </style>\n" +
        "  </head>\n" +
        "  <body>\n" +
        "    <h1>Select the sudoku</h1>\n" +
        "    <div class=\"image-links\">\n" +
        "      <a href=\"/image?image=image1\">Solved Sudoku</a>\n" +
        "      <a href=\"/image?image=image2\">Unsolved Sudoku</a>\n" +
        "    </div>\n" +
        "  </body>\n" +
        "</html>";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}

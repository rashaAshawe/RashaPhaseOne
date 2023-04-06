/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imagedisplay;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageClient {
     /**
     * Displays the selected image from the server.
     * 
     * @param args An array of command-line arguments.
     * @throws IOException If an I/O error occurs while connecting to the server or reading the response.
     */
    public static void main(String[] args) throws IOException {
        // Get user input for image choice
        String imageChoice;
        do {
            System.out.print("Enter 'image1' or 'image2': ");
            imageChoice = System.console().readLine().toLowerCase();
        } while (!imageChoice.equals("image1") && !imageChoice.equals("image2"));

        // Send HTTP request for selected image
        URL url = new URL("http://localhost:8080/image?image=" + imageChoice);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        // Read response as image and display in JFrame
        InputStream inputStream = conn.getInputStream();
        BufferedImage image = ImageIO.read(inputStream);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }
}

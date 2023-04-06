/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package imagedisplay;
import javax.swing.*;
import java.awt.event.*;

public class ImageDisplay extends JFrame implements ActionListener {
    JButton button1, button2;

    public ImageDisplay() {
        setTitle("Image Display");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create two buttons
        button1 = new JButton("Show soduko");
        button2 = new JButton("Show the solution");

        // Add action listeners to buttons
        button1.addActionListener(this);
        button2.addActionListener(this);

        // Add buttons to frame
        JPanel panel = new JPanel();
        panel.add(button1);
        panel.add(button2);
        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            // Show first image
            ImageIcon icon = new ImageIcon("C:\\Users\\rasha\\Desktop\\notSolved.png");
            JLabel label = new JLabel(icon);
            JOptionPane.showMessageDialog(null, label);
        } else if (e.getSource() == button2) {
            // Show second image
            ImageIcon icon = new ImageIcon("C:\\Users\\rasha\\Desktop\\solved.png");
            JLabel label = new JLabel(icon);
            JOptionPane.showMessageDialog(null, label);
        }
    }

    public static void main(String[] args) {
        new ImageDisplay().setVisible(true);
    }
}
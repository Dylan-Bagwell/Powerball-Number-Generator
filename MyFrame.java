/**
 *
 * Author: Dylan Bagwell
 * Date: 28/06/2025
 * Version: 1.0.0
 * Description: This class defines the main application window for the Power Ball Generator.
 * It sets up the JFrame, adds the custom animation panel, and configures window properties.
 */
import javax.swing.JFrame;
public class MyFrame extends JFrame {

    MyPanel panel;

    MyFrame(){

        panel = new MyPanel();
        
        this.setTitle("Power Ball Generator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        
        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // Center the frame on the screen
        
    }
}

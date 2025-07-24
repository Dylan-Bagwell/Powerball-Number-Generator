/**
 *
 * Author: Dylan Bagwell
 * Date: 28/06/2025
 * Version: 1.0.0
 * Description: This class defines a custom JPanel that animates regular and Powerball circles inside a container.
 * It displays moving circles with numbers, highlights selected numbers, and includes a button to generate new random Powerball numbers.
 */
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyPanel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 1200;
    final int PANEL_HEIGHT = 800;
    final int NUM_CIRCLES = 35; // Number of circles to draw
    final int POWER_BALLS = 20; // Number of power balls to draw

    Image circle;
    Image container;
    Timer timer;

    // Choosen numbers
    int number[];

    // Arrays for regular circles
    int[] xVelocity = new int[NUM_CIRCLES];
    int[] yVelocity = new int[NUM_CIRCLES];
    int[] x = new int[NUM_CIRCLES];
    int[] y = new int[NUM_CIRCLES];
    int[] displayNumber = new int[NUM_CIRCLES];
    Color[] circleColors = new Color[NUM_CIRCLES];

    // Arrays for power balls (separate from regular circles)
    int[] powerBallX = new int[POWER_BALLS];
    int[] powerBallY = new int[POWER_BALLS];
    int[] powerBallXVelocity = new int[POWER_BALLS];
    int[] powerBallYVelocity = new int[POWER_BALLS];
    int[] powerBallNum = new int[POWER_BALLS];
    
    Color[] powerBallColors = new Color[POWER_BALLS];


    int circleDiameter = 50;
    int containerDiameter = 500; // Diameter of the container circle
    int speed = 5; // Movement speed when keys are pressed
    int selectedCircle = 0; // Which circle is currently controlled

    MyPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        // Load images here (if needed)
        // circle = new ImageIcon("images/heal.png").getImage();

        // Initialize arrays for regular circles
        for (int i = 0; i < NUM_CIRCLES; i++) {
            x[i] = (int) (Math.random() * (PANEL_WIDTH - circleDiameter)); // Random horizontal position
            y[i] = (int) (Math.random() * (PANEL_HEIGHT - circleDiameter)); // Random vertical position
            xVelocity[i] = (int) (Math.random() * 6) - 3; // Random velocity between -3 and 3
            yVelocity[i] = (int) (Math.random() * 6) - 3; // Random velocity between -3 and 3

            // Ensure circles have some movement (no zero velocity)
            if (xVelocity[i] == 0)
                xVelocity[i] = 1;
            if (yVelocity[i] == 0)
                yVelocity[i] = 1;

            displayNumber[i] = i + 1; // Display numbers 1, 2, 3...
            circleColors[i] = Color.WHITE;
        }

        // Initialize arrays for power balls (separate positions)
        for (int i = 0; i < POWER_BALLS; i++) {
            powerBallX[i] = (int) (Math.random() * (PANEL_WIDTH - circleDiameter)); // Random horizontal position
            powerBallY[i] = (int) (Math.random() * (PANEL_HEIGHT - circleDiameter)); // Random vertical position
            powerBallXVelocity[i] = (int) (Math.random() * 6) - 3; // Random velocity between -3 and 3
            powerBallYVelocity[i] = (int) (Math.random() * 6) - 3;

            // Ensure the balls keep moving
            if (powerBallXVelocity[i] == 0)
                powerBallXVelocity[i] = 1;
            if (powerBallYVelocity[i] == 0)
                powerBallYVelocity[i] = 1;

            powerBallNum[i] = i + 1; // Display numbers 1, 2, 3...
            powerBallColors[i] = Color.RED; // Set color for power balls
        }

        timer = new Timer(10, this);
        timer.start();

        // Add button in the top middle
        JButton button = new JButton("Generate Numbers!");
        int buttonWidth = button.getPreferredSize().width; // Get preferred width
        int buttonHeight = 30;
        int buttonX = (PANEL_WIDTH - buttonWidth) / 2; // Center horizontally
        int buttonY = 700; // Top of the panel
        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        button.setBackground(new Color(164, 130, 27));
        button.setBorderPainted(true); // Remove border for a cleaner look
        button.setForeground(Color.WHITE);
        this.setLayout(null); // Use absolute positioning
        this.add(button);

        // Action for clicking the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // reset the displayNumber and powerBallNum arrays
                resetNumbers();

                Random_Numbers randomNumbers = new Random_Numbers();
                number = randomNumbers.randomNumber();
                

                //debugging show the generated numbers
                // for (int i = 0; i < number.length; i++) {
                //     System.out.print(number[i] + " ");
                // }
            }
        });
    }

    public void paint(Graphics g) {
        int containerX = (PANEL_WIDTH - containerDiameter) / 2;
        int containerY = (PANEL_HEIGHT - containerDiameter) / 2;
        int circleSpacingx = (PANEL_WIDTH - 900); // Spacing between circles
        // paints background
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        // Draw the container oval in the middle of the frame (only once)
        g2d.setColor(Color.WHITE);
        g2d.drawOval(containerX, containerY, containerDiameter, containerDiameter);

        // Draw all regular circles
        for (int i = 0; i < NUM_CIRCLES; i++) {

            // Check if displayNumber[i] matches any of the 6 non-powerball numbers
            boolean isGreen = false;
            if (number != null && number.length >= 6) {
                for (int j = 0; j < number.length - 1; j++) { // last number is powerball
                    if (displayNumber[i] == number[j]) {
                        isGreen = true;
                        break;
                    }
                }
            }
            if (isGreen) {
                circleColors[i] = Color.GREEN;
            } else {
                circleColors[i] = Color.WHITE;
            }

            // Draw the image
            g2d.drawImage(circle, x[i], y[i], circleDiameter, circleDiameter, this);

            // Draw a circle outline with the circle's color
            g2d.setColor(circleColors[i]);
            g2d.drawOval(x[i], y[i], circleDiameter, circleDiameter);

            // Draw filled circle
            g2d.fillOval(x[i] + 5, y[i] + 5, circleDiameter - 10, circleDiameter - 10);

            // Draw the integer in the center of the circle
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));

            // Get font metrics to center the text
            FontMetrics fm = g2d.getFontMetrics();
            String text = String.valueOf(displayNumber[i]);

            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();

            // Calculate center position
            int textX = x[i] + (circleDiameter - textWidth) / 2;
            int textY = y[i] + (circleDiameter + textHeight) / 2;

            // Draw the regular circle text
            g2d.drawString(text, textX, textY);
        }

        // Draw all power balls (separate circles)
        for (int i = 0; i < POWER_BALLS; i++) {

            // Check if powerBallNum[i] matches the last number in the generated numbers
            boolean isPowerBall = false;
            if (number != null && number.length >= 6) {
                if (powerBallNum[i] == number[number.length - 1]) { // last number is powerball
                    isPowerBall = true;
                }
            }
            if (isPowerBall) {
                powerBallColors[i] = Color.GREEN; // Set color for matched power balls
                
            } else {
                powerBallColors[i] = Color.RED; // Default color for power balls
            }
            // Draw power ball circle outline
            g2d.setColor(powerBallColors[i]);

            g2d.drawOval(powerBallX[i], powerBallY[i], circleDiameter, circleDiameter);

            // Draw filled power ball circle
            g2d.fillOval(powerBallX[i] + 5, powerBallY[i] + 5, circleDiameter - 10, circleDiameter - 10);

            // Draw the power ball number in the center
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));

            // Get font metrics to center the text
            FontMetrics fm = g2d.getFontMetrics();
            String powerText = String.valueOf(powerBallNum[i]);
            int powerTextWidth = fm.stringWidth(powerText);
            int powerTextHeight = fm.getAscent();

            // Calculate center position
            int powerTextX = powerBallX[i] + (circleDiameter - powerTextWidth) / 2;
            int powerTextY = powerBallY[i] + (circleDiameter + powerTextHeight) / 2;

            // Draw the power ball text
            g2d.drawString(powerText, powerTextX, powerTextY);
        }

        g2d.setColor(Color.white);

        //check null

        if(number != null && number.length >=8){

             for(int i = 0; i < 8; i++) {
            // Draw the container circle
            if(i  == 7) {
                g2d.setColor(Color.RED);
                g2d.fillOval(circleSpacingx, 95, 50, 50);
                g2d.setColor(Color.white);
                g2d.setFont(new Font("Comic Sans", Font.BOLD, 20));
                StringBuilder powerBallText = new StringBuilder();
                powerBallText.append(number[7]).append(" "); // Convert to string
                if (number[i] < 10) {
                    powerBallText.insert(0, "0"); // Add leading zero for single-digit numbers
                }
                g2d.drawString(powerBallText.toString(), circleSpacingx + 15, 127); // Draw at the bottom of the circle
            } else {
                g2d.setColor(Color.WHITE);
                g2d.fillOval(circleSpacingx, 95, 50, 50);
                g2d.setColor(Color.black);
                g2d.setFont(new Font("Comic Sans", Font.BOLD, 20));
                StringBuilder powerBallText = new StringBuilder();
                powerBallText.append(number[i]).append(" "); // Convert to string
                if (number[i] < 10) {
                    powerBallText.insert(0, "0"); // Add leading zero for single-digit numbers
                }
                g2d.drawString(powerBallText.toString(), circleSpacingx + 15, 127); // Draw at the bottom of the circle
            }
            circleSpacingx += 75; // Increment spacing for next circle
        }
        }
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Update the position of all regular circles
        for (int i = 0; i < NUM_CIRCLES; i++) {
            x[i] += xVelocity[i];
            y[i] += yVelocity[i];

            // Bounce off the container circle for regular circles
            int containerCenterX = PANEL_WIDTH / 2;
            int containerCenterY = PANEL_HEIGHT / 2;
            int circleCenterX = x[i] + circleDiameter / 2;
            int circleCenterY = y[i] + circleDiameter / 2;
            double dx = circleCenterX - containerCenterX;
            double dy = circleCenterY - containerCenterY;
            double distance = Math.sqrt(dx * dx + dy * dy);
            double maxDistance = (containerDiameter - circleDiameter) / 2.0;

            if (distance > maxDistance) {
                // Move circle back to the edge
                double angle = Math.atan2(dy, dx);
                circleCenterX = (int) (containerCenterX + maxDistance * Math.cos(angle));
                circleCenterY = (int) (containerCenterY + maxDistance * Math.sin(angle));
                x[i] = circleCenterX - circleDiameter / 2;
                y[i] = circleCenterY - circleDiameter / 2;

                // Reflect velocity vector
                double dot = xVelocity[i] * dx + yVelocity[i] * dy;
                double normSq = dx * dx + dy * dy;
                double rx = xVelocity[i] - 2 * dot / normSq * dx;
                double ry = yVelocity[i] - 2 * dot / normSq * dy;
                xVelocity[i] = (int) Math.round(rx);
                yVelocity[i] = (int) Math.round(ry);

                // Ensure velocity is not zero
                if (xVelocity[i] == 0)
                    xVelocity[i] = 1;
                if (yVelocity[i] == 0)
                    yVelocity[i] = 1;
            }
        }

        // Update the position of all power balls
        for (int i = 0; i < POWER_BALLS; i++) {
            powerBallX[i] += powerBallXVelocity[i];
            powerBallY[i] += powerBallYVelocity[i];

            // Bounce off the container circle for power balls
            int containerCenterX = PANEL_WIDTH / 2;
            int containerCenterY = PANEL_HEIGHT / 2;
            int powerBallCenterX = powerBallX[i] + circleDiameter / 2;
            int powerBallCenterY = powerBallY[i] + circleDiameter / 2;
            double dx = powerBallCenterX - containerCenterX;
            double dy = powerBallCenterY - containerCenterY;
            double distance = Math.sqrt(dx * dx + dy * dy);
            double maxDistance = (containerDiameter - circleDiameter) / 2.0;

            if (distance > maxDistance) {
                // Move power ball back to the edge
                double angle = Math.atan2(dy, dx);
                powerBallCenterX = (int) (containerCenterX + maxDistance * Math.cos(angle));
                powerBallCenterY = (int) (containerCenterY + maxDistance * Math.sin(angle));
                powerBallX[i] = powerBallCenterX - circleDiameter / 2;
                powerBallY[i] = powerBallCenterY - circleDiameter / 2;

                // Reflect velocity vector
                double dot = powerBallXVelocity[i] * dx + powerBallYVelocity[i] * dy;
                double normSq = dx * dx + dy * dy;
                double rx = powerBallXVelocity[i] - 2 * dot / normSq * dx;
                double ry = powerBallYVelocity[i] - 2 * dot / normSq * dy;
                powerBallXVelocity[i] = (int) Math.round(rx);
                powerBallYVelocity[i] = (int) Math.round(ry);

                // Ensure velocity is not zero
                if (powerBallXVelocity[i] == 0)
                    powerBallXVelocity[i] = 1;
                if (powerBallYVelocity[i] == 0)
                    powerBallYVelocity[i] = 1;
            }
        }

        // Repaint the panel
        repaint();
    }

    public void resetNumbers() {
        // Reset the displayNumber and powerBallNum arrays
        for (int i = 0; i < NUM_CIRCLES; i++) {
            displayNumber[i] = i + 1; // Reset to original numbers
        }
        for (int i = 0; i < POWER_BALLS; i++) {
            powerBallNum[i] = i + 1; // Reset to original numbers
        }

        // Repaint the panel to reflect changes
        repaint();
    }
}

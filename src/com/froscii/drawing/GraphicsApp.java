package com.froscii.drawing;

import com.froscii.drawing.Parts.Line;
import com.froscii.drawing.dynamodb.Drawing;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Works independently of AWS services.
 */
public class GraphicsApp extends JFrame {
// Defining all the static variables
    private static final long serialVersionUID = 1L;
    public static final int CANVAS_WIDTH  = 1024;
    public static final int CANVAS_HEIGHT = 768;
    public static final int THICKNESS = 3;
    public static Drawing drawing;
    private DrawCanvas canvas;
    // The program enters from the main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Enter your ASCII art: ");
                Scanner scanner = new Scanner(System.in);
                String textEntry = "";
                String newLine = scanner.nextLine();
                // The width is determined by the length of the first line only.
                int width = newLine.length();
                while(!newLine.equals("")) {
                    if (newLine.length() < width) {
                        // Add enough spaces to set it to the proper width
                        newLine = newLine + (new String(new char[width-newLine.length()]).replace('\0',' '));
                    } else {
                        if (newLine.length() > width) {
                            // Shorten lines that are too long.
                            newLine = newLine.substring(0,width);
                        }
                    }
                    textEntry = textEntry + newLine;
                    newLine = scanner.nextLine();
                }
                System.out.println("Enter a drawing name: ");

                String name = new Scanner(System.in).next();
                name = name.replace(' ','_');
                drawing = new Drawing(name, textEntry,width);
                new GraphicsApp(); // this run method will create a new object and thus invoke the constructor method.
            }
        });
    }
    public GraphicsApp() {
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        Container containerPane = getContentPane();
        containerPane.add(canvas);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true); // We want this OFF in Lambda implementation

    }
    /**
     * here drawCanvas is the inner class of the Jpanel which is used for custom drawing
     */
    private class DrawCanvas extends JPanel {
        private static final long serialVersionUID = 1L;
        @Override
        public void paintComponent(Graphics graphics) {
            BufferedImage bufferedImage = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2d = bufferedImage.createGraphics();
            super.paintComponent(graphics2d);
            graphics2d.setStroke(new BasicStroke(THICKNESS, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2d.setColor(Color.BLACK);
            List<Line> lines = drawing.convertText();
            for (Line p : lines) {
                graphics2d.drawLine(p.aX(), p.aY(), p.bX(), p.bY());
            }
            RenderedImage renderedImage = bufferedImage;
            try {
                File file = new File("src/com/froscii/drawing/prints/" + drawing.getName() + ".png");
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException e) {
                System.out.println("Sorry bud. It don't like you.");
            }
            GraphicsApp.this.dispatchEvent(new WindowEvent(GraphicsApp.this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
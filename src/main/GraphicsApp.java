package main;

import javax.swing.JFrame;
import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.awt.*;
import java.awt.Graphics2D;
public class GraphicsApp extends JFrame {
// Defining all the static variables
    private static final long serialVersionUID = 1L;
    public static final int CANVAS_WIDTH  = 1000;
    public static final int CANVAS_HEIGHT = 500;
    public static final int THICKNESS = 3;
    public static Drawing drawing;
    // The program enters from the main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Enter your ASCII art: ");
                Scanner scanner = new Scanner(System.in);
                String textEntry = "";
                String newLine = scanner.nextLine();
                int width = newLine.length();
                while(!newLine.equals("")) {
                    textEntry = textEntry + newLine;
                    newLine = scanner.nextLine();
                }
                drawing = new Drawing(textEntry,width);
                new GraphicsApp(); // this run method will create a new object and thus invoke the constructor method.
            }
        });
    }
    private DrawCanvas canvas;
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
            Graphics2D graphics2d = (Graphics2D) graphics; // Cast so we can set the line width \/
            super.paintComponent(graphics2d);
            graphics2d.setStroke(new BasicStroke(THICKNESS, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            List<Line> lines = drawing.convertText();
            for (Line p: lines) {
                graphics2d.drawLine(p.aX(), p.aY(),p.bX(), p.bY());
            }
            System.out.println("** " + lines.size() + " **");
        }
    }
}
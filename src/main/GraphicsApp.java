import javax.swing.JFrame;
import javax.swing.*;
import java.util.ArrayList;
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
    //Here we are creating an instance of the drawing canvas inner class called DrawCanwas
    private DrawCanvas sampleCanvas;
    public GraphicsApp() {
        sampleCanvas = new DrawCanvas();
        sampleCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        Container containerPane = getContentPane();
        containerPane.add(sampleCanvas);
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
            super.paintComponent(graphics);
            Graphics2D graphics2d = (Graphics2D) graphics;
            graphics2d.setStroke(new BasicStroke(THICKNESS, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
            List<PolylineData> polylines = drawing.convertText();
            for (PolylineData p: polylines) {
                graphics.drawPolyline(p.getXPoints(), p.getYPoints(), p.getPointCount());
            }
        }
    }
}
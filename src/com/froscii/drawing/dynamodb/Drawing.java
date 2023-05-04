package com.froscii.drawing.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.froscii.drawing.GraphicsApp;
import com.froscii.drawing.Parts.Coordinate;
import com.froscii.drawing.Parts.Line;
import java.util.*;

/**
 * Only name and text are stored in DynamoDB
 */
@DynamoDBTable(tableName= "drawings")
public class Drawing {
    private String name;
    private String text;

    private char[][] textGrid;
    //All other variables are based on text.
    private static final int CHAR_WIDTH = 5;
    private static final int CHAR_HEIGHT = 7;
    private int offsetX;
    private int offsetY;
    private static char[] ALLOWED_CHARS;
    private static final Map<Character,List<Coordinate>> CHARS_TO_POINTS = createCharsToPoints();

    public Drawing(){}
    public Drawing(String text, int width) {
        this("",text,width);
    }
    public Drawing(String name, String text, int width) {
        this.name = name;
        this.textGrid = stringToGrid(text,width);
        this.offsetX = (GraphicsApp.CANVAS_WIDTH - (width *(CHAR_WIDTH-1)*(CHAR_WIDTH-1)))/2;
        this.offsetY = (GraphicsApp.CANVAS_HEIGHT - (this.textGrid[0].length*(CHAR_HEIGHT-1)*(CHAR_HEIGHT-1)))/2;
    }
    @DynamoDBHashKey(attributeName = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    /**
     * DDB will only take this as a String
     * @return String
     */
    @DynamoDBAttribute(attributeName = "text")
    public String getText() {
        return text;
    }
    public void setText(String string) {
        System.out.println("textString = " + string);
        this.text = string;
        this.textGrid = stringToGrid(string);
    }

    /**
     * Returns a 3-layered list of lists.
     * The first is a list of poly-lines.
     * Each poly-line is a list of x-points, y-points, and number of points.
     * Finally, the x-points and y-points are each lists of the same length.
     */
    public List<Line> convertText() {
        List<Line> lines =  new ArrayList<>();
        Map<Integer, Set<Line>> rows = new HashMap<>(); //Stored by left-most point
        Coordinate coordinate;
        Coordinate nextCoordinate;
        int x1; int y1; int x2; int y2;
        for (int y = 0; y < textGrid[0].length; y++){
            for (int x = 0; x < textGrid.length; x++) {
                // I don't think checking for a space here is a time saver anymore.
                // Get the points from the character and where they should go
                List<Coordinate> charPoints = CHARS_TO_POINTS.get(textGrid[x][y]);
                if (charPoints.size() == 1) {
                    // Create two of the same point
                    coordinate = new Coordinate(calcX(charPoints.get(0).getX(),x),
                            calcY(charPoints.get(0).getY(),y));
                    lines.add(new Line (coordinate, coordinate));
                } else {
                    // Add two conjoined points to the list
                    for (int i = 0; i < charPoints.size()-1; i++) {
                        x1 = calcX(charPoints.get(i).getX(),x);
                        y1 = calcY(charPoints.get(i).getY(),y);
                        x2 = calcX(charPoints.get(i+1).getX(),x);
                        y2 = calcY(charPoints.get(i+1).getY(),y);
                        coordinate = new Coordinate(x1,y1);
                        nextCoordinate = new Coordinate(x2,y2);
                        Line line;
                        // Point a will always be to the left of point b.
                        if (x1 > x2) {
                            line = new Line(nextCoordinate,coordinate);
                        } else {
                            line = new Line(coordinate, nextCoordinate);
                        }
                        if (y1 == y2) { // horizontal line
                            if (!rows.containsKey(y1)) {
                                rows.put(y1, new TreeSet<>());
                            }
                            rows.get(y1).add(line);
                        } else {
                            lines.add(line);
                        }
                    }
                }
            }
        }
        // Combine lines in the row hashmap, then add them to the main lines list
        int bridge = 12;
        // This is only horizontal lines right now.
        for (Integer y: rows.keySet()) {
            Line previousLine = null;
            for (Line line: rows.get(y)) {
                if (previousLine == null) {
                    previousLine = line;
                }
                // If these two lines overlap or nearly overlap, condense them.
                if (Math.abs(previousLine.bX() - line.aX()) < bridge) {
                    // If one is actually within the other, just forget it.
                    if (line.bX() > previousLine.bX()) {
                        previousLine.setB(line.getB());
                    }
                } else {
                    lines.add(previousLine);
                    previousLine = line;
                }
            }
            if (previousLine != null) {
                lines.add(previousLine);
            }
        }
        return lines;
    }
    @Override
    public int hashCode() {
        return hashCode(this.name, this.text);
    }

    // Only static methods below

    /**
     * A Drawing's hashCode/id is made from the name and the textString.
     */
    public static int hashCode(String name, String text) {
        return Objects.hash(name,text.length(),text.charAt(text.length()/2));
    }
    /**
     * Used to create a map of accepted characters and their respective line endpoints.
     * @return Map of Characters to Line Endpoints
     */
    private static Map<Character, List<Coordinate>> createCharsToPoints() {
        // Set up the characters and their line endpoint values
        // /\|-_.,'`"<>v^L7V[]NzZMW
        ALLOWED_CHARS = new char[]
               {'/',                    '\\',                '|',                   '-',             '_',
                '.',                    ',',                 '\'',                  '`',             '"', //acts as over-line
                '<',                    '>',                 'v',                   '^',             'n',
                'L',                    '7',                 'V',                   '[',             'u',
                ']',                    'N',                 'z',                   'Z',
                'M',                    'W',                 'm',
                '#',                    'o',                                        '*',
                '+',                    '{',                                        '}',
                '(',                    ')',                                        ' ',             'I',
                'O',                                         'H',
                '$',                                         '!',                   'i', //these two purposely do not have dots
                'X',                    'Y',                 'A',                   't',
                'T',                    '0'};
        // Draws from the top-leftmost endpoint first.
        // Each character can be drawn in a 5x7 grid
        int[][] CHAR_POINTS = new int[][]
               {new int[]{4,4,17,17,30,30},new int[]{0,0,17,17,34,34},new int[]{2,2,17,17,32,32},new int[]{15,19},new int[]{30,34},
                new int[]{27,21,23,27}, new int[]{28,32},    new int[]{2,2,7},      new int[]{2,8,6,2},new int[]{0,4},
                new int[]{4,15,34},     new int[]{0,19,30},  new int[]{15,32,19},   new int[]{15,2,19},new int[]{31,16,18,33},
                new int[]{0,30,34},     new int[]{0,4,34},   new int[]{0,0,32,4,4}, new int[]{3,1,31,33},new int[]{1,16,18,3},
                new int[]{1,3,33,31},   new int[]{30,0,34,4},new int[]{16,18,31,33},new int[]{0,4,30,34},
                new int[]{30,0,12,4,34},new int[]{0,30,17,34,4},new int[]{30,20,16,27,18,24,34},
                new int[]{5,9,29,25,5}, new int[]{11,13,19,24,28,26,20,15,11},      new int[]{2,8,12,6,2},
                new int[]{7,27,17,15,19},new int[]{3,3,2,6,11,15,15,21,26,32,33,33},new int[]{1,1,2,8,13,19,19,23,28,32,31,31},
                new int[]{3,3,6,15,15,26,33,33},new int[]{1,1,8,19,19,28,31,31},    new int[]{},new int[]{0,4,2,32,30,34},
                new int[]{1,3,14,24,33,31,20,10,1},          new int[]{0,30,15,19,4,34},
                new int[]{10,25,11,26,12,27,13,28,14,29},    new int[]{2,2,17,17},new int[]{17,17,32,32},
                new int[]{0,34,17,30,4},new int[]{0,0,17,4,4},new int[]{30,30,17,34,34},new int[]{2,2,32,32,17,15,19},
                new int[]{0,4,2,32},    new int[]{1,3,33,31,1}};

        //  0  1  2  3  4     ..........
        //  5  6  7  8  9     ..##..##..      o----o
        //  10 11 12 13 14    ##......##    o'      'o
        //  15 16 17 18 19 >> .......... >> |        |
        //  20 21 22 23 24    ##......##    o        o
        //  25 26 27 28 29    ..##..##..     'o----o'
        //  30 31 32 33 34    ..........
        // Map the characters to their line-point-coordinate values

        Map<Character, List<Coordinate>> charsToPoints = new HashMap<>();
        // FIXME: Does not allow multi-stroke characters such as ';'
        for (int i = 0; i < ALLOWED_CHARS.length; i++) {
            List<Coordinate> charPoints = new ArrayList<>();
            for (Integer v : CHAR_POINTS[i]) {
                // Convert the line-point values to coordinate values
                int x = v % CHAR_WIDTH;
                int y = (v - x) / CHAR_WIDTH;
                charPoints.add(new Coordinate(x, y));
            }
            charsToPoints.put(ALLOWED_CHARS[i], charPoints);
        }
        return charsToPoints;
    }
    public int calcX(int localX, int globalX) {
        return (localX + (globalX * (CHAR_WIDTH-1))) * (CHAR_WIDTH-1) + offsetX;
    }
    public int calcY(int localY, int globalY) {
        return (localY + (globalY * (CHAR_HEIGHT-1))) * (CHAR_HEIGHT-1)  + offsetY;
    }
    /**
     * Checks if two points connect
     * @param a : first point value
     * @param b : second point value
     * @return if they should combine
     */
    public static boolean pointsConnect(int a, int b) {
        // More often than not, this will be false, so check that first
        int aCol = a%CHAR_WIDTH;
        int aRow = (a - aCol)/CHAR_WIDTH;
        int bCol = b%CHAR_WIDTH;
        int bRow = (b - bCol)/CHAR_WIDTH;
        if (aCol != bCol && aRow != bRow) {
            return false;
        } else {
            // opposite ends of same column
            if (b - a == (CHAR_WIDTH*(CHAR_HEIGHT-1))) {
                return true;
            } else return b - a == CHAR_WIDTH - 1;
        }
    }
    /**
     * Convert a text string into a 2-Dimensional grid of characters.
     * All characters not found in the ALLOWED_CHARS will be changed
     * to $, or "unknown".
     * @param s The text string
     * @param width The desired character width
     * @return char[][]
     */
    public static char[][] stringToGrid(String s, int width) {
        char[][] grid = new char[width][s.length()/width];
        int x = 0;
        int y = 0;
        for (char c: s.toCharArray()) {
            grid[x][y] = (filterChar(c));
            x++;
            if (x == width) {
                x = 0;
                y++;
            }
        }
        return grid;
    }

    /**
     * Determines the width on its own.
     * @param string Text string that has '/n's if there are multiple rows
     * @return a 2D array of characters with a consistent size
     */
    public static char[][] stringToGrid(String string) {
        //Divvy into lines
        List<String> lines = new ArrayList<>();
        int maxWidth = 0;
        if (string == null) {
            string = "UnforeseeableError";
        }
        while (string.contains("\n")) {
            String newString = string.substring(0, string.indexOf('\n'));
            lines.add(newString);
            //Keep track of the longest line length
            maxWidth = Math.max(maxWidth, newString.length());
            string = string.substring(string.indexOf('\n') + 1);
        }
        lines.add(string);
        maxWidth = Math.max(maxWidth, string.length());
        //Convert each line to an array of chars
        char[][] charGrid = new char[lines.size()][maxWidth];
        for (int i = 0; i < lines.size(); i ++) {
            int c = 0;
            while (c < lines.get(i).length()) {
                charGrid[i][c] = filterChar(lines.get(i).charAt(c));
                c++;
            }
            while (c < maxWidth) {
                charGrid[i][c] = ' ';
                c++;
            }
        }
        return charGrid;
    }

    /**
     * Returns '$' if c is not one of the ALLOWED_CHARS.
     * @param c The character to check for in the index.
     * @return c or '$'
     */
    private static char filterChar(char c) {
        for (char chr: ALLOWED_CHARS) {
            if (chr == c) {
                return c;
            }
        }
        return '$';
    }
}
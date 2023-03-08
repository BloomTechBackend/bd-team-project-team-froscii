import java.util.*;

public class Drawing {
    private static final int CHAR_WIDTH = 5;
    private static final int CHAR_HEIGHT = 7;
    private static final Map<Character,List<Coordinate>> CHARS_TO_POINTS = createCharsToPoints();

    private String name;
    private int id;
    private char[][] text;
    // Width is stored here so that we don't have
    //  to remove newline escape codes from the text.
    private int width;

    public Drawing(String name, String text, int width) {
        this.name = name;
        this.text = stringToGrid(text,width);
        this.id = this.hashCode();
        this.width = width;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Drawing(String text, int width) {
        this.text = stringToGrid(text,width);
        this.id = this.hashCode();
        this.name = "";
        this.width = width;
    }
    /**
     * Returns a 3-layered list of lists.
     * The first is a list of poly-lines.
     * Each poly-line is a list of x-points, y-points, and number of points.
     * Finally, the x-points and y-points are each lists of the same length.
     */
    public List<PolylineData> convertText() {
        List<PolylineData> polylines =  new ArrayList<>();
        List<Integer> xPointsList = new ArrayList<>();
        List<Integer> yPointsList = new ArrayList<>();
        int points = 0;
        for (int y = 0; y < text[0].length; y++){
            for (int x = 0; x < text.length; x++) {
                if (text[x][y] != ' ') {
                    List<Coordinate> charPointCoords = CHARS_TO_POINTS.get(text[x][y]);
                    for (Coordinate coord: charPointCoords) {
                        xPointsList.add((int) ((coord.getX()) + (x * CHAR_WIDTH)) * CHAR_WIDTH);
                        yPointsList.add((coord.getY() + (y * CHAR_HEIGHT)) * CHAR_HEIGHT);
                        points++;
                    }
                }
            }
        }
        int[] xPointsArray = new int[points];
        int[] yPointsArray = new int[points];
        for (int i = 0; i < points; i++) {
            xPointsArray[i] = xPointsList.get(i);
            yPointsArray[i] = yPointsList.get(i);
        }
        PolylineData polylineData = new PolylineData(xPointsArray,yPointsArray,points);
        polylines.add(polylineData);
        return polylines;
    }
    public static char[][] stringToGrid(String s, int width) {
        char[][] grid = new char[width][s.length()/width];
        int x = 0;
        int y = 0;
        for (char c: s.toCharArray()) {
            grid[x][y] = c;
            x++;
            if (x == width) {
                x = 0;
                y++;
            }
        }
        return grid;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name,text[0][0]);
    }

    /**
     * Used to create a map of accepted characters and their respective line endpoints.
     * @return Map of Characters to Line Endpoints
     */
    private static Map<Character, List<Coordinate>> createCharsToPoints() {
        // Set up the characters and their line endpoint values
        char[] ALLOWED_CHARS = new char[]
               {'/',                    '\\',                '|',                   '-',             '_',
                '.',                    ',',                 '\'',                  '`',             '"',
                '<',                    '>',                 'v',                   '^',
                'L',                    '7',                 'V',                   '[',
                ']',                    'N',                 'z',                   'Z',
                'M',                    'W'};
        // Draws from the left-topmost endpoint first.
        // Each character can be drawn in a 5x7 grid
        int[][] CHAR_POINTS = new int[][]
               {new int[]{4,30},        new int[]{0,34},     new int[]{2,32},       new int[]{15,19},new int[]{30,34},
                new int[]{32},          new int[]{28,32},    new int[]{2,7},        new int[]{2},    new int[]{1,6,3,8},
                new int[]{9,15,29},     new int[]{5,19,25},  new int[]{15,32,19},   new int[]{25,2,19},
                new int[]{0,30,34},     new int[]{0,4,34},   new int[]{0,32,4},     new int[]{3,1,31,33},
                new int[]{1,3,33,31},   new int[]{30,0,34,4},new int[]{16,18,31,33},new int[]{0,4,30,34},
                new int[]{30,0,12,4,34},new int[]{0,30,17,34,4}};
        // Map the characters to their line-point-coordinate values
        Map<Character, List<Coordinate>> charsToPoints = new HashMap<>();
        for (int i = 0; i < ALLOWED_CHARS.length; i++) {
            List<Coordinate> charPoints = new ArrayList<>();
            for (Integer v: CHAR_POINTS[i]) {
                // Convert the line-point values to coordinate values
                int x = v%CHAR_WIDTH;
                int y = (v - x)/CHAR_WIDTH;
                charPoints.add(new Coordinate(x, y));
            }
            charsToPoints.put(ALLOWED_CHARS[i], charPoints);
        }
        return charsToPoints;
    }
    //                   _1_2_3_4_5_
    // 0  1  2  3  4 // 1|..######..//  o----o  //
    // 5  6  7  8  9 // 2|......##..//       |  //
    // 10 11 12 13 14// 3|......##..//       |  //
    // 15 16 17 18 19// 4|......##..>>       |  //
    // 20 21 22 23 24// 5|......##..//       |  //
    // 25 26 27 28 29// 6|......##..//       |  //
    // 30 31 32 33 34// 7|..######..//  o----o  //
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
            } else if (b - a == CHAR_WIDTH - 1) {
                return true;
            } else {
                return false;
            }
        }
    }
}
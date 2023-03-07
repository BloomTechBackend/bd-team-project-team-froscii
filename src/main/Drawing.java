import java.util.List;
import java.util.Objects;

public class Drawing {
    private static char[] ALLOWED_CHARACTERS = new char[]
            {'/',                    '\\',                '|',                   '-',             '_',
             '.',                    ',',                 '\'',                  '`',             '"',
             '<',                    '>',                 'v',                   '^',
             'L',                    '7',                 'V',                   '[',
             ']',                    'N',                 'z',                   'Z',
             'M',                    'W'};
    private static int[][] CHARACTER_POINTS = new int[][]
            {new int[]{4,30},        new int[]{0,34},     new int[]{2,32},       new int[]{15,19},new int[]{30,34},
             new int[]{32},          new int[]{28,32},    new int[]{2,7},        new int[]{2},    new int[]{1,6,3,8},
             new int[]{9,15,29},     new int[]{5,19,25},  new int[]{15,32,19},   new int[]{25,2,19},
             new int[]{0,30,34},     new int[]{0,4,34},   new int[]{0,32,4},     new int[]{3,1,31,33},
             new int[]{1,3,33,31},   new int[]{30,0,34,4},new int[]{16,18,31,33},new int[]{0,4,30,34},
             new int[]{30,0,12,4,34},new int[]{0,30,17,34,4}};
    // Draws from the left-topmost endpoint first.
    // Each character can be drawn in a 5x7 grid
    //                   _1_2_3_4_5_
    // 0  1  2  3  4 // 1|..######..//  o----o  //
    // 5  6  7  8  9 // 2|......##..//       |  //
    // 10 11 12 13 14// 3|......##..//       |  //
    // 15 16 17 18 19// 4|......##..>>       |  //
    // 20 21 22 23 24// 5|......##..//       |  //
    // 25 26 27 28 29// 6|......##..//       |  //
    // 30 31 32 33 34// 7|..######..//  o----o  //
    private String name;
    private int id;
    private String text;
    // Width is stored here so that we don't have
    //  to remove newline escape codes from the text.
    private int width;

    public Drawing(String name, String text, int width) {
        this.name = name;
        this.text = text;
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
        this.text = text;
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

        return null;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name,text.substring(text.length()-1));
    }
}
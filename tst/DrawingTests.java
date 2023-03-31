import com.froscii.drawing.Parts.Line;
import com.froscii.drawing.dynamodb.Drawing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrawingTests {
    @Nested
    public class CharPointConnectionTests {
        @Test
        public void connectTopCorners() {
            assertTrue(Drawing.pointsConnect(0, 4));
        }
    }

    @Test
    public void testOnePolyline() {
        String input = "|||";
        List<Line> result = new Drawing(input,3).convertText();
        assertTrue((result.size() == 1), "Only one polyline should have been made");
    }
    @Test
    public void stringToCharGrid() {
        String input = "[]\ni";
        char[][] expected = new char[][]{new char[]{'[',']'},new char[]{'[',']'}};
        char[][] result = Drawing.stringToCharGrid(input);
        assertTrue(expected.length == result.length, "They should be same height");
        assertTrue(expected[0].length == result[0].length, "They should be same width");
        assertTrue(expected[1].length == result[1].length, "They should be same width despite initial difference.");
    }
}
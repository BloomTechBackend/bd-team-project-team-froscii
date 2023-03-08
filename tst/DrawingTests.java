import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        List<PolylineData> result = new Drawing(input,3).convertText();
        assertTrue("Only one polyline should have been made", (result.size() == 1));
    }
    @Test
    public void testFourPoints() {
        //Make sure that a 4-point line is made from ||
        String input = "||";
        List<PolylineData> result = new Drawing(input,2).convertText();
        assertTrue("There should only be 4 points to this polyline", (result.get(0).getPointCount()) == 4);
    }
    @Test
    public void testThreePoints() {
        //Make sure that 3 points are made from \/
        String input = "\\/";
        List<PolylineData> result = new Drawing(input,2).convertText();
        assertTrue("There should only be 4 points to this polyline", (result.get(0).getPointCount()) == 3);
    }
    @Test
    public void testTwoPolylines() {
        //Make sure that 2 polylines are made from \/  /\
        String input = "\\/  /\\";
        List<PolylineData> result = new Drawing(input,6).convertText();
        assertTrue("Two polylines should have been made", (result.size() == 2));
    }
    @Test
    public void testTwoHeightPolyline() {
        //Make sure that 1 polyline is made from \/  /\
        String input = "\\/ /\\ ";
        List<PolylineData> result = new Drawing(input, 3).convertText();
        assertTrue("One polyline should have been made", (result.size() == 1));
    }
}
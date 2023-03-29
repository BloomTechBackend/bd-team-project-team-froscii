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
}
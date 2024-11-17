package test.shapes;

import main.shapes.Circle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CircleTest {

    @Test
    public void testCalcArea() {
        Circle circle = new Circle("Yellow", 10);
        double expectedArea = Math.PI * 100;
        assertEquals(expectedArea, circle.calcArea(), 0.0000000000000001);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Circle("Yellow", -10);
        });
        assertEquals("Radius of circle must be a positive number.", exception.getMessage());
    }

    @Test
    public void testToString() {
        Circle circle = new Circle("Yellow", 10);
        String expectedString = "CIRCLE -- Radius: 10.0, Shape color: Yellow, Area: " + (Math.PI * 100);
        assertEquals(expectedString, circle.toString());
    }
}

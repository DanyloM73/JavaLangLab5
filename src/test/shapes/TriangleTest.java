package test.shapes;

import main.shapes.Triangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TriangleTest {

    @Test
    public void testCalcArea() {
        Triangle triangle = new Triangle("Green", 10, 20);
        double expectedArea = 100.0;
        assertEquals(expectedArea, triangle.calcArea(), 0.0000000000000001);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Triangle("Green", 10, -20);
        });
        assertEquals("Base and height of triangle must be positive numbers.", exception.getMessage());
    }

    @Test
    public void testToString() {
        Triangle triangle = new Triangle("Green", 10, 20);
        String expectedString = "TRIANGLE -- Base: 10.0, Height: 20.0, Shape color: Green, Area: 100.0";
        assertEquals(expectedString, triangle.toString());
    }
}
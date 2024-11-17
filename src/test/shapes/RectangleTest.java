package test.shapes;

import main.shapes.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RectangleTest {

    @Test
    public void testCalcArea() {
        Rectangle rectangle = new Rectangle("Red", 20, 10);
        double expectedArea = 200.0;
        assertEquals(expectedArea, rectangle.calcArea(), 0.0000000000000001);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle("Red", 20, -10);
        });
        assertEquals("Width and height of rectangle must be positive numbers.", exception.getMessage());
    }

    @Test
    public void testToString() {
        Rectangle rectangle = new Rectangle("Red", 20, 10);
        String expectedString = "RECTANGLE -- Width: 20.0, Height: 10.0, Shape color: Red, Area: 200.0";
        assertEquals(expectedString, rectangle.toString());
    }
}
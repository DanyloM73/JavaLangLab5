package test.views;

import main.shapes.Circle;
import main.shapes.Rectangle;
import main.shapes.Shape;
import main.shapes.Triangle;
import main.views.ShapeView;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ShapeViewTest {

    @Test
    public void testDisplayShapes() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Shape[] shapes = {
                new Rectangle("Red", 5, 7),
                new Circle("Blue", 3),
                new Triangle("Green", 6, 8)
        };

        ShapeView view = new ShapeView();
        view.displayShapes(shapes);

        String expectedOutput = "RECTANGLE -- Width: 5.0, Height: 7.0, Shape color: Red, Area: 35.0\n" +
                "CIRCLE -- Radius: 3.0, Shape color: Blue, Area: " + (Math.PI * 9) + "\n" +
                "TRIANGLE -- Base: 6.0, Height: 8.0, Shape color: Green, Area: 24.0\n";

        String actualOutput = outContent.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput, actualOutput);

        System.setOut(originalOut);
    }

    @Test
    public void testDisplayTotalArea() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        ShapeView view = new ShapeView();

        double totalArea = 120.77;
        view.displayTotalArea(totalArea);

        String expectedOutput = "\nTotal area of all shapes: 120.77\n";

        String actualOutput = outContent.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput, actualOutput);

        System.setOut(originalOut);
    }

    @Test
    public void testDisplayMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        ShapeView view = new ShapeView();
        view.displayMessage("Some text...");

        String actualOutput = outContent.toString().replace("\r\n", "\n");

        assertEquals("Some text...\n", actualOutput);

        System.setOut(originalOut);
    }

    @Test
    public void testDisplayAreaByType() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        ShapeView view = new ShapeView();

        String shapeType = "Rectangle";
        double area = 75.0;
        view.displayAreaByType(shapeType, area);

        String expectedOutput = "\nTotal area of rectangles: 75.0\n";

        String actualOutput = outContent.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput, actualOutput);

        System.setOut(originalOut);
    }
}

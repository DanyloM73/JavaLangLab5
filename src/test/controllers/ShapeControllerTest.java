package test.controllers;

import main.controllers.ShapeController;
import main.shapes.Circle;
import main.shapes.Rectangle;
import main.shapes.Shape;
import main.shapes.Triangle;
import main.views.ShapeView;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShapeControllerTest {

    @Test
    public void testCalculateTotalArea() {
        ShapeView view = new ShapeView();
        Shape[] shapes = {
                new Rectangle("Red", 5, 10),
                new Circle("Blue", 3),
                new Triangle("Green", 6, 8)
        };
        ShapeController controller = new ShapeController(shapes, view);

        double expectedTotalArea = 50.0 + (Math.PI * 9) + 24.0;
        assertEquals(expectedTotalArea, controller.calculateTotalArea(), 0.0000000000000001);
    }

    @Test
    public void testCalculateTotalAreaByType() {
        ShapeView view = new ShapeView();
        Shape[] shapes = {
                new Rectangle("Red", 5, 10),
                new Circle("Blue", 3),
                new Triangle("Green", 6, 8),
                new Rectangle("Yellow", 7, 7)
        };
        ShapeController controller = new ShapeController(shapes, view);

        double expectedRectangleArea = 50.0 + 49.0;
        assertEquals(expectedRectangleArea, controller.calculateTotalAreaByType(Rectangle.class), 0.0000000000000001);
    }

    @Test
    public void testSortByArea() {
        ShapeView view = new ShapeView();
        Shape[] shapes = {
                new Rectangle("Red", 5, 10),
                new Circle("Blue", 2),
                new Triangle("Green", 6, 8)
        };
        ShapeController controller = new ShapeController(shapes, view);

        controller.sortByArea();

        assertTrue(shapes[0] instanceof Circle);
        assertTrue(shapes[1] instanceof Triangle);
        assertTrue(shapes[2] instanceof Rectangle);
    }

    @Test
    public void testSortByColor() {
        ShapeView view = new ShapeView();
        Shape[] shapes = {
                new Rectangle("Red", 5, 10),
                new Circle("Blue", 12),
                new Triangle("Green", 6, 8)
        };
        ShapeController controller = new ShapeController(shapes, view);

        controller.sortByColor();

        assertEquals("Blue", shapes[0].shapeColor);
        assertEquals("Green", shapes[1].shapeColor);
        assertEquals("Red", shapes[2].shapeColor);
    }
}

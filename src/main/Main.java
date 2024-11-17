package main;

import main.controllers.ShapeController;
import main.shapes.Circle;
import main.shapes.Rectangle;
import main.shapes.Shape;
import main.shapes.Triangle;
import main.utils.Color;
import main.views.ShapeView;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shape[] shapes = new Shape[10];
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(shapes, view);

        controller.loadShapesFromFile(scanner, Main::generateRandomShapes);

        controller.displayShapes();

        controller.calculateTotalArea();

        controller.calculateTotalAreaByType(Rectangle.class);

        controller.calculateTotalAreaByType(Triangle.class);

        controller.calculateTotalAreaByType(Circle.class);

        controller.sortByArea();

        controller.sortByColor();

        controller.saveShapesToFile(scanner);
    }

    private static Shape[] generateRandomShapes() {
        Random random = new Random();
        Shape[] shapes = new Shape[10];

        for (int i = 0; i < shapes.length; i++) {
            int shapeType = random.nextInt(3);
            Color color = Color.values()[random.nextInt(Color.values().length)];

            switch (shapeType) {
                case 0 -> {
                    double width = random.nextDouble(30);
                    double height = random.nextDouble(30);
                    shapes[i] = new Rectangle(color.getName(), width, height);
                }
                case 1 -> {
                    double radius = random.nextDouble(30);
                    shapes[i] = new Circle(color.getName(), radius);
                }
                case 2 -> {
                    double base = random.nextDouble(30);
                    double triangleHeight = random.nextDouble(30);
                    shapes[i] = new Triangle(color.getName(), base, triangleHeight);
                }
            }
        }

        return shapes;
    }
}

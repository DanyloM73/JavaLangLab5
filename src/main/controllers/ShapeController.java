package main.controllers;

import main.shapes.*;
import main.utils.FileUtils;
import main.views.ShapeView;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class ShapeController {
    private Shape[] shapes;
    private final ShapeView view;

    public ShapeController(Shape[] shapes, ShapeView view) {
        this.shapes = shapes;
        this.view = view;
    }

    public void displayShapes() {
        view.displayMessage(view.ALL_SHAPES_TITLE);
        view.displayShapes(shapes);
    }

    public double calculateTotalArea() {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.calcArea();
        }
        view.displayTotalArea(totalArea);
        return totalArea;
    }

    public double calculateTotalAreaByType(Class<? extends Shape> shapeType) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            if (shapeType.isInstance(shape)) {
                totalArea += shape.calcArea();
            }
        }
        view.displayAreaByType(shapeType.getSimpleName(), totalArea);
        return totalArea;
    }

    public void sortByArea() {
        Arrays.sort(shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return Double.compare(s1.calcArea(), s2.calcArea());
            }
        });
        view.displayMessage(view.AREA_SORTED_SHAPES_TITLE);
        view.displayShapes(shapes);
    }

    public void sortByColor() {
        Arrays.sort(shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return s1.shapeColor.compareTo(s2.shapeColor);
            }
        });
        view.displayMessage(view.COLOR_SORTED_SHAPES_TITLE);
        view.displayShapes(shapes);
    }

    public void saveShapesToFile(Scanner scanner) {
        view.displayMessage(view.SAVE_TO_FILE_MESSAGE);
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            view.displayMessage(view.ENTER_FILE_MESSAGE);
            String fileName = scanner.nextLine();

            try {
                FileUtils.writeShapesToFile(fileName, shapes);
                view.displayMessage(view.SUCCESS_SAVING_TO_FILE_MESSAGE + fileName);
            } catch (IOException e) {
                view.displayMessage(view.ERROR_SAVING_TO_FILE_MESSAGE + e.getMessage());
                System.exit(1);
            }
        } else {
            view.displayMessage(view.NOT_SAVED_MESSAGE);
        }
    }

    public void loadShapesFromFile(Scanner scanner, Supplier<Shape[]> shapesGenerator) {
        view.displayMessage(view.READ_FROM_FILE_MESSAGE);
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            view.displayMessage(view.ENTER_FILE_MESSAGE);
            String fileName = scanner.nextLine();

            try {
                shapes = FileUtils.readShapesFromFile(fileName);
                view.displayMessage(view.SUCCESS_READING_FROM_FILE_MESSAGE);
            } catch (IOException | ClassNotFoundException e) {
                view.displayMessage(view.ERROR_READING_FROM_FILE_MESSAGE + e.getMessage());
                System.exit(1);
            }
        } else {
            shapes = shapesGenerator.get();
        }
    }
}

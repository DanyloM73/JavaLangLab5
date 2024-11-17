package main.views;

import main.shapes.Shape;

public class ShapeView {
    public final String ALL_SHAPES_TITLE = "\nAll shapes:\n";
    public final String AREA_SORTED_SHAPES_TITLE = "\nShapes sorted by area:\n";
    public final String COLOR_SORTED_SHAPES_TITLE = "\nShapes sorted by color:\n";
    public final String SAVE_TO_FILE_MESSAGE = "\nWould you like to save the shapes to a file? (yes/no): ";
    public final String ENTER_FILE_MESSAGE = "\nEnter file path (including file name): ";
    public final String SUCCESS_SAVING_TO_FILE_MESSAGE = "\nShapes were successfully saved to: ";
    public final String ERROR_SAVING_TO_FILE_MESSAGE = "\nError saving shapes to file: ";
    public final String NOT_SAVED_MESSAGE = "\nShapes were not saved.";
    public final String READ_FROM_FILE_MESSAGE = "\nWould you like to read the shapes from a file? (yes/no): ";
    public final String SUCCESS_READING_FROM_FILE_MESSAGE = "\nShapes were successfully read from file.";
    public final String ERROR_READING_FROM_FILE_MESSAGE = "\nError reading shapes from file: ";
    public final String UNKNOWN_SHAPE_TYPE_MESSAGE = "\nUnknown shape type: ";
    public final String ERROR_PARSING_LINE_MESSAGE = "\nError parsing line '";

    public void displayShapes(Shape[] shapes) {
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void displayTotalArea(double totalArea) {
        System.out.println("\nTotal area of all shapes: " + totalArea);
    }

    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displayAreaByType(String shapeType, double area) {
        System.out.println("\nTotal area of " + shapeType.toLowerCase() + "s: " + area);
    }
}

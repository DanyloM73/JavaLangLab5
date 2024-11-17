package main.shapes;

import java.util.Objects;

public class Triangle extends Shape {
    private final double base;
    private final double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height of triangle must be positive numbers.");
        }
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return (base * height) / 2;
    }

    @Override
    public String toString() {
        return "TRIANGLE -- Base: " + this.base + ", Height: " + this.height + ", " + super.toString();
    }

    @Override
    public void draw() {
        System.out.println("Drawing a triangle...");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Triangle triangle = (Triangle) obj;
        return Double.compare(triangle.base, base) == 0 &&
                Double.compare(triangle.height, height) == 0 &&
                shapeColor.equals(triangle.shapeColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, height, shapeColor);
    }
}


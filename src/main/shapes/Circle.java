package main.shapes;

import java.util.Objects;

public class Circle extends Shape {
    private final double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius of circle must be a positive number.");
        }
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "CIRCLE -- Radius: " + this.radius + ", " + super.toString();
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle...");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Circle circle = (Circle) obj;
        return Double.compare(circle.radius, radius) == 0 &&
                shapeColor.equals(circle.shapeColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, shapeColor);
    }
}

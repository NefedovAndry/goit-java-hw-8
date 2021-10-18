package module_7;

public class Main {
    public static void main(String[] args) {
        Shape[] shapeArray = new Shape[]{new Rectangle(), new Quad(), new Rhomb(), new Ellipse(), new Circle(),
                new Line(), new Point()};
        for (Shape shape : shapeArray) {
            new ShapeName(shape);
        }
    }
}

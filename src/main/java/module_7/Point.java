package module_7;

public class Point extends Shape implements Drawable{
    private int x;
    private int y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public String getName() {
        return "Point";
    }

    @Override
    public void draw() {
    }
}

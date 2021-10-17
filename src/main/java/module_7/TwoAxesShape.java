package module_7;

public abstract class TwoAxesShape extends Shape implements Drawable {
    private Point startPoint;
    private int length;
    private int width;
    private int angle;

    @Override
    public abstract String getName();

    public TwoAxesShape() {
        this.startPoint = new Point();
        this.length = 0;
        this.width = 0;
        this.angle = 0;
    }

    @Override
    public abstract void draw();
}

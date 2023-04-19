import java.awt.*;
import java.util.ArrayList;

public interface ExtendShape extends Shape {
    public abstract Point nearPoint(Point p);
    public abstract ArrayList<Point> getPoints();
    public abstract int getOX();
    public abstract int getOY();
    public abstract int getW();
    public abstract int getH();
    public abstract int getComposite();
    public abstract void setComposite(int composite);
    public abstract void deComposite();
    public abstract void move(Point p);
}

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class UseCase extends Ellipse2D.Double implements ExtendShape{
    public UseCase(double x, double y,double w,double h){
        super(x,y,w,h);
        Points.add(new Point((int) (x+w/2), (int) y));
        Points.add(new Point((int) (x+w/2), (int) (y+h)));
        Points.add(new Point((int) x, (int) (y+h/2)));
        Points.add(new Point((int) (x+w), (int) (y+h/2)));
        setComposite(-1);
    }
    @Override
    public Point nearPoint(Point p){
        double minDistance=99999;
        Point returnPoint = new Point();
        for(int i=0;i<Points.size();i++){
            if(p.distance(Points.get(i))<minDistance){
                minDistance=p.distance(Points.get(i));
                returnPoint = Points.get(i);
            }
        }
        return returnPoint;
    }

    public void move(Point p) {
        double oldX = this.x;
        double oldY = this.y;
        this.x = p.x;
        this.y = p.y;
        for(int i=0;i<Points.size();i++){
            Points.get(i).translate((int) (this.x-oldX), (int) (this.y-oldY));
        }
    }

    @Override
    public ArrayList<Point> getPoints() {
        return Points;
    }

    @Override
    public int getH() {
        return (int) this.height;
    }

    @Override
    public int getComposite() {
        return this.composite.get(this.composite.size()-1);
    }

    @Override
    public void setComposite(int composite) {
        this.composite.add(composite);
    }

    public void deComposite(){
        this.composite.remove(this.composite.size()-1);
    }
    @Override
    public int getW() {
        return (int) this.width;
    }
    @Override
    public int getOX() {
        return (int) this.x;
    }
    @Override
    public int getOY() {
        return (int) this.y;
    }

    private ArrayList<Point> Points = new ArrayList();
    private ArrayList<Integer> composite = new ArrayList();
}
import java.awt.*;
import java.util.ArrayList;

public class NewClass extends Rectangle implements ExtendShape{
    public NewClass(int x,int y, int w, int h){
        super(x,y,w,h);
        Points.add(new Point((int) (x+w/2), (int) y));
        Points.add(new Point((int) (x+w/2), (int) (y+h)));
        Points.add(new Point((int) x, (int) (y+h/2)));
        Points.add(new Point((int) (x+w), (int) (y+h/2)));
        setComposite(-1);
    }
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

    @Override
    public ArrayList<Point> getPoints() {
        return Points;
    }
    @Override
    public int getH() {
        return (int) this.height;
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

    @Override
    public void move(Point p) {
        int oldX = this.x;
        int oldY = this.y;
        this.x = p.x;
        this.y = p.y;
        for(int i=0;i<Points.size();i++){
            Points.get(i).translate(this.x-oldX, this.y-oldY);
        }
    }

    public int getComposite() {
        return this.composite.get(this.composite.size()-1);
    }
    public void setComposite(int composite) {
        this.composite.add(composite);
    }
    public void deComposite(){
        this.composite.remove(this.composite.size()-1);
    }

    private ArrayList<Point> Points = new ArrayList();
    private ArrayList<Integer> composite = new ArrayList();
}

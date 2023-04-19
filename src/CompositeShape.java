import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class CompositeShape implements ExtendShape{
    @Override
    public Point nearPoint(Point p) {
        return null;
    }

    @Override
    public ArrayList<Point> getPoints() {
        return null;
    }

    @Override
    public int getOX() {
        return 0;
    }

    @Override
    public int getOY() {
        return 0;
    }

    @Override
    public int getW() {
        return 0;
    }

    @Override
    public int getH() {
        return 0;
    }

    @Override
    public int getComposite() {
        return 0;
    }

    @Override
    public void setComposite(int composite) {

    }

    @Override
    public void deComposite() {

    }

    @Override
    public void move(Point p) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return false;
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return false;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return null;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return null;
    }

    public void addObject(ExtendShape s){
        if(s instanceof CompositeShape){
            ArrayList<ExtendShape> r = ((CompositeShape) s).getArr();
            for(int i=0;i<r.size();i++){
                this.addObject(r.get(i));
            }
        }
        else{
            arr.add(s);
        }
    }
    public int getNum(){
        return arr.size();
    }

    public ArrayList<ExtendShape> getArr() {
        return arr;
    }

    private ArrayList<ExtendShape> arr = new ArrayList();
}

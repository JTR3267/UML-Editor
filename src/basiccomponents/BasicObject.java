package basiccomponents;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BasicObject {
    protected int depth;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String name;
    protected ArrayList<Port> ports;

    public BasicObject(int x, int y, int depth){
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.ports = new ArrayList();
    }

    public BasicObject(int depth) {
        this.depth = depth;
        this.ports = new ArrayList();
    }

    public int getDepth() {
        return this.depth;
    }

    public void draw(Graphics2D g){}

    public void drawPort(Graphics2D g){
        for(Port port : this.ports){
            port.draw(g);
        }
    }

    public Port closestPort(int x, int y){
        double miniDistance = Integer.MAX_VALUE;
        Port rtPort = null;
        for(Port port : this.ports){
            if(Point2D.distance(port.getX(), port.getY(), x, y) < miniDistance){
                miniDistance = Point2D.distance(port.getX(), port.getY(), x, y);
                rtPort = port;
            }
        }
        return rtPort;
    }

    public boolean insideRect(Rectangle rectangle){
        return rectangle.contains(new Rectangle(this.x, this.y, this.width, this.height));
    }

    public Point isClicked(int x,int y){
        return null;
    }

    public void moveByRef(int x,int y){
        move(this.x + x, this.y + y);
    }

    public void move(int x,int y){
        for(Port port : this.ports){
            port.move(x - this.x, y - this.y);
        }
        this.x = x;
        this.y = y;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void unGroup(ArrayList<BasicObject> allBasicObjects){};
}
package basiccomponents;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class NewClass extends BasicObject{
    public NewClass(int x, int y, int depth) {
        super(x, y, depth);
        this.width = 44;
        this.height = 60;
        this.ports.add(new Port(x+this.width/2, y));
        this.ports.add(new Port(x+this.width/2, y+this.height));
        this.ports.add(new Port(x, y+this.height/2));
        this.ports.add(new Port(x+this.width, y+this.height/2));
        this.name = "Class";
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawRect(this.x, this.y, this.width, this.height);
        g.drawString(this.name, this.x, this.y + this.height/2);
    }

    @Override
    public Point isClicked(int x, int y) {
        Rectangle2D.Double rectangle2D = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
        if(rectangle2D.contains(x,y)) return new Point(this.x, this.y);
        else return null;
    }
}
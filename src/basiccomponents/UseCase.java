package basiccomponents;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class UseCase extends BasicObject{
    public UseCase(int x, int y, int depth) {
        super(x, y, depth);
        this.width = 60;
        this.height = 44;
        this.ports.add(new Port(x+this.width/2, y));
        this.ports.add(new Port(x+this.width/2, y+this.height));
        this.ports.add(new Port(x, y+this.height/2));
        this.ports.add(new Port(x+this.width, y+this.height/2));
        this.name = "UseCase";
    }

    @Override
    public void draw(Graphics2D g) {
        g.draw(new Ellipse2D.Double(this.x, this.y, this.width, this.height));
        g.drawString(this.name, this.x, this.y + this.height/2);
    }

    @Override
    public Point isClicked(int x, int y) {
        Ellipse2D.Double ellipse2D = new Ellipse2D.Double(this.x, this.y, this.width, this.height);
        if(ellipse2D.contains(x,y)) return new Point(this.x, this.y);
        else return null;
    }
}
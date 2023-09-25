package basiccomponents;

import java.awt.*;

public class ExtendLine  {
    protected Port p1;
    protected Port p2;

    public ExtendLine(Port p1, Port p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public void draw(Graphics2D g){
        g.drawLine(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY());
    }
}
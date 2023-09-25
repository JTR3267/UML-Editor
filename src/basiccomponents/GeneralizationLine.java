package basiccomponents;

import java.awt.*;
import java.awt.geom.AffineTransform;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;

public class GeneralizationLine extends ExtendLine{
    public GeneralizationLine(Port p1, Port p2){
        super(p1, p2);
    }

    @Override
    public void draw(Graphics2D g){
        super.draw(g);
        double theta = atan2(this.p2.getY()-this.p1.getY(), this.p2.getX()-this.p1.getX());
        AffineTransform old = g.getTransform();
        g.rotate(theta+ PI/2,this.p2.getX(),this.p2.getY());
        g.drawPolygon(new int[] { this.p2.getX()-3, this.p2.getX(), this.p2.getX()+3},
                new int[] {this.p2.getY()+5, this.p2.getY()-7, this.p2.getY()+5}, 3);
        g.setTransform(old);
    }
}
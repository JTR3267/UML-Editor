package basiccomponents;

import java.awt.*;

public class Port {
    private int  x;
    private int  y;

    public Port(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x,int y){
        this.x += x;
        this.y += y;
    }

    public void draw(Graphics2D g){
        g.fillRect(this.x - 2, this.y - 2, 4,4);
    }

    public int getX() { return this.x; }

    public int getY() {
        return this.y;
    }
}

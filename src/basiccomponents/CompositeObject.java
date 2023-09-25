package basiccomponents;

import java.awt.*;
import java.util.ArrayList;

public class CompositeObject extends BasicObject{
    private ArrayList<BasicObject> basicObjects;
    private Point refP;

    public CompositeObject(ArrayList<BasicObject> arrayList, int depth){
        super(depth);
        this.basicObjects = new ArrayList();
        refP = null;
        for(BasicObject basicObject : arrayList) this.basicObjects.add(basicObject);
    }

    @Override
    public void draw(Graphics2D g) {
        for(BasicObject basicObject : this.basicObjects){
            basicObject.draw(g);
        }
    }

    @Override
    public void drawPort(Graphics2D g) {
        for(BasicObject basicObject : this.basicObjects){
            basicObject.drawPort(g);
        }
    }

    @Override
    public Point isClicked(int x, int y) {
        int maxDepth = -1;
        for(BasicObject basicObject : this.basicObjects){
            if(basicObject.isClicked(x, y) != null && basicObject.getDepth() > maxDepth){
                maxDepth = basicObject.getDepth();
                this.refP = basicObject.isClicked(x, y);//判斷哪個basicObject被click
            }
        }
        if(maxDepth > -1) return this.refP;
        return null;
    }

    @Override
    public void moveByRef(int x, int y) {
        for(BasicObject basicObject : this.basicObjects){
            basicObject.moveByRef(x, y);
        }
    }

    @Override
    public void move(int x, int y) {
        for(BasicObject basicObject : this.basicObjects){
            basicObject.moveByRef(x - this.refP.x, y - this.refP.y);
        }
    }

    @Override
    public boolean insideRect(Rectangle rectangle) {
        for(BasicObject basicObject : this.basicObjects){
            if(!basicObject.insideRect(rectangle)) return false;
        }
        return true;
    }

    @Override
    public Port closestPort(int x, int y) {
        return null;
    }

    @Override
    public void changeName(String name){}

    @Override
    public void unGroup(ArrayList<BasicObject> allBasicObjects){
        for(BasicObject basicObject : this.basicObjects){
            allBasicObjects.add(basicObject);
        }
        allBasicObjects.remove(this);
    }
}
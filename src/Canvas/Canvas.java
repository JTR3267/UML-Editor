package Canvas;

import basiccomponents.BasicObject;
import basiccomponents.ExtendLine;
import basiccomponents.Mode;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas extends JPanel {
    private static Canvas canvas;
    private Mode mode;
    private CanvasMouseListener canvasMouseListener;
    private ArrayList<BasicObject> basicObjects;
    private ArrayList<ExtendLine> extendLines;
    private ArrayList<BasicObject> selectObjects;
    private JLabel titleLabel;
    private int depth;

    private Canvas(){
        super();
        this.setPreferredSize(new Dimension(550, 300));
        this.setLayout(new GridLayout(22,0));

        this.mode = Mode.NONE;

        this.basicObjects = new ArrayList();

        this.extendLines = new ArrayList();

        this.selectObjects = new ArrayList();

        this.titleLabel = new JLabel("Canvas");
        this.add(this.titleLabel);

        this.depth = 0;
    }

    public void setCanvasMouseListener(){
        this.canvasMouseListener = new CanvasMouseListener();
        this.addMouseListener(this.canvasMouseListener);
    }

    public static Canvas getInstance(){
        if(canvas == null){
            canvas = new Canvas();
        }
        return canvas;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }

    public ArrayList<BasicObject> getBasicObjects() {
        return this.basicObjects;
    }

    public void deleteBasicObject(BasicObject basicObject) {this.basicObjects.remove(basicObject);}

    public void addBasicObject(BasicObject basicObject) {
        this.basicObjects.add(basicObject);
    }

    public void addLine(ExtendLine line){this.extendLines.add(line);}

    public void addSelectObject(BasicObject basicObject) {
        this.selectObjects.add(basicObject);
    }

    public ArrayList<BasicObject> getSelectObjects() {
        return this.selectObjects;
    }

    public void clearSelectObjects(){
        this.selectObjects.clear();
    }

    public BasicObject getTopObject(int x, int y){
        int maxDepth = -1;
        BasicObject basicObject = null;
        for(BasicObject basicObject1 : this.basicObjects){
            if(basicObject1.isClicked(x,y) != null && basicObject1.getDepth() > maxDepth){
                maxDepth = basicObject1.getDepth();
                basicObject = basicObject1;
            }
        }
        return basicObject;
    }

    public int getDepth(){ return this.depth++; }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(BasicObject basicObject : this.basicObjects) basicObject.draw(g2);
        for(ExtendLine line : this.extendLines) line.draw(g2);
        for(BasicObject basicObject : this.selectObjects) basicObject.drawPort(g2);
    }
}
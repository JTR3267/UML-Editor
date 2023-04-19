import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static java.lang.Math.*;

public class EditCanvas extends JPanel {
    public EditCanvas() {
        this.setPreferredSize(new Dimension(550, 300));
        this.setLayout(new GridLayout(22,0));
        this.add(titleLabel);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (LeftBar.mode == "class") {
                    NewClass r = new NewClass(e.getX(), e.getY(), 44, 60);
                    Shapes.add(r);
                    JLabel l = new JLabel("class");
                    add(l);
                    nameList.add(l);
                    repaint();
                }
                else if (LeftBar.mode == "usecase") {
                    UseCase c = new UseCase(e.getX(), e.getY(), 60, 44);
                    Shapes.add(c);
                    JLabel l = new JLabel("usecase");
                    add(l);
                    nameList.add(l);
                    repaint();
                }
                else if(LeftBar.mode == "select"){
                    showPoints.clear();
                    selectOne = false;
                    selectMode = false;
                    selectShapes.clear();
                    for(int i=Shapes.size()-1;i>=0;i--){
                        if(Shapes.get(i).contains(e.getX(),e.getY())){
                            selectMode = true;
                            if(Shapes.get(i).getComposite() == -1){
                                selectShapes.add(Shapes.get(i));
                            }
                            else{
                                selectOne = true;
                                CompositeShape c = (CompositeShape) TopMenu.compositeShapes.get(Shapes.get(i).getComposite());
                                ArrayList<ExtendShape> a = c.getArr();
                                for(int j=0;j<a.size();j++){
                                    selectShapes.add(a.get(j));
                                }
                            }
                            break;
                        }
                    }
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(LeftBar.mode == "association" || LeftBar.mode == "generalization"
                        || LeftBar.mode == "composition"){
                    for(int i=Shapes.size()-1;i>=0;i--){
                        if(Shapes.get(i).contains(e.getX(),e.getY())){
                            startPoint = Shapes.get(i).nearPoint(e.getPoint());
                            startShape = Shapes.get(i);
                            dragMode=true;
                            repaint();
                            break;
                        }
                    }
                }
                else if(LeftBar.mode == "select"){
                    moveStart = moveEnd = e.getPoint();
                    moveOneShape = false;
                    moveShapes = false;
                    selectShape=null;
                    showPoints.clear();
                    repaint();
                    selectAll = true;
                    selectStart = e.getPoint();
                    for(int i=0;i<Shapes.size();i++){
                        if(Shapes.get(i).contains(e.getPoint())){
                            selectShape = Shapes.get(i);
                            selectAll = false;
                            break;
                        }
                    }
                    if(selectShape!=null){
                        if(selectShape.getComposite()==-1){
                            moveOneShape =true;
                        }
                        else{
                            moveShapes = true;
                        }
                    }
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(dragMode) {
                    dragMode = false;
                    lineMode = false;
                    for (int i = Shapes.size() - 1; i >= 0; i--) {
                        if (Shapes.get(i).contains(e.getPoint()) && Shapes.get(i) != startShape) {
                            endPoint = Shapes.get(i).nearPoint(e.getPoint());
                            lineMode = true;
                            break;
                        }
                    }
                    if(lineMode){
                        if(LeftBar.mode == "association"){
                            AssociationLine l = new AssociationLine(startPoint,endPoint);
                            arrows.add("association");
                            Lines.add(l);
                        }
                        else if (LeftBar.mode == "generalization"){
                            GeneralizationLine l = new GeneralizationLine(startPoint,endPoint);
                            arrows.add("generalization");
                            Lines.add(l);
                        }
                        else{
                            CompositionLine l = new CompositionLine(startPoint,endPoint);
                            arrows.add("composition");
                            Lines.add(l);
                        }
                    }
                    repaint();
                }
                else if(selectAll){
                    selectOne = true;
                    selectPrint = true;
                    tempShapes.clear();
                    selectShapes.clear();
                    selectEnd = e.getPoint();
                    Rectangle selectRect = new Rectangle(min(selectStart.x,selectEnd.x), min(selectStart.y,selectEnd.y),
                            abs(selectEnd.x-selectStart.x), abs(selectEnd.y-selectStart.y));
                    for(int i=0;i<Shapes.size();i++){
                        if(selectRect.contains(Shapes.get(i).getOX(),Shapes.get(i).getOY(),Shapes.get(i).getW(),Shapes.get(i).getH())){
                            tempShapes.add(Shapes.get(i));
                        }
                    }
                    for(int i=0;i<tempShapes.size();i++){
                        if(tempShapes.get(i).getComposite() == -1){
                            selectShapes.add(tempShapes.get(i));
                        }
                        else{
                            CompositeShape c = (CompositeShape) TopMenu.compositeShapes.get(tempShapes.get(i).getComposite());
                            int num = c.getNum();
                            boolean jud = false;
                            int count = 0;
                            for(int j=0;j<tempShapes.size();j++){
                                if(tempShapes.get(j).getComposite() == tempShapes.get(i).getComposite()){
                                    count++;
                                }
                                if(count == num){
                                    jud = true;
                                    break;
                                }
                            }
                            if(jud){
                                selectShapes.add(tempShapes.get(i));
                            }
                        }
                    }
                    for(int i=1;i<selectShapes.size();i++){
                        if(selectShapes.get(i).getComposite() != selectShapes.get(i-1).getComposite()){
                            selectOne = false;
                        }
                    }
                    if(selectOne == true){
                        int cnt = 0;
                        for(int i =0;i<selectShapes.size();i++){
                            if(selectShapes.get(i).getComposite() == -1){
                                cnt++;
                            }
                        }
                        if(cnt>1){
                            selectOne = false;
                        }
                    }
                    repaint();
                }
                else if(moveOneShape){
                    moveOneShape = false;
                    moveEnd = e.getPoint();
                    if(moveStart.x != moveEnd.x && moveStart.y !=moveEnd.y){
                        showPoints.clear();
                        ArrayList<Point> p = new ArrayList();
                        for(int i=0;i<selectShape.getPoints().size();i++){
                            p.add(new Point(selectShape.getPoints().get(i).x, selectShape.getPoints().get(i).y));
                        }
                        selectShape.move(e.getPoint());
                        ArrayList<Point> newP = selectShape.getPoints();
                        for(int i=0;i<Lines.size();i++){
                            Point p1 = new Point((int) Lines.get(i).x1,(int) Lines.get(i).y1);
                            Point p2 = new Point((int) Lines.get(i).x2,(int) Lines.get(i).y2);
                            for(int j=0;j<p.size();j++){
                                if(Lines.get(i).getP1().distance(p.get(j))==0){
                                    Lines.get(i).setLine(newP.get(j),p2);
                                    break;
                                }
                                if(Lines.get(i).getP2().distance(p.get(j))==0){
                                    Lines.get(i).setLine(p1,newP.get(j));
                                    break;
                                }
                            }
                        }
                        repaint();
                    }
                }
                else if (moveShapes) {
                    moveShapes = false;
                    moveEnd = e.getPoint();
                    if(moveStart.x != moveEnd.x && moveStart.y !=moveEnd.y){
                        moveShapeList.clear();
                        Point originP = new Point(selectShape.getOX(), selectShape.getOY());
                        showPoints.clear();
                        for(int i=0;i<Shapes.size();i++){
                            if(Shapes.get(i).getComposite() == selectShape.getComposite()){
                                moveShapeList.add(Shapes.get(i));
                            }
                        }
                        for(int k=0;k<moveShapeList.size();k++){
                            int dx = moveShapeList.get(k).getOX() - originP.x;
                            int dy = moveShapeList.get(k).getOY() - originP.y;
                            ArrayList<Point> p = new ArrayList();
                            for(int i=0;i<moveShapeList.get(k).getPoints().size();i++){
                                p.add(new Point(moveShapeList.get(k).getPoints().get(i).x, moveShapeList.get(k).getPoints().get(i).y));
                            }
                            Point newE = new Point(e.getX()+dx, e.getY()+dy);
                            moveShapeList.get(k).move(newE);
                            ArrayList<Point> newP = moveShapeList.get(k).getPoints();
                            for(int i=0;i<Lines.size();i++){
                                Point p1 = new Point((int) Lines.get(i).x1,(int) Lines.get(i).y1);
                                Point p2 = new Point((int) Lines.get(i).x2,(int) Lines.get(i).y2);
                                for(int j=0;j<p.size();j++){
                                    if(Lines.get(i).getP1().distance(p.get(j))==0){
                                        Lines.get(i).setLine(newP.get(j),p2);
                                        break;
                                    }
                                    if(Lines.get(i).getP2().distance(p.get(j))==0){
                                        Lines.get(i).setLine(p1,newP.get(j));
                                        break;
                                    }
                                }
                            }
                        }
                        repaint();
                    }
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragMode) {
                    endPoint = e.getPoint();
                    repaint();
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i=0;i<Shapes.size();i++){
            g2.draw(Shapes.get(i));
            Dimension size = nameList.get(i).getPreferredSize();
            nameList.get(i).setBounds(Shapes.get(i).getOX()+5, Shapes.get(i).getOY()+Shapes.get(i).getH()/2-10, size.width, size.height);
        }
        for(int i=0;i<Lines.size();i++){
            g2.draw(Lines.get(i));
            Point2D startP = Lines.get(i).getP1();
            Point2D endP = Lines.get(i).getP2();
            double theta = atan2(endP.getY()-startP.getY(), endP.getX()-startP.getX());
            AffineTransform old = g2.getTransform();
            if(arrows.get(i) == "association"){
                g2.rotate(theta+ PI/2,endP.getX(),endP.getY());
                g2.drawLine((int) endP.getX()-3,(int) endP.getY()+3,(int) endP.getX(), (int) endP.getY());
                g2.drawLine((int) endP.getX()+3,(int) endP.getY()+3,(int) endP.getX(), (int) endP.getY());
            }
            else if(arrows.get(i) == "generalization"){
                g2.rotate(theta+ PI/2,endP.getX(),endP.getY());
                g2.drawPolygon(new int[] {(int) endP.getX()-3, (int) endP.getX(), (int) endP.getX()+3},
                        new int[] {(int) endP.getY()+5, (int) endP.getY()-7, (int) endP.getY()+5}, 3);
            }
            else{
                g2.rotate(theta+ PI/4,endP.getX(),endP.getY());
                g2.drawRect((int) endP.getX()-3, (int) endP.getY()-3, 6, 6);
            }
            g2.setTransform(old);
        }
        for(int i=0;i<showPoints.size();i++){
            g2.draw(new Rectangle2D.Double(showPoints.get(i).x-0.5, showPoints.get(i).y- 0.5, 2, 2));
        }

        if(dragMode){
            g2.drawLine(startPoint.x,startPoint.y,endPoint.x,endPoint.y);
        }

        else if(selectMode){
            for(int i=0;i<selectShapes.size();i++){
                ArrayList<Point> arr = selectShapes.get(i).getPoints();
                for(int j=0;j<arr.size();j++){
                    showPoints.add(arr.get(j));
                    g2.draw(new Rectangle2D.Double(arr.get(j).x-0.5, arr.get(j).y- 0.5, 2, 2));
                }
            }
            selectMode = false;
        }

        else if(selectPrint){
            for(int i=0;i<selectShapes.size();i++){
                ArrayList<Point> arr = selectShapes.get(i).getPoints();
                for(int j=0;j<arr.size();j++){
                    showPoints.add(arr.get(j));
                    g2.draw(new Rectangle2D.Double(arr.get(j).x-0.5, arr.get(j).y- 0.5, 2, 2));
                }
            }
            selectPrint = false;
        }
    }
    public LeftBar getLeftBar() {
        return this.leftBar;
    }

    public static boolean selectOne = false;
    private boolean dragMode = false;
    private boolean lineMode = false;
    private boolean selectMode = false;
    private boolean selectAll = false;
    private boolean selectPrint = false;
    private boolean moveOneShape = false;
    private boolean moveShapes = false;
    private ArrayList<String> arrows = new ArrayList();
    private ArrayList<Point> showPoints = new ArrayList();
    private ArrayList<ExtendShape> tempShapes = new ArrayList();
    private LeftBar leftBar = new LeftBar();
    public static ArrayList<JLabel> nameList = new ArrayList();
    public static ArrayList<ExtendShape> Shapes = new ArrayList();
    private ArrayList<Line2D.Double> Lines = new ArrayList();
    public static ArrayList<ExtendShape> selectShapes = new ArrayList();
    private ArrayList<ExtendShape> moveShapeList = new ArrayList();
    private JLabel titleLabel = new JLabel("Canvas");
    private Point moveStart = new Point();
    private Point moveEnd = new Point();
    private Point startPoint = new Point();
    private Point endPoint = new Point();
    private Point selectStart = new Point();
    private Point selectEnd = new Point();
    private ExtendShape startShape;
    private ExtendShape selectShape;
}
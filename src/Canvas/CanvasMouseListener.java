package Canvas;

import basiccomponents.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class CanvasMouseListener extends MouseAdapter {
    private Canvas canvas;
    private BasicObject startObject;
    private Port startPort;
    private BasicObject endObject;
    private Port endPort;

    public CanvasMouseListener(){
        super();
        this.canvas = Canvas.getInstance();

        this.startObject = null;

        this.startPort = null;

        this.endObject = null;

        this.endPort = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (this.canvas.getMode()) {
            case CREATE_CLASS:
                this.canvas.clearSelectObjects();
                this.canvas.addBasicObject(new NewClass(e.getX(), e.getY(), this.canvas.getDepth()));
                this.canvas.repaint();
                break;
            case CREATE_USECASE:
                this.canvas.clearSelectObjects();
                this.canvas.addBasicObject(new UseCase(e.getX(), e.getY(), this.canvas.getDepth()));
                this.canvas.repaint();
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (this.canvas.getMode()){
            case SELECT:
                this.canvas.clearSelectObjects();
                this.startPort = new Port(e.getX(), e.getY());
                this.canvas.repaint();
                break;
            case ASSOCIATION_LINE:
            case GENERALIZATION_LINE:
            case COMPOSITION_LINE:
                this.canvas.clearSelectObjects();
                this.canvas.repaint();
                this.startObject = this.canvas.getTopObject(e.getX(), e.getY());
                if(this.startObject != null) this.startPort = this.startObject.closestPort(e.getX(), e.getY());
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (this.canvas.getMode()){
            case SELECT:
                this.endPort = new Port(e.getX(), e.getY());
                if(this.canvas.getTopObject(startPort.getX(), startPort.getY()) != null//移動
                        && (this.startPort.getX() != this.endPort.getX() || this.startPort.getY() != this.endPort.getY())){
                    this.canvas.getTopObject(this.startPort.getX(), this.startPort.getY()).move(e.getX(), e.getY());
                }
                else if(this.canvas.getTopObject(this.startPort.getX(), this.startPort.getY()) != null//單獨選取
                        && this.startPort.getX() == this.endPort.getX() && this.startPort.getY() == this.endPort.getY()){
                    this.canvas.addSelectObject(this.canvas.getTopObject(this.startPort.getX(), this.startPort.getY()));
                }
                else{//範圍選取
                    Rectangle rectangle = new Rectangle(min(this.startPort.getX(), this.endPort.getX()), min(this.startPort.getY(), this.endPort.getY()),
                            max(this.startPort.getX(), this.endPort.getX()) - min(this.startPort.getX(), this.endPort.getX()),
                            max(this.startPort.getY(), this.endPort.getY()) - min(this.startPort.getY(), this.endPort.getY()));
                    for(BasicObject basicObject : this.canvas.getBasicObjects()){
                        if(basicObject.insideRect(rectangle)) this.canvas.addSelectObject(basicObject);
                    }
                }
                this.canvas.repaint();
                this.startPort = null;
                this.endPort = null;
                break;
            case ASSOCIATION_LINE:
            case GENERALIZATION_LINE:
            case COMPOSITION_LINE:
                if(this.startPort != null){
                    this.endObject = this.canvas.getTopObject(e.getX(), e.getY());
                    if(this.endObject != null && this.endObject != this.startObject){
                        this.endPort = this.endObject.closestPort(e.getX(), e.getY());
                        if(this.endPort != null) {
                            if (this.canvas.getMode() == Mode.ASSOCIATION_LINE) this.canvas.addLine(new AssociationLine(startPort, endPort));
                            else if (this.canvas.getMode() == Mode.GENERALIZATION_LINE) this.canvas.addLine(new GeneralizationLine(startPort, endPort));
                            else this.canvas.addLine(new CompositionLine(startPort, endPort));
                            this.canvas.repaint();
                        }
                    }
                }
                this.startObject = null;
                this.startPort = null;
                this.endObject = null;
                this.endPort = null;
                break;
        }
    }
}
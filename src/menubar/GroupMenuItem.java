package menubar;

import basiccomponents.BasicObject;
import basiccomponents.CompositeObject;
import basiccomponents.Mode;
import Canvas.Canvas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupMenuItem extends JMenuItem {
    private Canvas canvas;

    public GroupMenuItem(String string){
        super(string);
        this.canvas = Canvas.getInstance();
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(canvas.getSelectObjects().size() > 1 && canvas.getMode() == Mode.SELECT){
                    for(BasicObject basicObject : canvas.getSelectObjects()){
                        canvas.deleteBasicObject(basicObject);
                    }
                    canvas.addBasicObject(new CompositeObject(canvas.getSelectObjects(), canvas.getDepth()));
                    canvas.repaint();
                }
            }
        });
    }
}
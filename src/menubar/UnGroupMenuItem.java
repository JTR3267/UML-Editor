package menubar;

import basiccomponents.Mode;
import Canvas.Canvas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnGroupMenuItem extends JMenuItem {
    private Canvas canvas;

    public UnGroupMenuItem(String string){
        super(string);
        this.canvas = Canvas.getInstance();
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(canvas.getSelectObjects().size() == 1 && canvas.getMode() == Mode.SELECT){
                    canvas.getSelectObjects().get(0).unGroup(canvas.getBasicObjects());
                    canvas.repaint();
                }
            }
        });
    }
}
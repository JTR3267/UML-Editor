package menubar;

import basiccomponents.Mode;
import Canvas.Canvas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeNameMenuItem extends JMenuItem {
    private Canvas canvas;

    public ChangeNameMenuItem(String string){
        super(string);
        this.canvas = Canvas.getInstance();
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(canvas.getMode() == Mode.SELECT && canvas.getSelectObjects().size() == 1){
                    String getMessage = JOptionPane.showInputDialog(canvas, "Enter New Name");
                    if(getMessage!=null){
                        canvas.getSelectObjects().get(0).changeName(getMessage);
                        canvas.repaint();
                    }
                }
            }
        });
    }
}
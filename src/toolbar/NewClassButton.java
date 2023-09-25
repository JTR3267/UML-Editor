package toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewClassButton extends JButton{
    private Icon newClassIcon;
    private Icon newClassBlackIcon;

    public NewClassButton(){
        super();
        this.newClassIcon = new ImageIcon("img/class.png");
        this.newClassBlackIcon = new ImageIcon("img/class_black.png");
        this.setIcon(this.newClassIcon);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("NewClassButtonClicked", null, null);
            }}
        );
    }

    public void setNewClassBlackIcon() {
        this.setIcon(this.newClassBlackIcon);
    }

    public void setNewClassIcon() {
        this.setIcon(this.newClassIcon);
    }
}
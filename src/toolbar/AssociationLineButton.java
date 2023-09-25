package toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssociationLineButton extends JButton {
    private Icon associationLineIcon;

    public AssociationLineButton(){
        super();
        this.associationLineIcon = new ImageIcon("img/association_line.png");
        this.setIcon(this.associationLineIcon);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("AssociationLineButtonClicked", null, null);
            }}
        );
    }
}
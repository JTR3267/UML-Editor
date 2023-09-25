package toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectButton extends JButton {
    private Icon selectIcon;

    public SelectButton(){
        super();
        this.selectIcon = new ImageIcon("img/select.png");
        this.setIcon(this.selectIcon);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("SelectButtonClicked", null, null);
            }}
        );
    }
}
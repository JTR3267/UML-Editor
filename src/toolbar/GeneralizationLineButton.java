package toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralizationLineButton extends JButton {
    private Icon generalizationLineIcon;

    public GeneralizationLineButton(){
        super();
        this.generalizationLineIcon = new ImageIcon("img/generalization_line.png");
        this.setIcon(this.generalizationLineIcon);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("GeneralizationLineButtonClicked", null, null);
            }}
        );
    }
}
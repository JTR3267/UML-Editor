package toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompositionLineButton extends JButton {
    private Icon compositionLineIcon;

    public CompositionLineButton(){
        super();
        this.compositionLineIcon = new ImageIcon("img/composition_line.png");
        this.setIcon(this.compositionLineIcon);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("CompositionLineButtonClicked", null, null);
            }}
        );
    }
}
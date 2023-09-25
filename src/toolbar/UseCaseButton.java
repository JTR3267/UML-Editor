package toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UseCaseButton extends JButton{
    private Icon useCaseIcon;
    private Icon useCaseBlackIcon;

    public UseCaseButton(){
        super();
        this.useCaseIcon = new ImageIcon("img/use_case.png");
        this.useCaseBlackIcon = new ImageIcon("img/use_case_black.png");
        this.setIcon(this.useCaseIcon);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("UseCaseButtonClicked", null, null);
            }}
        );
    }

    public void setUseCaseBlackIcon() {
        this.setIcon(this.useCaseBlackIcon);
    }
    public void setUseCaseIcon() {
        this.setIcon(this.useCaseIcon);
    }
}
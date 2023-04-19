import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftBar extends JToolBar {
    public LeftBar(){
        this.setFloatable(false);
        this.setOrientation(SwingConstants.VERTICAL);
        this.add(selectButton);
        this.add(associationButton);
        this.add(generalizationButton);
        this.add(compositionButton);
        this.add(classButton);
        this.add(usecaseButton);
        this.selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode="select";
                classButton.setIcon(classIcon);
                usecaseButton.setIcon(usecaseIcon);
            }}
        );
        this.associationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode="association";
                classButton.setIcon(classIcon);
                usecaseButton.setIcon(usecaseIcon);
            }}
        );
        this.generalizationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode="generalization";
                classButton.setIcon(classIcon);
                usecaseButton.setIcon(usecaseIcon);
            }}
        );
        this.compositionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode="composition";
                classButton.setIcon(classIcon);
                usecaseButton.setIcon(usecaseIcon);
            }}
        );
        this.classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode="class";
                classButton.setIcon(classBlackIcon);
                usecaseButton.setIcon(usecaseIcon);
            }}
        );
        this.usecaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode="usecase";
                usecaseButton.setIcon(usecaseBlackIcon);
                classButton.setIcon(classIcon);
            }}
        );
    }
    public static String mode = "none";
    private Icon selectIcon = new ImageIcon("img/select.png");
    private JButton selectButton = new JButton(selectIcon);
    private Icon associationIcon = new ImageIcon("img/association_line.png");
    private JButton associationButton = new JButton(associationIcon);
    private Icon generalizationIcon = new ImageIcon("img/generalization_line.png");
    private JButton generalizationButton = new JButton(generalizationIcon);
    private Icon compositionIcon = new ImageIcon("img/composition_line.png");
    private JButton compositionButton = new JButton(compositionIcon);
    private Icon classIcon = new ImageIcon("img/class.png");
    private Icon classBlackIcon = new ImageIcon("img/class_black.png");
    private JButton classButton = new JButton(classIcon);
    private Icon usecaseIcon = new ImageIcon("img/use_case.png");
    private Icon usecaseBlackIcon = new ImageIcon("img/use_case_black.png");
    private JButton usecaseButton = new JButton(usecaseIcon);
}

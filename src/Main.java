import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame mainFrame = new JFrame("UML editor");
    public static void main(String[] args) {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700,375);
        mainFrame.setResizable(false);

        TopMenu topMenu = new TopMenu();
        mainFrame.getContentPane().add(BorderLayout.NORTH, topMenu);

        EditCanvas mainCanvas = new EditCanvas();
        mainFrame.getContentPane().add(BorderLayout.WEST, mainCanvas.getLeftBar());
        mainFrame.getContentPane().add(mainCanvas);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
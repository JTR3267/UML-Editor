package UMLEditor;

import Canvas.Canvas;
import menubar.MenuBar;
import toolbar.ToolBar;
import javax.swing.*;
import java.awt.*;

public class UMLEditor {
    private JFrame mainFrame;
    private Canvas canvas;
    private MenuBar menuBar;
    private ToolBar toolBar;

    public UMLEditor(){
        this.mainFrame = new JFrame("UML editor");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(700,375);
        this.mainFrame.setResizable(false);

        this.menuBar = new MenuBar();
        this.mainFrame.getContentPane().add(BorderLayout.NORTH, this.menuBar);

        this.toolBar = new ToolBar();
        this.mainFrame.getContentPane().add(BorderLayout.WEST, this.toolBar);

        this.canvas = Canvas.getInstance();
        this.canvas.setCanvasMouseListener();
        this.mainFrame.getContentPane().add(this.canvas);

        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }
}
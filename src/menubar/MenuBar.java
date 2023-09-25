package menubar;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    private JMenu fileMenu;
    private JMenu editMenu;
    private GroupMenuItem groupMenuItem;
    private UnGroupMenuItem unGroupMenuItem;
    private ChangeNameMenuItem changeNameMenuItem;

    public MenuBar(){
        super();
        this.setBackground(new Color(85, 85, 85));

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        groupMenuItem = new GroupMenuItem("Group");
        this.editMenu.add(this.groupMenuItem);

        unGroupMenuItem = new UnGroupMenuItem("UnGroup");
        this.editMenu.add(this.unGroupMenuItem);

        changeNameMenuItem = new ChangeNameMenuItem("Change Object Name");
        this.editMenu.add(this.changeNameMenuItem);

        this.add(this.fileMenu);
        this.add(this.editMenu);
    }
}
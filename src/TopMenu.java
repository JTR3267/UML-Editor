import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TopMenu extends JMenuBar {
    public TopMenu(){
        this.setBackground(new Color(85, 85, 85));
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(groupItem);
        editMenu.add(ungroupItem);
        editMenu.add(changeItem);
        this.add(fileMenu);
        this.add(editMenu);

        this.groupItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(LeftBar.mode == "select" && EditCanvas.selectShapes.size()>1 && EditCanvas.selectOne == false){
                    CompositeShape c = new CompositeShape();
                    for(int i=0;i<EditCanvas.selectShapes.size();i++){
                        EditCanvas.selectShapes.get(i).setComposite(compositeNum);
                        c.addObject(EditCanvas.selectShapes.get(i));
                    }
                    compositeShapes.add(c);
                    compositeNum++;
                }
            }
        });

        this.ungroupItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(LeftBar.mode == "select" && EditCanvas.selectShapes.size()>1 && EditCanvas.selectOne == true){
                    for(int i=0;i<EditCanvas.selectShapes.size();i++){
                        EditCanvas.selectShapes.get(i).deComposite();
                    }
                }
            }
        });

        this.changeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(LeftBar.mode == "select" && EditCanvas.selectShapes.size()==1){
                    String getMessage = JOptionPane.showInputDialog(Main.mainFrame, "Enter New Name");
                    if(getMessage!=null){
                        EditCanvas.nameList.get(EditCanvas.Shapes.indexOf(EditCanvas.selectShapes.get(0))).setText(getMessage);
                    }
                }
            }
        });
    }
    public static int compositeNum=0;
    public static ArrayList<ExtendShape> compositeShapes = new ArrayList();
    private JMenuItem groupItem = new JMenuItem("Group");
    private JMenuItem ungroupItem = new JMenuItem("Ungroup");
    private JMenuItem changeItem = new JMenuItem("Change Object Name");

}

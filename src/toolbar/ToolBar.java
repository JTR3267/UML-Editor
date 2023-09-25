package toolbar;

import basiccomponents.Mode;
import Canvas.Canvas;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ToolBar extends JToolBar implements PropertyChangeListener {
    private Canvas canvas;
    private SelectButton selectButton;
    private AssociationLineButton associationLineButton;
    private GeneralizationLineButton generalizationLineButton;
    private CompositionLineButton compositionLineButton;
    private NewClassButton newClassButton;
    private UseCaseButton useCaseButton;

    public ToolBar(){
        super();

        this.canvas = Canvas.getInstance();

        this.setFloatable(false);
        this.setOrientation(SwingConstants.VERTICAL);

        this.selectButton = new SelectButton();
        this.add(this.selectButton);
        this.selectButton.addPropertyChangeListener(this);

        this.associationLineButton = new AssociationLineButton();
        this.add(this.associationLineButton);
        this.associationLineButton.addPropertyChangeListener(this);

        this.generalizationLineButton = new GeneralizationLineButton();
        this.add(this.generalizationLineButton);
        this.generalizationLineButton.addPropertyChangeListener(this);

        this.compositionLineButton = new CompositionLineButton();
        this.add(this.compositionLineButton);
        this.compositionLineButton.addPropertyChangeListener(this);

        this.newClassButton = new NewClassButton();
        this.add(this.newClassButton);
        this.newClassButton.addPropertyChangeListener(this);

        this.useCaseButton = new UseCaseButton();
        this.add(this.useCaseButton);
        this.useCaseButton.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        switch (propertyName){
            case "SelectButtonClicked":
                this.newClassButton.setNewClassIcon();
                this.useCaseButton.setUseCaseIcon();
                this.canvas.setMode(Mode.SELECT);
                break;
            case "AssociationLineButtonClicked":
                this.newClassButton.setNewClassIcon();
                this.useCaseButton.setUseCaseIcon();
                this.canvas.setMode(Mode.ASSOCIATION_LINE);
                break;
            case "GeneralizationLineButtonClicked":
                this.newClassButton.setNewClassIcon();
                this.useCaseButton.setUseCaseIcon();
                this.canvas.setMode(Mode.GENERALIZATION_LINE);
                break;
            case "CompositionLineButtonClicked":
                this.newClassButton.setNewClassIcon();
                this.useCaseButton.setUseCaseIcon();
                this.canvas.setMode(Mode.COMPOSITION_LINE);
                break;
            case "NewClassButtonClicked":
                this.newClassButton.setNewClassBlackIcon();
                this.useCaseButton.setUseCaseIcon();
                this.canvas.setMode(Mode.CREATE_CLASS);
                break;
            case "UseCaseButtonClicked":
                this.newClassButton.setNewClassIcon();
                this.useCaseButton.setUseCaseBlackIcon();
                this.canvas.setMode(Mode.CREATE_USECASE);
                break;
        }
    }
}
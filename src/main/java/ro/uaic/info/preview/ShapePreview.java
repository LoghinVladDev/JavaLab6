package ro.uaic.info.preview;

import ro.uaic.info.panel.ShapeSelector;
import ro.uaic.info.panel.ShapeSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapePreview extends JPanel {
    private JButton selectButton;
    private ShapeSelector parent;

    public ShapePreview(ShapeSelector parent, String shapeName){
        super();
        this.setMinimumSize(new Dimension((int)(parent.getHeight() * 1.2) - 5, parent.getHeight() - 20));
        this.setBackground(Color.lightGray);
        this.parent = parent;

        this.selectButton = new JButton(shapeName);
        this.selectButton.setMinimumSize(new Dimension((int)(parent.getHeight() * 1.2) - 5, parent.getHeight() - 20));
        this.add(this.selectButton);

        this.selectButton.addActionListener(e->{
            this.initializeShapeSettings();
        });

        System.out.println(parent.getHeight());
        System.out.println(this.getHeight());
    }

    private void initializeShapeSettings(){
        //System.out.println(this.selectButton.getText());
        if(this.parent.getMainFrame().getShapeSettings() == null)
        this.parent.getMainFrame().setShapeSettings(new ShapeSettings(this.parent.getMainFrame(), this));
        else
            this.parent.getMainFrame().getShapeSettings().resetSettings(this);
    }

    public String getShapeName(){
        return this.selectButton.getText();
    }
}

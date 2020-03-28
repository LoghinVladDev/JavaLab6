package ro.uaic.info.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ro.uaic.info.geometry.*;
import ro.uaic.info.geometry.Shape;

public class ActiveElements extends JPanel {
    private JList<Shape> activeShapesList;
    private DefaultListModel<Shape> listModel;

    public ActiveElements(App mainFrame){
        super();

        this.setBounds(0,
                mainFrame.getShapes().getHeight(),
                120,
                mainFrame.getHeight()
                        - mainFrame.getShapes().getHeight()
                        - App.TITLE_BAR_HEIGHT
                        - mainFrame.getControls().getHeight()
        );

        this.setBackground(Color.LIGHT_GRAY);
        this.listModel = new DefaultListModel<>();
        this.activeShapesList = new JList<>(this.listModel);

        this.activeShapesList.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(activeShapesList.isSelectionEmpty())
                    return;
                if(keyEvent.getKeyCode() == KeyEvent.VK_DELETE){
                    Shape a = activeShapesList.getSelectedValue();
                    a.remove();
                    listModel.remove(activeShapesList.getSelectedIndex());
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        this.add(activeShapesList);
    }

    public void addShape(Shape shape){
        this.listModel.addElement(shape);
    }

    public void removeShape(Shape shape){

    }
}

package ro.uaic.info.panel;

import javax.swing.*;
import java.awt.*;

public class DrawingCanvas extends JPanel {
    public DrawingCanvas(App mainFrame){
        super();
        this.setBounds(
                mainFrame.getActiveShapes().getWidth(),
                mainFrame.getShapes().getHeight(),
                mainFrame.getWidth() -
                        mainFrame.getActiveShapes().getWidth(),
                mainFrame.getHeight() -
                        mainFrame.getShapes().getHeight() -
                        mainFrame.getControls().getHeight() -
                        App.TITLE_BAR_HEIGHT
         );

        this.setBackground(Color.WHITE);
    }

    public void shrinkForSettingsPanel(int width){
        this.setBounds(
                this.getX(),
                this.getY(),
                this.getWidth() - width,
                this.getHeight()
        );
    }
}

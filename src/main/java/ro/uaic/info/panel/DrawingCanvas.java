package ro.uaic.info.panel;


import javax.swing.*;
import java.awt.*;

public class DrawingCanvas extends JPanel {
    private App mainFrame;
    private Graphics2D graphics;

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
        this.mainFrame = mainFrame;
        this.setBackground(Color.WHITE);
    }

    public void initGraphics(){
        if(this.graphics == null){
            this.graphics = (Graphics2D) this.mainFrame.getGraphics();
        }
    }

    public void shrinkForSettingsPanel(int width){
        if(!ShapeSettings.isInitialised())
        this.setBounds(
                this.getX(),
                this.getY(),
                this.getWidth() - width,
                this.getHeight()
        );
    }

    public Graphics2D getGraphics2D(){
        return this.graphics;
    }
}

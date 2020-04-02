package ro.uaic.info.panel;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingCanvas extends JPanel {
    private App mainFrame;
    private Graphics2D imageGraphics;
    BufferedImage bufferedImage;

    public DrawingCanvas(App mainFrame){
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
        this.bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.imageGraphics = this.bufferedImage.createGraphics();
        this.imageGraphics.setColor(Color.WHITE);
        this.imageGraphics.fillRect(0,0,this.getWidth(), this.getHeight());
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
        return (Graphics2D) this.getGraphics();
    }

    public Graphics2D getImageGraphics() { return this.imageGraphics; }

    public BufferedImage getImage(){
        return this.bufferedImage;
    }

    public void paintComponent(Graphics g){
        g.drawImage(this.bufferedImage, 0, 0, this);
    }
}

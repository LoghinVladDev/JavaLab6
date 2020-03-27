package ro.uaic.info.panel;

import ro.uaic.info.exception.InvalidShape;
import ro.uaic.info.geometry.Shape;
import ro.uaic.info.panel.ActiveElements;
import ro.uaic.info.panel.ControlPanel;
import ro.uaic.info.panel.DrawingCanvas;
import ro.uaic.info.panel.ShapeSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class App extends JFrame {
    private DrawingCanvas canvas;
    private ControlPanel controls;
    private ShapeSelector shapes;
    private ActiveElements activeShapes;
    private ShapeSettings shapeSettings;

    public static final int APP_WINDOW_DEFAULT_WIDTH = 1366;
    public static final int APP_WINDOW_DEFAULT_HEIGHT = 768;
    public static final int APP_WINDOW_DEFAULT_X_OFFSET = 0;
    public static final int APP_WINDOW_DEFAULT_Y_OFFSET = 0;
    public static final int TITLE_BAR_HEIGHT = 39;

    private int windowWidth = APP_WINDOW_DEFAULT_WIDTH;
    private int windowHeight = APP_WINDOW_DEFAULT_HEIGHT;
    private int windowOffsetX = APP_WINDOW_DEFAULT_X_OFFSET;
    private int windowOffsetY = APP_WINDOW_DEFAULT_Y_OFFSET;

    public App(){
        super();
        super.setBounds(
                this.windowOffsetX,
                this.windowOffsetY,
                this.windowWidth,
                this.windowHeight
        );
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.shapes = new ShapeSelector(this);
        this.controls = new ControlPanel(this);
        this.activeShapes = new ActiveElements(this);
        this.canvas = new DrawingCanvas(this);

        this.add(this.shapes);
        this.add(this.activeShapes);
        this.add(this.controls);
        this.add(this.canvas);

        new MouseEventHandler(this.canvas, this);

        super.setLayout(null);
        super.setVisible(true);
        super.setSize(this.windowWidth+15, this.windowHeight);
        this.canvas.initGraphics();
    }

    public ShapeSelector getShapes(){
        return this.shapes;
    }

    public ControlPanel getControls(){
        return this.controls;
    }

    public ActiveElements getActiveShapes() { return this.activeShapes; }

    public ShapeSettings getShapeSettings() { return this.shapeSettings; }

    public DrawingCanvas getCanvas() { return this.canvas; }

    public void setShapeSettings(ShapeSettings shapeSettings){ this.shapeSettings = shapeSettings; }
}


class MouseEventHandler implements MouseListener {
    private App mainFrame;

    public MouseEventHandler(DrawingCanvas canvas, App mainFrame){
        canvas.addMouseListener(this);
        this.mainFrame = mainFrame;
    }

    public void mousePressed(MouseEvent e){
        if(e.getClickCount() == 1){
            System.out.println("Mouse pressed on " + e.getComponent().getClass().getName());
            try {
                Shape a = this.mainFrame.getShapeSettings().createShape(this.mainFrame); //TODO : Assign to left panel
                a.draw(Color.BLACK);
            }
            catch(Exception exception){
                System.out.println(exception.toString());
                exception.printStackTrace();
            }
        }
        //System.out.println("Mouse Pressed " + e.getClickCount() + " times");
    }

    public void mouseReleased(MouseEvent e){
        //System.out.println("Mouse Released " + e.getClickCount() + " times");
    }

    public void mouseEntered(MouseEvent e){
        //.out.println("Mouse entered : "
        //        + e.getComponent().getClass().getName()
        //);
    }

    public void mouseExited(MouseEvent e){
        //.out.println("Mouse exited : "
        //        + e.getComponent().getClass().getName()
        //);
    }

    public void mouseClicked(MouseEvent e){
        //System.out.println("Mouse clicked " +
       //         e.getClickCount() + " times on "+
///        );
    }
}

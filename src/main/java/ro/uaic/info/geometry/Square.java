package ro.uaic.info.geometry;

import ro.uaic.info.panel.App;
import ro.uaic.info.panel.DrawingCanvas;

import java.awt.*;
import java.awt.geom.Line2D;

public class Square extends Shape{
    private App mainApp;
    private Graphics2D graphics;
    private int x;
    private int y;
    private int length;
    private int stroke;

    public Square(App mainFrame, Object size, Object stroke, Point center){
        System.out.println(size + " " + stroke + " " + center.x + " " + center.y);

        this.x = center.x;
        this.y = center.y;
        this.length = (int)size;
        this.stroke = (int)stroke;

        this.mainApp = mainFrame;
        this.graphics = this.mainApp.getCanvas().getGraphics2D();

    }

    public void draw(){
        this.graphics.setStroke(new BasicStroke(this.stroke, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0f, null, 0f));
        this.graphics.drawRect(
                this.x - this.length/2,
                this.y - this.length/2,
                this.length,
                this.length
        );
    }

    public void remove(){
        this.draw(Color.WHITE);
    }

    public void draw(Color color){
        Color oldColor = this.graphics.getColor();

        this.graphics.setColor(color);
        this.draw();

        this.graphics.setColor(oldColor);
    }

    public String getName(){
        return "Square";
    }
    public String toString(){
        return "Square";
    }
}

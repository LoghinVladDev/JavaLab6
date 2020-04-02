package ro.uaic.info.geometry;

import ro.uaic.info.exception.InvalidShape;
import ro.uaic.info.panel.App;
import ro.uaic.info.panel.DrawingCanvas;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Square extends Shape implements Serializable {
    private int x;
    private int y;
    private int length;
    private int stroke;

    public void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject("Square");
        objectOutputStream.writeInt(x);
        objectOutputStream.writeInt(y);
        objectOutputStream.writeInt(length);
        objectOutputStream.writeInt(stroke);
    }

    public static Shape readSpecialisedObject(ObjectInputStream objectInputStream) throws IOException {
        Square s = new Square();
        s.x = objectInputStream.readInt();
        s.y = objectInputStream.readInt();
        s.length = objectInputStream.readInt();
        s.stroke = objectInputStream.readInt();
        return s;
    }

    public Square(){
        this.graphics = null;
        this.mainApp = null;
        this.length = 0;
        this.stroke = 0;
    }

    public Square(App mainFrame, Object size, Object stroke, Point center,Color edgeColor, Color fillColor){
        this.edgeColor = edgeColor;
        this.fillColor = fillColor;
        System.out.println(size + " " + stroke + " " + center.x + " " + center.y);

        this.mainApp = mainFrame;
        this.x = center.x - this.mainApp.getActiveShapes().getWidth() - 8;
        this.y = center.y - this.mainApp.getShapes().getHeight() - App.TITLE_BAR_HEIGHT + 8;
        this.length = (int)size;
        this.stroke = (int)stroke;

        this.graphics = (Graphics2D) this.mainApp.getCanvas().getGraphics();

    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setStroke(new BasicStroke(this.stroke, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0f, null, 0f));
        graphics2D.drawRect(
                this.x - this.length/2,
                this.y - this.length/2,
                this.length,
                this.length
        );
    }

    public void remove(){
        this.draw(Color.WHITE);
    }

    public String getName(){
        return "Square";
    }
    public String toString(){
        return "Square";
    }
}

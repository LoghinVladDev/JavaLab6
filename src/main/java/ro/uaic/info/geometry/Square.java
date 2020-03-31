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

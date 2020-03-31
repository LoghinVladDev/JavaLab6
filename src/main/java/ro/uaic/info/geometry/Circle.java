package ro.uaic.info.geometry;

import ro.uaic.info.panel.App;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Circle extends Shape implements Serializable {
    private int x;
    private int y;
    private int ray;
    private int stroke;

    public void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject("Circle");
        objectOutputStream.writeInt(x);
        objectOutputStream.writeInt(y);
        objectOutputStream.writeInt(ray);
        objectOutputStream.writeInt(stroke);
    }

    public static Shape readSpecialisedObject(ObjectInputStream objectInputStream) throws IOException{
        Circle c = new Circle();
        c.x = objectInputStream.readInt();
        c.y = objectInputStream.readInt();
        c.ray = objectInputStream.readInt();
        c.stroke = objectInputStream.readInt();
        return c;
    }

    public Circle()
    {
        this.x = 0;
        this.y = 0;
        this.ray = 0;
        this.stroke = 0;
        this.mainApp = null;
        this.graphics = null;
    }

    public Circle(App mainApp, Object size, Object stroke, Point center){
        System.out.println(size + " " + stroke + " " + center.x + " " + center.y);

        this.x = center.x;
        this.y = center.y;

        this.ray = (int)size;

        this.stroke = (int)stroke;

        this.mainApp = mainApp;
        this.graphics = this.mainApp.getCanvas().getGraphics2D();
    }

    @Override
    public void draw() {
        this.graphics.setStroke(new BasicStroke(this.stroke, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0f, null, 0f));
        this.graphics.drawOval(
                this.x - ray/2,
                this.y - ray/2,
                this.ray,
                this.ray
        );
    }

    public void remove(){
        this.draw(Color.WHITE);
    }

    @Override
    public void draw(Color color) {
        Color oldColor = this.graphics.getColor();

        this.graphics.setColor(color);

        this.draw();

        this.graphics.setColor(oldColor);
    }

    public String getName(){
        return "Circle";
    }

    public String toString(){
        return "Circle";
    }
}

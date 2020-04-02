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

    private Circle()
    {
        this.x = 0;
        this.y = 0;
        this.ray = 0;
        this.stroke = 0;
        this.mainApp = null;
        this.graphics = null;
    }

    public Circle(App mainApp, Object size, Object stroke, Point center, Color edgeColor, Color fillColor){
        this.edgeColor = edgeColor;
        this.fillColor = fillColor;
        System.out.println(size + " " + stroke + " " + center.x + " " + center.y);

        this.mainApp = mainApp;
        this.x = center.x - this.mainApp.getActiveShapes().getWidth() - 8;
        this.y = center.y - this.mainApp.getShapes().getHeight() - App.TITLE_BAR_HEIGHT + 8;

        this.ray = (int)size;

        this.stroke = (int)stroke;

        this.graphics = (Graphics2D)this.mainApp.getCanvas().getGraphics();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(this.stroke, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0f, null, 0f));
        graphics2D.drawOval(
                this.x - ray/2,
                this.y - ray/2,
                this.ray,
                this.ray
        );
        //this.graphics.drawLine(this.x, this.y - ray/2, this.x, this.y + ray/2);
        //this.graphics.drawLine(this.x - ray/2, this.y, this.x + ray/2, this.y);
    }

    public void remove(){
        this.draw(Color.WHITE);
    }

    public String getName(){
        return "Circle";
    }

    public String toString(){
        return "Circle";
    }
}

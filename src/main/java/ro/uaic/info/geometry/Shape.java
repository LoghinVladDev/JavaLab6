package ro.uaic.info.geometry;


import ro.uaic.info.exception.InvalidShape;
import ro.uaic.info.panel.App;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Shape implements Serializable {
    protected App mainApp;
    protected Graphics2D graphics;
    public enum Shapes{
        SQUARE, CIRCLE
    }

    public void setGraphics(Graphics2D graphics){
        this.graphics = graphics;
    }

    public abstract void draw();
    public abstract void draw(Color color);
    public abstract String getName();
    public abstract void remove();

    public abstract String toString();

    public abstract void writeObject(ObjectOutputStream objectOutputStream) throws IOException;
    public static Shape readObject(ObjectInputStream objectInputStream) throws InvalidShape {
        try{
            String shapeType = (String) objectInputStream.readObject();
            switch(shapeType){
                case "Circle": return Circle.readSpecialisedObject(objectInputStream);
                case "Square": return Square.readSpecialisedObject(objectInputStream);
                default : return null;
            }
        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }
    }
}

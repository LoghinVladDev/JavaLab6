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
    protected Color edgeColor;
    protected Color fillColor;
    protected App mainApp;
    protected Graphics2D graphics;
    public enum Shapes{
        SQUARE, CIRCLE
    }

    public void setGraphics(Graphics2D graphics){
        this.graphics = graphics;
    }

    public void setMainApp(App mainApp){ this.mainApp = mainApp; }

    public void setEdgeColor(Color color){ this.edgeColor = color; }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void draw(){
        this.graphics.setColor(this.edgeColor);
        this.mainApp.getCanvas().getImageGraphics().setColor(this.edgeColor);
        this.draw(this.graphics, this.fillColor);
        this.draw(this.mainApp.getCanvas().getImageGraphics(), this.fillColor);
    }
    public abstract void draw(Graphics2D graphics2D, Color fillColor);

    public void draw(Color color){
        Color oldColor = this.graphics.getColor();

        this.graphics.setColor(color);
        this.mainApp.getCanvas().getImageGraphics().setColor(color);
        this.draw();

        this.graphics.setColor(oldColor);
        this.mainApp.getCanvas().getImageGraphics().setColor(oldColor);
    }
    public abstract String getName();
    public void remove(){
        Color oldFill = this.fillColor;
        Color oldEdge = this.edgeColor;
        this.fillColor = Color.white;
        this.edgeColor = Color.white;
        this.draw(Color.WHITE);
        this.fillColor = oldFill;
        this.edgeColor = oldEdge;
    }

    public abstract String toString();

    public abstract void writeObject(ObjectOutputStream objectOutputStream) throws IOException;
    public static Shape readObject(ObjectInputStream objectInputStream) throws InvalidShape {
        try{
            String shapeType = (String) objectInputStream.readObject();
            switch(shapeType){
                case "Circle": return Circle.readSpecialisedObject(objectInputStream);
                case "Square": return Square.readSpecialisedObject(objectInputStream);
                case "RegPoly" : return RegularPolygon.readSpecialisedObject(objectInputStream);
                default : return null;
            }
        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }
    }
}

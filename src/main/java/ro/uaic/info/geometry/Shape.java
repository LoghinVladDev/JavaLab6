package ro.uaic.info.geometry;


import java.awt.*;
import java.util.ArrayList;

public abstract class Shape {
    public enum Shapes{
        SQUARE, CIRCLE
    }

    public abstract void draw();
    public abstract void draw(Color color);
}

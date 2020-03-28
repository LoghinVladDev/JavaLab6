package ro.uaic.info.geometry;

import ro.uaic.info.panel.App;

import java.awt.*;

public class Circle extends Shape {
    private App mainApp;
    private Graphics2D graphics;
    private int x;
    private int y;
    private int ray;
    private int stroke;

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

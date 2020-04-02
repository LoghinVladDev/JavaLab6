package ro.uaic.info.geometry;

import ro.uaic.info.panel.App;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class RegularPolygon extends Shape implements Serializable {
    private int x;
    private int y;
    private int length;
    private int stroke;
    private int edgeCount;
    private ArrayList<Point> points;
    private int angle;

    public void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject("RegPoly");
        objectOutputStream.writeInt(this.x);
        objectOutputStream.writeInt(this.y);
        objectOutputStream.writeInt(this.length);
        objectOutputStream.writeInt(this.stroke);
        objectOutputStream.writeObject(this.edgeColor);
        objectOutputStream.writeObject(this.fillColor);
        objectOutputStream.writeInt(this.edgeCount);
        objectOutputStream.writeInt(this.angle);
    }

    public static Shape readSpecialisedObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException{
        RegularPolygon r = new RegularPolygon();
        r.x = objectInputStream.readInt();
        r.y = objectInputStream.readInt();
        r.length = objectInputStream.readInt();
        r.stroke = objectInputStream.readInt();

        r.edgeColor = (Color) objectInputStream.readObject();
        r.fillColor = (Color) objectInputStream.readObject();

        r.edgeCount = objectInputStream.readInt();
        r.angle = objectInputStream.readInt();

        for(int i = 0 ;i < 360; i+= 360/r.edgeCount) {
            r.points.add(new Point(r.x + (int) (r.length * Math.sin((float) (r.angle+i) * Math.PI / 180)), r.y + (int) (r.length * Math.cos((float) (r.angle+i) * Math.PI / 180))));
        }
        return r;
    }

    public RegularPolygon(){
        this.graphics = null;
        this.mainApp = null;
        this.x = 0;
        this.y = 0;
        this.length = 0;
        this.stroke = 0;
        this.edgeCount = 0;
        this.points = new ArrayList<>();
        this.angle = 0;
    }

    public RegularPolygon(App mainFrame, Object size, Object stroke, Point center, Color edgeColor, Color fillColor, Object pointCount, Object angle){
        this.edgeColor = edgeColor;
        System.out.println(edgeCount);
        this.fillColor = fillColor;

        this.mainApp = mainFrame;
        this.x = center.x - this.mainApp.getActiveShapes().getWidth() - 8;
        this.y = center.y - this.mainApp.getShapes().getHeight() - App.TITLE_BAR_HEIGHT + 8;
        this.length = (int)size;
        this.stroke = (int)stroke;

        this.graphics = (Graphics2D) this.mainApp.getCanvas().getGraphics();

        this.edgeCount = (int)pointCount;
        this.angle = (int)angle;

        this.points = new ArrayList<>();

        for(int i = 0 ;i < 360; i+= 360/this.edgeCount) {
            this.points.add(new Point(this.x + (int) (this.length * Math.sin((float) (i+this.angle) * Math.PI / 180)), this.y + (int) (this.length * Math.cos((float) (i+this.angle) * Math.PI / 180))));
        }
    }

    public void draw(Graphics2D graphics2D, Color fillColor){
        graphics2D.setStroke(new BasicStroke(this.stroke, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0f, null, 0f));
        /*for(int i = 0; i < this.points.size() - 1; i++){
            graphics2D.drawLine(this.points.get(i).x, this.points.get(i).y, this.points.get(i+1).x, this.points.get(i+1).y);
        }
        graphics2D.drawLine(this.points.get(0).x, this.points.get(0).y, this.points.get(this.points.size()-1).x, this.points.get(this.points.size()-1).y);
*/
        GeneralPath poly = new GeneralPath();

        poly.moveTo(this.points.get(0).x, this.points.get(0).y);

        for(int i = 1; i < this.points.size(); i++)
            poly.lineTo(this.points.get(i).x, this.points.get(i).y);

        poly.closePath();

        graphics2D.draw(poly);


        Color oldColor = graphics2D.getColor();
        graphics2D.setColor(fillColor);

        graphics2D.fill(poly);

        graphics2D.setColor(oldColor);
        /*...*/
    }

    public String getName() { return "Regular Polygon"; }
    public String toString() { return "Regular Polygon"; }
}

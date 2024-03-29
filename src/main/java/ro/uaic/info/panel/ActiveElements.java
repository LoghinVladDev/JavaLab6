package ro.uaic.info.panel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import ro.uaic.info.exception.InvalidShape;
import ro.uaic.info.geometry.*;
import ro.uaic.info.geometry.Shape;

public class ActiveElements extends JPanel implements Serializable {
    private JList<Shape> activeShapesList;
    private DefaultListModel<Shape> listModel;
    private App mainFrame;

    public ActiveElements(App mainFrame){
        super();
        this.setBounds(0,
                mainFrame.getShapes().getHeight(),
                120,
                mainFrame.getHeight()
                        - mainFrame.getShapes().getHeight()
                        - App.TITLE_BAR_HEIGHT
                        - mainFrame.getControls().getHeight()
        );

        this.mainFrame = mainFrame;
        this.setBackground(Color.LIGHT_GRAY);
        this.listModel = new DefaultListModel<>();
        this.activeShapesList = new JList<>(this.listModel);

        this.activeShapesList.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(activeShapesList.isSelectionEmpty())
                    return;
                if(keyEvent.getKeyCode() == KeyEvent.VK_DELETE){
                    Shape a = activeShapesList.getSelectedValue();
                    a.remove();
                    listModel.remove(activeShapesList.getSelectedIndex());
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        this.add(activeShapesList);
    }

    public void addShape(Shape shape){
        this.listModel.addElement(shape);
    }

    public void removeShape(Shape shape){

    }

    public void saveAsImage(String path){
        try{
            if(ImageIO.write(this.mainFrame.getCanvas().getImage(), "png", new File(path))) {
                System.out.println("--saved--");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void save(String path){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(
                        path
                )
        )){
            Shape[] shapes = new Shape[this.listModel.size()];
            this.listModel.copyInto(shapes);

            for(Shape s : shapes){
                s.writeObject(objectOutputStream);
            }
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public void load(String path){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(path)
        )){/*
            ArrayList<Shape> shapesList = new ArrayList<>();
            shapesList = (ArrayList<Shape>) objectInputStream.readObject();

            clearAll();

            shapesList.forEach(e->this.listModel.addElement(e));*/
            this.clearAll();

            Shape s;

            do{
                s = Shape.readObject(objectInputStream);
                if(s != null) {
                    s.setGraphics((Graphics2D)this.mainFrame.getCanvas().getGraphics());
                    s.setMainApp(this.mainFrame);
                    this.listModel.addElement(s);
                    s.draw();
                    System.out.println(s);
                }
            }while(s != null);
        }
        catch (IOException | InvalidShape e){
            System.out.println(e.toString());
        }
    }

    public void clearAll(){
        while(!listModel.isEmpty()){
            listModel.remove(0).remove();
        }
    }
}

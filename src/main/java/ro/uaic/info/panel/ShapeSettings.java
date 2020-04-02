package ro.uaic.info.panel;

import ro.uaic.info.exception.InvalidShape;
import ro.uaic.info.geometry.Circle;
import ro.uaic.info.geometry.Shape;
import ro.uaic.info.geometry.Square;
import ro.uaic.info.preview.ShapePreview;

import javax.swing.*;
import java.awt.*;

public class ShapeSettings extends JPanel {
    private String whichShape;
    private int windowWidth;
    private JLabel shapeName;
    private JLabel sizeSliderLabel;
    private JLabel strokeSliderLabel;
    private JSpinner sizeSlider;
    private JSpinner strokeSlider;
    private JButton selectEdgeColor;
    private JButton selectFillColor;
    private Color edgeColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private JCheckBox isRandom;

    private static boolean initalised =  false;

    public static boolean isInitialised() {
        return ShapeSettings.initalised;
    }

    public JSpinner getSizeSlider(){
        return this.sizeSlider;
    }

    public JSpinner getStrokeSlider(){
        return this.strokeSlider;
    }

    public ShapeSettings(App mainFrame, ShapePreview selected){
        super();
        this.windowWidth = 190;
        this.setBounds(
                mainFrame.getWidth()- 16 - this.windowWidth,
                mainFrame.getShapes().getHeight(),
                this.windowWidth,
                mainFrame.getHeight() -
                        mainFrame.getShapes().getHeight() -
                        mainFrame.getControls().getHeight() -
                        App.TITLE_BAR_HEIGHT
        );
        mainFrame.add(this);
        this.whichShape = selected.getShapeName();

        GroupLayout windowLayout = new GroupLayout(this);
        this.setLayout(windowLayout);

        windowLayout.setAutoCreateContainerGaps(true);
        windowLayout.setAutoCreateGaps(true);

        this.sizeSliderLabel = new JLabel("Size");
        this.strokeSliderLabel = new JLabel("Stroke");
        this.sizeSlider = new JSpinner();
        this.strokeSlider = new JSpinner();

        this.selectEdgeColor = new JButton("Select Edge Color");
        this.selectFillColor = new JButton("Select Fill Color");

        this.isRandom = new JCheckBox("Random Color");

        this.sizeSlider.setValue(50);
        this.strokeSlider.setValue(2);

        this.selectFillColor.addActionListener(e->this.fillSelectColor());
        this.selectEdgeColor.addActionListener(e->this.edgeSelectColor());
        this.isRandom.addActionListener(e->this.randomStateChanged());

        this.shapeName = new JLabel(this.whichShape);

        windowLayout.setHorizontalGroup(
                windowLayout.createSequentialGroup()
                    .addGroup(windowLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(this.shapeName)
                            .addComponent(this.sizeSliderLabel)
                            .addComponent(this.strokeSliderLabel)
                            .addComponent(this.selectEdgeColor)
                            .addComponent(this.selectFillColor)
                            .addComponent(this.isRandom)
                    )
                    .addGroup(windowLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(this.sizeSlider)
                            .addComponent(this.strokeSlider)
                    )
        );

        windowLayout.setVerticalGroup(
                windowLayout.createSequentialGroup()
                    .addComponent(this.shapeName)
                    .addGroup(windowLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(this.sizeSliderLabel)
                            .addComponent(this.sizeSlider)
                    )
                    .addGroup(windowLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(this.strokeSliderLabel)
                            .addComponent(this.strokeSlider)
                    )
                    .addComponent(this.selectEdgeColor)
                    .addComponent(this.selectFillColor)
                    .addComponent(this.isRandom)
        );

        //windowLayout.linkSize(SwingConstants.VERTICAL, this.sizeSlider, this.sizeSliderLabel);

        this.setBackground(Color.LIGHT_GRAY);

        mainFrame.getCanvas().shrinkForSettingsPanel(this.windowWidth);
        ShapeSettings.initalised = true;
    }

    private void randomStateChanged(){
        if(this.isRandom.isSelected()){
            this.selectEdgeColor.setEnabled(false);
            this.selectFillColor.setEnabled(false);
        }
        else{
            this.selectEdgeColor.setEnabled(true);
            this.selectFillColor.setEnabled(true);
        }
    }

    private void edgeSelectColor(){
    }

    private void fillSelectColor(){

    }

    public void resetSettings(ShapePreview preview){
        this.sizeSlider.setValue(50);
        this.strokeSlider.setValue(2);
        this.whichShape = preview.getShapeName();
        this.shapeName.setText(this.whichShape);
    }

    public int getWindowWidth(){
        return this.windowWidth;
    }

    public String getWhichShape(){
        return this.whichShape;
    }

    public Shape createShape(App mainApp) throws InvalidShape {
        Color edgeColor = Color.BLACK;
        Color fillColor = Color.WHITE;

        if(this.isRandom.isSelected()){
            edgeColor = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
            fillColor = this.edgeColor;
        }

        System.out.println(this.edgeColor);
        switch(this.whichShape){
            case "Square" : return new Square(
                    mainApp,
                    this.sizeSlider.getValue(),
                    this.strokeSlider.getValue(),
                    MouseInfo.getPointerInfo().getLocation(),
                    edgeColor,
                    fillColor
            );
            case "Circle" : return new Circle(
                    mainApp,
                    this.sizeSlider.getValue(),
                    this.strokeSlider.getValue(),
                    MouseInfo.getPointerInfo().getLocation(),
                    edgeColor,
                    fillColor
            );
            default : throw new InvalidShape("No such Shape exists");

        }
    }
}

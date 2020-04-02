package ro.uaic.info.panel;

import ro.uaic.info.exception.InvalidShape;
import ro.uaic.info.geometry.Circle;
import ro.uaic.info.geometry.RegularPolygon;
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
    private JLabel edgeCountLabel;
    private JLabel strokeSliderLabel;
    private JLabel angleLabel;

    private JSpinner sizeSlider;
    private JSpinner strokeSlider;
    private JSpinner edgeCount;
    private JSpinner angle;

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
        this.windowWidth = 200;
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

        this.sizeSliderLabel = new JLabel("Size");
        this.strokeSliderLabel = new JLabel("Stroke");
        this.sizeSlider = new JSpinner();
        this.strokeSlider = new JSpinner();



        this.selectEdgeColor = new JButton("Edge Color");
        this.selectFillColor = new JButton("Fill Color");

        this.isRandom = new JCheckBox("Random Color");

        this.sizeSlider.setValue(50);
        this.strokeSlider.setValue(2);

        this.selectFillColor.addActionListener(e->this.fillSelectColor());
        this.selectEdgeColor.addActionListener(e->this.edgeSelectColor());
        this.isRandom.addActionListener(e->this.randomStateChanged());

        this.shapeName = new JLabel(this.whichShape);

        this.reInitGroups();

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
        this.edgeColor = JColorChooser.showDialog(this, "Choose an Edge Color", this.edgeColor);
    }

    private void fillSelectColor(){
        this.fillColor = JColorChooser.showDialog(this, "Choose a Fill Color", this.fillColor);
    }

    public void resetSettings(ShapePreview preview){
        this.sizeSlider.setValue(50);
        this.strokeSlider.setValue(2);
        this.whichShape = preview.getShapeName();
        this.shapeName.setText(this.whichShape);
        this.reInitGroups();
    }

    private void reInitGroups(){
        if(this.angleLabel != null)
            this.remove(this.angleLabel);
        if(this.edgeCountLabel != null)
            this.remove(this.edgeCountLabel);

        GroupLayout windowLayout = new GroupLayout(this);
        this.setLayout(windowLayout);

        windowLayout.setAutoCreateContainerGaps(true);
        windowLayout.setAutoCreateGaps(true);

        if(this.whichShape.equals("Regular Polygon")){
            SpinnerModel edgeModel = new SpinnerNumberModel(3, 3, 100, 1);
            SpinnerModel angleModel = new SpinnerNumberModel(0, 0, 360, 1);

            this.angleLabel = new JLabel("Angle");
            this.angle = new JSpinner(angleModel);
            this.edgeCountLabel = new JLabel("Edges");
            this.edgeCount = new JSpinner(edgeModel);
        }

        GroupLayout.Group leftGroup = windowLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.shapeName)
                .addComponent(this.sizeSliderLabel)
                .addComponent(this.strokeSliderLabel);

        GroupLayout.Group rightGroup = windowLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.sizeSlider)
                .addComponent(this.strokeSlider);

        if(this.whichShape.equals("Regular Polygon")){
            leftGroup.addComponent(this.edgeCountLabel).addComponent(this.angleLabel);
            rightGroup.addComponent(this.edgeCount).addComponent(this.angle);
        }

        leftGroup
                .addComponent(this.selectEdgeColor)
                .addComponent(this.selectFillColor)
                .addComponent(this.isRandom);

        GroupLayout.SequentialGroup horizontalGroup = windowLayout.createSequentialGroup()
                .addGroup(leftGroup)
                .addGroup(rightGroup);

        GroupLayout.SequentialGroup verticalGroup = windowLayout.createSequentialGroup()
                .addComponent(this.shapeName)
                .addGroup(windowLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(this.sizeSliderLabel)
                        .addComponent(this.sizeSlider)
                )
                .addGroup(windowLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(this.strokeSliderLabel)
                        .addComponent(this.strokeSlider)
                );

        if(this.whichShape.equals("Regular Polygon")){
            verticalGroup
                    .addGroup(windowLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(this.edgeCountLabel)
                            .addComponent(this.edgeCount)
                    )
                    .addGroup(windowLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(this.angleLabel)
                            .addComponent(this.angle)
                    );
        }

        verticalGroup
                .addComponent(this.selectEdgeColor)
                .addComponent(this.selectFillColor)
                .addComponent(this.isRandom);


        windowLayout.setHorizontalGroup(horizontalGroup);
        windowLayout.setVerticalGroup(verticalGroup);
    }

    public int getWindowWidth(){
        return this.windowWidth;
    }

    public String getWhichShape(){
        return this.whichShape;
    }

    public Shape createShape(App mainApp) throws InvalidShape {

        if(this.isRandom.isSelected()){
            edgeColor = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
            fillColor = edgeColor;
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
            case "Regular Polygon" : return new RegularPolygon(
                    mainApp,
                    this.sizeSlider.getValue(),
                    this.strokeSlider.getValue(),
                    MouseInfo.getPointerInfo().getLocation(),
                    edgeColor,
                    fillColor,
                    this.edgeCount.getValue(),
                    this.angle.getValue()
            );
            default : throw new InvalidShape("No such Shape exists");

        }
    }
}

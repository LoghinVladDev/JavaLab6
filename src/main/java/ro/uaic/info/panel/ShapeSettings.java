package ro.uaic.info.panel;

import ro.uaic.info.geometry.Shape;
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

    public JSpinner getSizeSlider(){
        return this.sizeSlider;
    }

    public JSpinner getStrokeSlider(){
        return this.strokeSlider;
    }

    public ShapeSettings(App mainFrame, ShapePreview selected){
        super();
        this.windowWidth = 150;
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

        this.shapeName = new JLabel(this.whichShape);

        windowLayout.setHorizontalGroup(
                windowLayout.createSequentialGroup()
                    .addGroup(windowLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(this.shapeName)
                            .addComponent(this.sizeSliderLabel)
                            .addComponent(this.strokeSliderLabel)
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
        );

        //windowLayout.linkSize(SwingConstants.VERTICAL, this.sizeSlider, this.sizeSliderLabel);

        this.setBackground(Color.LIGHT_GRAY);

        mainFrame.getCanvas().shrinkForSettingsPanel(this.windowWidth);
    }

    public int getWindowWidth(){
        return this.windowWidth;
    }

    public String getWhichShape(){
        return this.whichShape;
    }
}

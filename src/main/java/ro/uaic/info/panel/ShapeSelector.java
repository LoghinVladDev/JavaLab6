package ro.uaic.info.panel;

import ro.uaic.info.preview.ShapePreview;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShapeSelector extends JPanel {

    private JScrollPane shapeScroller;
    private App mainFrame;

    public ShapeSelector(App mainFrame){
        super();
        this.setBounds(0, 0, mainFrame.getWidth(), 200);
        this.setSize(mainFrame.getWidth(), 200);
        this.setSize(mainFrame.getWidth(), 54);
        this.setBackground(Color.gray);
        this.mainFrame = mainFrame;

        JPanel j = new JPanel();
        /*
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 10, 5);
        j.setLayout(layout);*/

        GridLayout layout = new GridLayout(1,0);
        j.setLayout(layout);
        layout.layoutContainer(j);

        j.add(new ShapePreview(this, "Square"));
        j.add(new ShapePreview(this, "Circle"));
        j.add(new ShapePreview(this,"Regular Polygon"));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.shapeScroller = new JScrollPane(j,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        this.shapeScroller.setSize(mainFrame.getWidth()-20, 100);
        this.add(this.shapeScroller);
    }

    public App getMainFrame(){
        return this.mainFrame;
    }
}

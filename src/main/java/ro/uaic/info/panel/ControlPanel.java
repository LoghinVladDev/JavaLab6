package ro.uaic.info.panel;



import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ControlPanel extends JPanel{
    private App mainFrame;
    private JButton saveButton;
    private JButton loadButton;
    private JButton clearButton;
    private JButton saveImageButton;


    public ControlPanel(App mainFrame){
        super();
        this.setBounds(
                0,
                mainFrame.getHeight() - App.TITLE_BAR_HEIGHT - 30,
                mainFrame.getWidth(),
                30
        );
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        this.setBackground(Color.WHITE);
        this.mainFrame = mainFrame;

        this.saveButton = new JButton("Save");
        this.loadButton = new JButton("Load");
        this.clearButton = new JButton("Clear");

        this.add(this.saveButton);
        this.add(this.loadButton);
        this.add(this.clearButton);

        this.clearButton.addActionListener(e->this.clearAllContents());
        this.saveButton.addActionListener(e->this.saveCanvas());
        this.loadButton.addActionListener(e->this.loadCanvas());
    }

    public void clearAllContents(){
        this.mainFrame.getActiveShapes().clearAll();
    }

    public void saveCanvas(){

        this.mainFrame.getActiveShapes().save("D:/test.ser");
    }

    public void loadCanvas(){
        this.mainFrame.getActiveShapes().load("D:/test.ser");
    }
}

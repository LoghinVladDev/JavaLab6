package ro.uaic.info.panel;



import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
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

        this.saveImageButton = new JButton("Save to Image");
        this.saveButton = new JButton("Save Session");
        this.loadButton = new JButton("Load Session");
        this.clearButton = new JButton("Clear Session");

        this.add(this.saveImageButton);
        this.add(this.saveButton);
        this.add(this.loadButton);
        this.add(this.clearButton);

        this.saveImageButton.addActionListener(e->this.saveImageCanvas());
        this.clearButton.addActionListener(e->this.clearAllContents());
        this.saveButton.addActionListener(e->this.saveCanvas());
        this.loadButton.addActionListener(e->this.loadCanvas());
    }

    public void clearAllContents(){
        this.mainFrame.getActiveShapes().clearAll();
    }

    public void saveImageCanvas(){
        JFileChooser fc = new JFileChooser();
        fc.setFileHidingEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.png", "png");
        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);
        int retVal = fc.showSaveDialog(this);

        if(retVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            this.mainFrame.getActiveShapes().saveAsImage(file.getAbsolutePath());
        }
    }

    public void saveCanvas(){

        JFileChooser fc = new JFileChooser();

        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.ser", "ser");
        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);
        fc.setFileHidingEnabled(false);
        int retVal = fc.showSaveDialog(this);

        if(retVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            this.mainFrame.getActiveShapes().save(file.getAbsolutePath());
        }
    }

    public void loadCanvas(){
        JFileChooser fc = new JFileChooser();

        fc.setFileHidingEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.ser (Image Editor Project)", "ser");
        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);
        int retVal = fc.showOpenDialog(this);

        if(retVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            this.mainFrame.getActiveShapes().load(file.getAbsolutePath());
        }
    }
}

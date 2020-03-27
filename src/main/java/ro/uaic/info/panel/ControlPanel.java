package ro.uaic.info.panel;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ControlPanel extends JPanel{
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
    }
}

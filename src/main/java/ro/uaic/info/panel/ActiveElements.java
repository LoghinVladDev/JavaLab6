package ro.uaic.info.panel;

import javax.swing.*;
import java.awt.*;

public class ActiveElements extends JPanel {
    ActiveElements(App mainFrame){
        super();

        this.setBounds(0,
                mainFrame.getShapes().getHeight(),
                120,
                mainFrame.getHeight()
                        - mainFrame.getShapes().getHeight()
                        - App.TITLE_BAR_HEIGHT
                        - mainFrame.getControls().getHeight()
        );

        this.setBackground(Color.cyan);
    }
}

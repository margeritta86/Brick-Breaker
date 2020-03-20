package view;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JFrame {

    private ViewType viewType;

    public View(String title, ViewType viewType) throws HeadlessException {
        super(title);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        this.viewType = viewType;
    }
}

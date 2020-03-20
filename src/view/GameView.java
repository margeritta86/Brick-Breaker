package view;


import game.engine.KeyboardManager;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameView extends View {
    public static final int HEIGHT = 800;
    public static final int WIDTH = 1200;
    private Canvas canvas;
    private Dimension size;


    public GameView() {
        super("Game Play", ViewType.GAME);
        size = new Dimension(WIDTH, HEIGHT);
        createAndSetupFrame();
        createAndSetupCanvas();
        pack();
    }

    private void createAndSetupFrame() {

        setSize(size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createAndSetupCanvas() {
        canvas = new Canvas();
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);
        add(canvas);
        canvas.setFocusable(true);
        canvas.requestFocusInWindow();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void addKeyManager(KeyboardManager keyboard) {
        canvas.addKeyListener(keyboard);

    }

}

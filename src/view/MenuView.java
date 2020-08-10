package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends View {

    private JButton startButton;
    private JButton exitButton;
    private JButton rankingButton;

    private JPanel mainPanel;

    public MenuView() throws HeadlessException {
        super("Bouncing Ball", ViewType.MENU);
        buildMainComponents();
        prepareOtherComponents();
        pack();
    }

    private void buildMainComponents() {
        GridLayout gridlayout = new GridLayout(0, 1);
        mainPanel = new JPanel(gridlayout);
        add(mainPanel);
    }

    private void prepareOtherComponents() {
        startButton = new JButton("START GAME");
        exitButton = new JButton("EXIT GAME");
        rankingButton = new JButton("SHOW RANKING");

        mainPanel.add(startButton);
        mainPanel.add(rankingButton);
        mainPanel.add(exitButton);

    }

    public void addStartAction(ActionListener listener) {
        startButton.addActionListener(listener);

    }

    public void addExitAction(ActionListener listener) {
        exitButton.addActionListener(listener);

    }

    public void addRankingAction(ActionListener listener) {
        rankingButton.addActionListener(listener);

    }
}

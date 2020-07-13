package game.model.ui;

import game.model.GameObject;
import game.model.player.Player;
import view.GameView;

import javax.swing.*;
import java.awt.*;

public class ScoreCounter {

    private Player player;


    public ScoreCounter(Player player) {
        this.player = player;


    }

    public void render(Graphics graphics) {
        int x = GameView.WIDTH - (GameView.WIDTH / 8);
        int y =  GameView.HEIGHT / 8;
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        graphics.drawString("PUNKTY:" + player.getScores(),x,y);

    }

}

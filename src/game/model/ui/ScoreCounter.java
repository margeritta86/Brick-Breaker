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
        int x = GameView.WIDTH - (GameView.WIDTH / 8)-40;
        int y =  (GameView.HEIGHT / 8)-40;
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x,y,170,45);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x,y,170,45);


        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        graphics.drawString("POINTS :" + player.getScores(),x+10,y+30);

    }

}

package game.model.ui;

import game.model.brick.Brick;
import game.model.player.Player;
import view.GameView;


import java.awt.*;

public class ScoreCounter {

    private Player player;


    public ScoreCounter(Player player) {
        this.player = player;

    }

    public void render(Graphics graphics) {
        int alpha = 80; // 50% transparent
        int x = 0;
        int y = 0;
        graphics.setColor(new Color(0, 0, 0, alpha));
        graphics.fillRect(x, y, GameView.WIDTH, Brick.DEFAULT_HEIGHT);
        graphics.setColor(Color.MAGENTA);
        graphics.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        graphics.drawString("POINTS :" + player.getScores(), Brick.DEFAULT_WIDTH, y + 20);

    }

}

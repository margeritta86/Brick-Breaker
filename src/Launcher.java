

import engine.GameEngine;

import java.awt.*;

public class Launcher {
    public static void main(String[] args) {
        GameEngine game = new GameEngine("Odbijanie",new Dimension(1200,800));
        game.start();
    }
}



import game.engine.GameViewController;
import view.MenuView;
import view.ViewFactory;
import view.ViewType;

import javax.swing.*;
import java.awt.*;

public class Launcher {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runApplication();
            }
        });

       // GameViewController game = new GameViewController("Odbijanie");
      //  game.start();
    }

    private static void runApplication(){
        ViewFactory factory = new ViewFactory();
        factory.showView(ViewType.MENU);
    }
}

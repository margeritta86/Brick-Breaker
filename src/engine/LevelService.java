package engine;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static engine.State.*;

public class LevelService {


    private Gameplay gameplay;
    private KeyboardManager keyboard;
    private State state;
    private boolean powtorzPoziom;
    private boolean menuActive;
    LocalDateTime time;
    private int levelNumber = 2;
    private Mediator mediator;
    private ObjectFactory factory;

    public LevelService(Gameplay gameplay, KeyboardManager keyboard) {
        this.gameplay = gameplay;
        this.keyboard = keyboard;
        mediator = new Mediator(gameplay);
        factory = new ObjectFactory(mediator);
        state = PLAY;
    }

    public void tick() {

        endLevel();

        if (isLevelLost()) {
            state = LOOSE;
        } else if (isLevelWon()) {
            state = WIN;
        }
    }

    private void endLevel() {
        if (menuActive) {
            return;
        }
        switch (state) {
            case WIN:
                levelWon();
                break;
            case LOOSE:
                levelLoose();
                break;
        }

    }

    public boolean stopPlaying() {
        return state != PLAY;
    }

    public void preparePreLevel() {
        prepareStage();
        buildLevel();

    }

    private synchronized void prepareStage() {
        prepareStartingObjects();
        state = PLAY;
        powtorzPoziom = false;
        menuActive = false;
        time = LocalDateTime.now();
        keyboard.releaseAllKeys();
    }

    private void prepareStartingObjects() {
        gameplay.removeAll();
        List<GameObject> objects = new ArrayList<>();
        objects.addAll(factory.produceBalls(5, 30));
        objects.add(new Raquet(keyboard,mediator));
      //  objects.add(factory.buildTestSpecial());
        gameplay.addObjects(objects);

    }

    private synchronized void buildLevel() {
        gameplay.addObjects(factory.buildBoard(levelNumber));

    }


    public synchronized void buildNextLevel() {

        gameplay.addObjects(factory.buildBoard(++levelNumber));
    }


    private void levelWon() {
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("TIME IN LEVEL " + time.until(LocalDateTime.now(), ChronoUnit.SECONDS));
        menuActive = true;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                int answer = JOptionPane.showConfirmDialog(null, "Poziom zakończony w czasie:" + time.until(LocalDateTime.now(), ChronoUnit.SECONDS) + " sekund \nCzy chcesz zagrać w kolejny level?");
                if (answer == JOptionPane.OK_OPTION) {
                    LevelService.this.prepareStage();
                    LevelService.this.buildNextLevel();

                } else {
                    System.exit(0);
                }
            }
        });

    }

    private void levelLoose() {
        menuActive = true;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int answer = JOptionPane.showConfirmDialog(null, "Przegrałeś!\nCzy chcesz powótrzyć poziom " + levelNumber + " ?");
                if (answer == JOptionPane.OK_OPTION) {
                    LevelService.this.prepareStage();
                    LevelService.this.buildLevel();
                } else {
                    System.exit(0);
                }
            }
        });

    }

    public boolean isLevelLost() {

        return gameplay.countObjectsByType(Type.BALL) == 0;
    }

    public boolean isLevelWon() {

        return gameplay.countObjectsByType(Type.BRICK) == 0;
    }


}

enum State {

    WIN,
    LOOSE,
    PLAY;


}
package game.engine;

import game.model.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import static game.engine.State.*;

public class LevelService {

    private Gameplay gameplay;
    private KeyboardManager keyboard;
    private ObjectFactory factory;
    private Mediator mediator;

    private State state;
    private boolean menuActive;
    private LocalDateTime time;
    private int levelNumber = 4;

    public LevelService(Gameplay gameplay, KeyboardManager keyboard) {
        this.gameplay = gameplay;
        this.keyboard = keyboard;
        mediator = new Mediator(gameplay);
        factory = new ObjectFactory(mediator);
        state = PLAY;

        preparePreLevel();
    }

    public void preparePreLevel() {
        prepareStage();
        buildLevel();
    }

    private synchronized void buildLevel() {
        gameplay.addObjects(factory.buildBoard(levelNumber));
    }

    private void prepareStage() { // was synchronized
        prepareStartingObjects();
        prepareStartingState();
    }

    private void prepareStartingObjects() {
        gameplay.removeAll();
        gameplay.addObjects(factory.produceBallsAndRaquet(keyboard));

    }

    private void prepareStartingState() {
        state = PLAY;
        menuActive = false;
        time = LocalDateTime.now();
        keyboard.releaseAllKeys();
    }

    public void tick() {
        handleLevelEnding();
    }

    private void handleLevelEnding() {
        updateEndState();
        if (isGameEnded()) {
            endLevel();
        }
    }

    private void updateEndState() {
        if (isLevelLost()) {
            state = LOOSE;
        } else if (isLevelWon()) {
            state = WIN;
        }
    }

    private boolean isLevelLost() {
        return gameplay.countObjectsByType(Type.BALL) == 0;
    }

    private boolean isLevelWon() {
        return gameplay.countObjectsByType(Type.BRICK) == 0;
    }

    private boolean isGameEnded() {
        return !menuActive && state!= PLAY;
    }

    private void endLevel() {
        switch (state) {
            case WIN:
                levelWon();
                break;
            case LOOSE:
                levelLoose();
                break;
        }
    }

    private void levelWon() {
        System.out.println("TIME IN LEVEL " + time.until(LocalDateTime.now(), ChronoUnit.SECONDS));
        menuActive = true;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                int answer = JOptionPane.showConfirmDialog(null, "Poziom zakończony w czasie: " + time.until(LocalDateTime.now(), ChronoUnit.SECONDS) + " sekund \nCzy chcesz zagrać w kolejny level?");
                if (answer == JOptionPane.OK_OPTION) {
                    LevelService.this.prepareStage();
                    LevelService.this.buildNextLevel();

                } else {
                    System.exit(0);
                }
            }
        });

    }

    private synchronized void buildNextLevel() {
        gameplay.addObjects(factory.buildBoard(++levelNumber));
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

    public boolean isPlaying() {
        return state != PLAY;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

enum State {
    WIN,
    LOOSE,
    PLAY;
}
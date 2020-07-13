package game.engine;

import game.model.*;
import game.model.player.Player;
import game.model.ui.ScoreCounter;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static game.engine.State.*;

public class LevelService {

    private Gameplay gameplay;
    private KeyboardManager keyboard;
    private ObjectFactory factory;
    private Mediator mediator;
    private Player player;
    private ScoreCounter scoreCounter;

    private State state;
    private boolean menuActive;
    private LocalDateTime time;
    private int levelNumber = 2;

    public LevelService(Gameplay gameplay, KeyboardManager keyboard) {
        this.gameplay = gameplay;
        this.keyboard = keyboard;
        player = new Player();
        scoreCounter = new ScoreCounter(player);
        mediator = new Mediator(gameplay,player);
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

    public void render(Graphics graphics){
        scoreCounter.render(graphics);
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
        return !menuActive && state != PLAY;
    }

    private void endLevel() {
        switch (state) {
            case WIN:
                levelWon();
                break;
            case LOOSE:
                menuActive = true;
                saveScores();
                levelRepeat();
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
                    player.growLastLevelScore();

                } else {
                    System.exit(0);
                }
            }
        });

    }

    private synchronized void buildNextLevel() {
        gameplay.addObjects(factory.buildBoard(++levelNumber));
    }

    private void saveScores() {

        String name = JOptionPane.showInputDialog("Otrzymałeś: "+ player.getScores() + " punktów. Wpisz swoje imię, aby dopisac Cię do rankingu:");
        if (name != null && !name.isEmpty()) {
            player.setName(name);
        }

    }


    private void levelRepeat() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int answer = JOptionPane.showConfirmDialog(null, "Przegrałeś!\nCzy chcesz powótrzyć poziom " + levelNumber + " ?");
                if (answer == JOptionPane.OK_OPTION) {
                    LevelService.this.prepareStage();
                    LevelService.this.buildLevel();
                    player.reverseToLastLevelScore();
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
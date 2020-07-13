package game.model;

import game.model.brick.BrickStandard;
import view.GameView;
import game.engine.KeyboardManager;
import game.engine.Mediator;
import game.model.ball.Ball;
import game.model.brick.Brick;
import game.model.brick.BrickSolid;
import game.model.brick.BrickUnbreakable;
import game.model.raquet.Raquet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//istnieje więcej niż jeden object factory

public class ObjectFactory {

    private static final int BALLS_INITIAL_QUANTITY = 3;

    private List<Color> colorList = new ArrayList<>();
    private Mediator mediator;
    public ObjectFactory(Mediator mediator) {
        this.mediator = mediator;
    }


    public List<Brick> buildBoard(int levelNumber) {

        switch (levelNumber) {
            case 0:
                return buildTestLevel();
            case 1:
                return buildLevelNr1();
            case 2:
                return buildLevelNr2();
            case 3:
                return buildLevelNr3();
            case 4:
                return  buildLevelNr4();
            default:
                return buildTestLevel();

        }

    }

    private List<Brick> buildTestLevel() {
        List<Brick> board = new ArrayList<>();
        board.add(new BrickStandard(GameView.WIDTH / 2, 100, chooseRandomColor(), mediator));
        return board;

    }

    private List<Brick> buildLevelNr1() {
        colorList.add(Color.ORANGE);
        colorList.add(Color.RED);
        List<Brick> board = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            board.addAll(buildRegularRow(i, 1, 19));
        }
        colorList.clear();
        return board;
    }

    private List<Brick> buildLevelNr2() {
        colorList.add(Color.BLUE);
        colorList.add(Color.WHITE);

        List<Brick> board = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            board.addAll(buildRegularRow(i, i, 19 - i));
        }
        for (int i = 10; i > 1; i--) {
            board.addAll(buildRegularTriangleRow(i, i, 1+i));
        }


        colorList.clear();
        return board;
    }

    private List<Brick> buildLevelNr3() {
        List<Brick> board = new ArrayList<>();
        colorList.add(Color.MAGENTA);
        colorList.add(Color.GREEN);
        board.addAll(buildRowOfSolidBricks(1,1,19));

        for (int i = 2; i < 6; i++) {
            board.addAll(buildRegularRow(i, 1, 19));
        }
        board.addAll(buildUnregularRow(6,1,19));
        colorList.clear();
        return board;
    }

    private List<Brick> buildLevelNr4() {
        List<Brick> board = new ArrayList<>();
        colorList.add(Color.MAGENTA);
        colorList.add(Color.GREEN);
        board.addAll(buildRowOfSolidBricks(1,1,19));

        for (int i = 2; i < 6; i++) {
            board.addAll(buildRegularRow(i, 1, 19));
        }
        board.addAll(buildRowOfUnbreakableBricks(6,1,19));

        colorList.clear();
        return board;
    }


    private List<Brick> buildRegularRow(int numberOfRow, int firstBrick, int lastBrick) {
        List<Brick> row = new ArrayList<>();
        for (int i = firstBrick; i < lastBrick; i++) {
            row.add(new BrickStandard(i * Brick.DEFAULT_WIDTH, Brick.DEFAULT_HEIGHT * numberOfRow,
                    chooseColorFromList(), mediator));
        }
        return row;
    }

    private List<Brick> buildRegularTriangleRow(int numberOfRow, int firstBrick, int lastBrick) {
        List<Brick> row = new ArrayList<>();
       //for(int j = )
        //TODO tutaj skończyła się edycja
        for (int i = numberOfRow; i < lastBrick; i++) {
            row.add(new BrickStandard(i * Brick.DEFAULT_WIDTH, Brick.DEFAULT_HEIGHT * numberOfRow,
                    chooseColorFromList(), mediator));
        }
        return row;
    }


    private List<Brick> buildUnregularRow(int numberOfRow, int firstBrick, int lastBrick) {
        List<Brick> row = new ArrayList<>();
        for (int i = firstBrick; i < lastBrick;i++) {
            row.add(new BrickStandard(i * Brick.DEFAULT_WIDTH, Brick.DEFAULT_HEIGHT * numberOfRow,
                    chooseColorFromList(), mediator));
            i++;
            row.add(new BrickSolid(i*Brick.DEFAULT_WIDTH,Brick.DEFAULT_HEIGHT*numberOfRow,mediator));
        }
        return row;
    }

    private List<Brick> buildRowOfSolidBricks(int numberOfRow, int firstBrick, int lastBrick) {
        List<Brick> row = new ArrayList<>();
        for (int i = firstBrick; i < lastBrick; i++) {
            row.add(new BrickSolid(i * Brick.DEFAULT_WIDTH, Brick.DEFAULT_HEIGHT * numberOfRow,
                     mediator));
        }
        return row;
    }

    private List<Brick> buildRowOfUnbreakableBricks(int numberOfRow, int firstBrick, int lastBrick) {

        List<Brick> row = new ArrayList<>();
        for (int i = firstBrick; i < lastBrick; i++) {
            row.add(new BrickUnbreakable(i * Brick.DEFAULT_WIDTH, Brick.DEFAULT_HEIGHT * numberOfRow,
                    mediator));

        }
        return row;
    }

    public List<GameObject> produceBallsAndRaquet(KeyboardManager keyboardManager) {
        List<GameObject> objects = new ArrayList<>(produceBalls(BALLS_INITIAL_QUANTITY));
        objects.add(new Raquet(keyboardManager, mediator));
        return objects;

    }

    private List<Ball> produceBalls(int howManyBalls) {
        List<Ball> balls = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < howManyBalls; i++) {
            balls.add(new Ball(random.nextInt(GameView.WIDTH), 20, Color.BLACK, mediator));
        }
        return balls;
    }

   public List<Ball> produceBalls(int howManyBalls, Ball ball) {
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < howManyBalls; i++) {
            balls.add(new Ball(ball.getX(), ball.getY(), Color.BLACK, mediator));
        }
        return balls;
    }

    private Color chooseColorFromList() {
        Random random = new Random();
        return colorList.get(random.nextInt(colorList.size()));
    }

    private Color chooseRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

    }

}

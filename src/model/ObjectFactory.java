package model;

import engine.Display;
import engine.Mediator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//istnieje więcej niż jeden object factory

public class ObjectFactory {
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
            default:
                return buildTestLevel();

        }

    }

    public List<Brick> buildTestLevel() {
        List<Brick> board = new ArrayList<>();
        board.add(new Brick(Display.getWidth() / 2, 100, chooseRandomColor(), mediator));
        return board;

    }

    public List<Brick> buildLevelNr1() {
        colorList.add(Color.ORANGE);
        colorList.add(Color.RED);

        List<Brick> board = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            board.addAll(buildRow(i, 1, 9));

        }
        colorList.clear();
        return board;
    }

    public List<Brick> buildLevelNr2() {
        colorList.add(Color.BLUE);
        colorList.add(Color.WHITE);

        List<Brick> board = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            board.addAll(buildRow(i, i, 10 - i));

        }
        colorList.clear();
        return board;
    }

    public List<Brick> buildLevelNr3() {
        List<Brick> board = new ArrayList<>();
        colorList.add(Color.MAGENTA);
        colorList.add(Color.CYAN);
        for (int i = 0; i < 7; i++) {
            board.addAll(buildRow(i, i, 10 - i));

        }
        colorList.clear();
        return board;
    }


    private List<Brick> buildRow(int numberOfRow, int firstBrick, int lastBrick) {
        List<Brick> row = new ArrayList<>();
        for (int i = firstBrick; i < lastBrick; i++) {
            row.add(new Brick(i * Brick.DEFAULT_WIDTH, Brick.DEFAULT_HEIGHT * numberOfRow,
                    chooseColorFromList(), mediator));
        }
        return row;
    }

    public java.util.List<Ball> produceBalls(int howManyBalls) {
        List<Ball> balls = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < howManyBalls; i++) {

            balls.add(new Ball(random.nextInt(Display.getWidth()), 20, Color.BLACK, mediator));
        }
        return balls;
    }

   public java.util.List<Ball> produceBalls(int howManyBalls, Ball ball) {
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

    /*public Special buildTestSpecial() {

        return new SpeedBallSpecial(400, 400, mediator);
    }*/
}

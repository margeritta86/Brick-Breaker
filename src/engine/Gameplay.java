package engine;

import model.Ball;
import model.Brick;
import model.Raquet;
import model.RectFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Gameplay {

    private List<Ball> balls;
    private List<Brick> bricks;
    private Raquet raquet;
    private KeyboardManager keyboard;

    public Gameplay(KeyboardManager keyboard) {
        this.keyboard = keyboard;
        balls = produceBalls(5, 30);
        RectFactory factory = new RectFactory();
        bricks = factory.buildBoard();
        buildRaquet();
    }

    private List<Ball> produceBalls(int howManyBalls, int size) {
        List<Ball> balls = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < howManyBalls; i++) {

            balls.add(new Ball(random.nextInt(Display.getWidth()), 20, size, Color.BLACK));
        }
        return balls;
    }

    private void buildRaquet() {
        raquet = new Raquet(Color.BLUE, keyboard);
    }

    public void tick() {
        for (Ball ball : balls) {
            ball.tick();
            ball.checkColisionWithRect(raquet);
            for (Brick brick : bricks) {
                ball.checkColisionWithRect(brick);

            }
        }

        for (Brick brick : bricks) {
            brick.tick();

        }
        keyboard.tick();
        raquet.tick();

    }


    public void render(Graphics graphics) {
        for (Ball ball : balls) {
            ball.render(graphics);
        }

        for (Brick brick : bricks) {
            brick.render(graphics);
        }
        raquet.render(graphics);
    }


}

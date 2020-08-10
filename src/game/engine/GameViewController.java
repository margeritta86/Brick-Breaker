package game.engine;


import controller.Controller;
import game.model.ImageLoader;
import view.GameView;
import view.ViewFactory;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameViewController extends Controller implements Runnable {

    private GameView gameView;
    private KeyboardManager keyboard;
    private Thread thread;
    private boolean running;
    private BufferStrategy strategy;



    private Gameplay gameplay;


    public GameViewController(GameView gameView, ViewFactory viewFactory) {
        super(viewFactory);
        running = false;
        this.gameView = gameView;
        keyboard = new KeyboardManager();
        this.gameView.addKeyManager(keyboard);
        gameplay = new Gameplay(keyboard);
        start();

    }

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1_000_000_000) {
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    private void tick() {
        gameplay.tick();
    }

    private void render() {
        if (!ensureBufferReady()) {
            return;
        }
        renderFrame();



    }

    private boolean ensureBufferReady() {
        Canvas canvas = gameView.getCanvas();
        strategy = canvas.getBufferStrategy();

        if (strategy == null) {
            canvas.createBufferStrategy(3);
            return false;
        }
        return true;
    }

    private void renderFrame() {
        
        Graphics graphics = strategy.getDrawGraphics();
        graphics.clearRect(0, 0, GameView.WIDTH, GameView.HEIGHT);        
        gameplay.render(graphics);
        strategy.show();
        graphics.dispose();
    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

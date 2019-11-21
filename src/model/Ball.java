package model;

import engine.Display;

import java.awt.*;
import java.util.Random;

public class Ball implements GameObject {

    private Random random = new Random();

    private int x;
    private int y;
    private int size;
    private Color color;
    private double speedX = random.nextDouble()*5+3;
    private double speedY =  random.nextDouble()*5+3;


    public Ball(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    @Override
    public void tick() {
        if (speedY >= -0.5 && speedY <=0.5) {
            speedY = 1;
        }

        if (speedX >= -0.5 && speedX <=0.5) {
            speedX = 1;
        }
        checkColision();
        x += Math.round( speedX);
        y += Math.round(speedY) ;
    }

    private void checkColision() {
        if (x + size >= Display.getWidth()) {
            speedX = -speedX;
            x = Display.getWidth() - size;
        } else if (x <= 0) {
            speedX = -speedX;
            x = 0;
        } else if (y + size >= Display.getHeight()) {
            speedY = -speedY;
            y = Display.getHeight() - size;
        } else if (y <= 0) {
            speedY = -speedY;
            y = 0;
        }

    }

    public boolean checkColisionWithRect(Rect rect) {

        if (!rect.isAcitve()) {
            return false;
        }
        boolean collision = true;
        boolean isInYRange = y + size / 2 >= rect.getY() && y + size / 2 <= rect.getY() + rect.getHeight();
        boolean isinXRange = x + size / 2 >= rect.getX() && x + size / 2 <= rect.getX() + rect.getWidth();

        if (x + size >= rect.getX() && x + size < rect.getX() + rect.getWidth() / 2 && isInYRange) {//LEWA
            speedX = -speedX;
            x = rect.getX() - size;
            rect.reactToCollision();
        } else if (x <= rect.getX() + rect.getWidth() && x > rect.getX() + rect.getWidth() / 2 && isInYRange) {//PRAWA
            speedX = -speedX;
            x = rect.getX() + rect.getWidth();
            rect.reactToCollision();
        } else if (y + size >= rect.getY() && y < rect.getY() + rect.getHeight() / 2 && isinXRange) {//GÓRA
            if (rect.getCollisionType() == CollisionType.RAQUET) {
               // speedY = -speedY;

                reactToRaquet(rect);
            } else {
                speedY = -speedY;
                y = rect.getY() - size;
                rect.reactToCollision();
            }
        } else if (y <= rect.getY() + rect.getHeight() && y > rect.getY() + rect.getHeight() / 2 && isinXRange) {//DÓŁ
            speedY = -speedY;
            y = rect.getY() + rect.getHeight();
            rect.reactToCollision();
        } else {
            collision = false;
        }
        return collision;
    }


    public void reactToRaquet(Rect rect) {
        int ballCenter = x + size / 2;
        int ballHit = ballCenter - rect.x;

        //getWidth == 100%
        // kat prosty w prawo
        // X  5
        // Y  5

        // kat rozwarty
        // X  8
        // Y  2

         //0%
        //kat ostry   -> lewo
        // X  -10   //suma wszystki + ustanie ile idzie dla X a reszta dla Y
        // Y  0

        //25%
        //kat ostry   -> lewo
        // X  -5
        // Y  5

        //50%
        // pilka odbita pionowo  -> środek
        // X  0
        // Y  10

        //75%
        //kat ostry   -> prawo
        // X  5
        // Y  5

        //100%
        //kat ostry   -> lewo
        // X  10
        // Y  0



        // kat prosty w lewo
        // X  -5
        // Y  5

        // kat rozwarty
        // X  -8
        // Y  2

        //kat ostry
        // X  -2
        // Y  8




        double result = (double) ballHit/ rect.width;
        boolean right = false;
        if (result > 0.5) {
            result -= 0.5;
            right = true;
        }
        result *= 2;

        System.out.println(result * 100 + "%");
        if (right) {
            System.out.println("right");
            speedX = Math.abs(speedX);
            System.out.println(speedX);
            System.out.println(speedY);
            double totalSpeed = speedX + speedY;
            System.out.println("total : " + totalSpeed);
            speedX = result * totalSpeed;
            speedY = totalSpeed - speedX;
            System.out.println("new x" + speedX);
            System.out.println("new y" + speedY);
            speedX = Math.abs(speedX);
            System.out.println("-----");
        } else {
            System.out.println("left");
            speedX = Math.abs(speedX);
            System.out.println(speedX);
            System.out.println(speedY);
            double totalSpeed = speedX + speedY;
            System.out.println("total : " + totalSpeed);
            speedY = result * totalSpeed;
            speedX = totalSpeed - speedY;
            System.out.println("new x" + speedX);
            System.out.println("new y" + speedY);
            speedX = -speedX;

            System.out.println("-----");
        }
        speedY = -speedY;
        y = rect.getY() - size;


        //0.4 -> 0.8
        //0.6 -> 0.2
        // 0.0 - 1.0



    }


    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(x, y, size, size);
    }


}

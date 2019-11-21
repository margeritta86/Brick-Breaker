package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RectFactory {



    public List<Brick> buildBoard() {
        List<Brick>board = new ArrayList<>();

        for (int i = 1; i <6 ; i++) {
             board.addAll(buildRow(i,1,9));

        }
       return board;

    }


    private List<Brick> buildRow(int numberOfRow, int firstBrick, int lastBrick) {
        List<Brick> row = new ArrayList<>();
        for (int i = firstBrick; i < lastBrick; i++) {
            row.add(new Brick(i * Brick.DEFAULT_WIDTH, Brick.DEFAULT_HEIGHT * numberOfRow,
                    Brick.DEFAULT_WIDTH, Brick.DEFAULT_HEIGHT, chooseRandomColor()));

        }
        return row;

    }

    private Color chooseRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

    }
}

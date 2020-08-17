package game.engine;

import game.model.ControllerStatus;
import game.model.raquet.Raquet;

import javax.swing.*;
import java.awt.*;

public class MouseManager implements ControllerStatus {
    private Canvas canvas;
    private int lastMouseXPosition;
    private int actualDistance = 0;

    public MouseManager(Canvas canvas) {
        this.canvas = canvas;
        lastMouseXPosition = Raquet.DEFAULT_X;

    }

    @Override
    public int getHorizontalMovement() {
        return actualDistance;
    }

    @Override
    public boolean isActionPressed() {
        return false;
    }


    public void tick() {
        int newMouseX = getMouseXPixel();
        if(newMouseX !=lastMouseXPosition){
            actualDistance = lastMouseXPosition - newMouseX;
            lastMouseXPosition = newMouseX;
        }else{
            actualDistance = 0;
        }
    }

    private int getMouseXPixel() {
        PointerInfo info = MouseInfo.getPointerInfo();
        Point location = info.getLocation();
        SwingUtilities.convertPointFromScreen(location, canvas);
        return location.x;
    }
}

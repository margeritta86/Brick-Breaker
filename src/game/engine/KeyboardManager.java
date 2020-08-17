package game.engine;

import game.model.ControllerStatus;
import game.model.raquet.Raquet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener, ControllerStatus {


    private boolean[] keys = new boolean[1000];

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void releaseAllKeys() {
        keys = new boolean[1000];
    }

    @Override
    public int getHorizontalMovement() {
        if (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]) {
            return -Raquet.SPEED_X;
        } else if (keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]) {
            return Raquet.SPEED_X;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isActionPressed() {
        return keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}

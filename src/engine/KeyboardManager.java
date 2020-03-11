package engine;

import model.ControllerStatus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener, ControllerStatus {

    private boolean[] keys = new boolean[1000];

    public void tick() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void releaseAllKeys(){
        keys = new boolean[1000];
    }

    @Override
    public boolean isLeftPressed() {
        return keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
    }

    @Override
    public boolean isRIghtPressed() {
        return keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
    }

    @Override
    public boolean isSpacePressed() { return keys[KeyEvent.VK_SPACE]; }

}

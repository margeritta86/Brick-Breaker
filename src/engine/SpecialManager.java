package engine;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecialManager implements ActionListener {

    private List<Special> activatedSpecials;
    private Gameplay gameplay;
    private Mediator mediator;
    private Timer timer;

    public SpecialManager(Gameplay gameplay, Mediator mediator) {
        this.gameplay = gameplay;
        this.mediator = mediator;
        activatedSpecials = new ArrayList<>();
        timer = new Timer(1000, this);
        timer.start();
    }

    public void spawnSpecial(Brick brick) {
        SpecialFactory specialFactory = new SpecialFactory();
        Optional<Special> special = specialFactory.produceSpecial(brick, mediator);
        if (special.isPresent()) {
            gameplay.addObject(special.get());
        }
    }

    private void executeSpecial(Special special) {
        for (GameObject gameObject : gameplay.getObjects()) {
            gameObject.accept(special);
        }
    }

    private void updateActivatedSpecial(Special special) {
        special.activated();
        activatedSpecials.add(special);
    }

    public void executeRaquetCollision(Special special) {
        for (GameObject gameObject : gameplay.getObjects()) {
            if (gameObject.getType() == Type.SPECIAL) { // jeśli sam ze sobą - pomiń
                continue;
            }
            if (gameObject.getType() == Type.RAQUET && gameObject.isIntersecting(special)) {
                executeSpecial(special);  // jeśli rakietka i kolizja to aktywacja
                updateActivatedSpecial(special);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Special> toRemove = new ArrayList<>();
        for (Special activatedSpecial : activatedSpecials) {
            activatedSpecial.increase();
            if (activatedSpecial.isTimeToReverse()) {
                executeSpecial(activatedSpecial);
                toRemove.add(activatedSpecial);
            }
        }
        activatedSpecials.removeAll(toRemove);


    }

}

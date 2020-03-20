package game.engine;

import game.model.*;
import game.model.brick.Brick;
import game.model.special.Special;
import game.model.special.SpecialFactory;

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
        SpecialFactory specialFactory = new SpecialFactory(mediator);
        Optional<Special> special = specialFactory.produceSpecial(brick);
        if (special.isPresent()) {
            gameplay.addObject(special.get());
        }
    }

    private void executeSpecial(Special special) {
        special.execute();
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
                executeNewSpecial(special);
            }
        }
    }

    private void executeNewSpecial(Special special) {
        if (activatedSpecials.contains(special)) {
            doubleTimeSpecial(special);
            special.activated();
            return;
        }
        executeSpecial(special);
        updateActivatedSpecial(special);
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

    private void doubleTimeSpecial(Special special) {
        for (Special activatedSpecial : activatedSpecials) {
            if (special.equals(activatedSpecial)){
                activatedSpecial.doubleTime();
            }

        }

    }

}

//w tej chwili dodajemy czas do speciali i jeśli minie ich duration to są usuwane przez ponowne wywołanie execute które
//dostosowuje sie do tego czy ten special był juz wczesniej wywoałny

//zamiast tego chcemy scenariusz: stary special ma sie wylaczyc ale nowy aktywowany gdy już wcześniej aktywny był ten sam tego typu
// powinien być aktywowany osobna metoda execute again która zadecyude czy dodajemy dodatkowy (hand ball) czas czy wywolujemy ponownie efekt (add balls)

//ewentualnie zerowy czas dla tych które maja sie ponownie aktywowac zamiast zwiekszac czasu
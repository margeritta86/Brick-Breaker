package engine;

import model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Gameplay {

    private List<GameObject> objects;
    private KeyboardManager keyboard;
    private LevelService levelService;


    public Gameplay(KeyboardManager keyboard) {
        objects = new CopyOnWriteArrayList<>();
        this.keyboard = keyboard;
        this.levelService = new LevelService(this, keyboard);
        levelService.preparePreLevel();
    }

    public void tick() {
        levelService.tick();
        if (levelService.stopPlaying()) return;
        keyboard.tick();

        for (GameObject object : objects) {
            object.tick();
        }
        removeObjects();
    }

    public void render(Graphics graphics) {

        for (GameObject object : objects) {
            object.render(graphics);
        }
    }

    public void removeObjects() {
        List<GameObject> skopiowaneObiekty = new ArrayList<>(objects);
        for (GameObject object : skopiowaneObiekty) {
            if (!object.isInGame()) {
                objects.remove(object);
            }
        }
    }

    public void addObjects(List<? extends GameObject> objects) {
        this.objects.addAll(objects);
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public int countObjectsByType(Type type) {
        int licznik = 0;
        for (GameObject object : objects) {
            if (object.getType().equals(type)) {
                licznik++;
            }
        }
        return licznik;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public void removeAll() {
        objects.clear();
    }
}

package game.engine;

import game.model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Gameplay {

    private List<GameObject> objects;
    private LevelService levelService;


    public Gameplay(KeyboardManager keyboard) {
        objects = new CopyOnWriteArrayList<>();
        this.levelService = new LevelService(this, keyboard);
    }

    public void tick() {
        levelService.tick();

        if (levelService.isPlaying()) return;

        for (GameObject object : objects) {
            object.tick();
        }
        removeOffGameObjects();
    }

    private void removeOffGameObjects() {
        List<GameObject> copied = new ArrayList<>(objects);
        for (GameObject object : copied) {
            if (object.isNotInGame()) {
                objects.remove(object);
            }
        }
    }

    public void render(Graphics graphics) {
        for (GameObject object : objects) {
            object.render(graphics);
        }
        levelService.render(graphics);
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

    public LevelService getLevelService() {
        return levelService;
    }
}

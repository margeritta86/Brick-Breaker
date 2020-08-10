package game.engine;

import java.io.*;
import javax.sound.sampled.*;

public enum SoundEffect {

    LEVEL_WON("src/game/resources/sounds/levelWon.wav"),
    BRICK_BREAKER("src/game/resources/sounds/short16speed.wav"),
    PLOOP("src/game/resources/sounds/short16ploop.wav"),
    MUSIC("src/game/resources/sounds/sample.wav");

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;
    private Clip clip;


     SoundEffect(String soundFileName) {
        try {
            File file = new File(soundFileName);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
     }

    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void playInLoop(){
         clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    // Optional static method to pre-load all the sound files.
    static void init() {
        values(); // calls the constructor for all the elements
    }
}

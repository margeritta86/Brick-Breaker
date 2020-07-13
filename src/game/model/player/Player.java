package game.model.player;

import java.util.Objects;

public class Player {

    private String name;
    private int scores;
    private int lastLevelScore;

    public Player() {
        name = "";

    }

    public void reverseToLastLevelScore(){
        scores = lastLevelScore;
    }

    public void incrementScore() {
        scores++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void growLastLevelScore(){
        lastLevelScore = scores;
    }

    public int getScores() {
        return scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return scores == player.scores &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scores);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }


}

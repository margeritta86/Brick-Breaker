package repository;

import game.model.player.Player;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class RankingRepository {
    
    private static final String FILE_PATH = "ranking.bin"; 

    private List<Player> players;
    private int rankingNumber;


    public RankingRepository() {
        players = new ArrayList<>();
        load();
        System.out.println(players);
    }

    public void save(Player player) {
        players.add(player);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            oos.writeObject(players);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            players = (List<Player>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }


    }
    public List<Player> getSortedRanking(){
        Map<Integer, Player> sortedMap = new HashMap<>();
        List<Player> sortedList = new ArrayList<>(players);
        Collections.sort(sortedList);
        return sortedList.stream().limit(10).collect(Collectors.toList());
       
    }
    
}

package view;

import game.model.player.Player;
import repository.RankingRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class RankingView extends View {

    private JPanel rankingPanel;
    private JList<Player> rankingList;
   

    public RankingView() throws HeadlessException {
        super("RANKING", ViewType.RANKING);
        rankingPanel = new JPanel();
        rankingPanel.setBackground(Color.red);
        add(rankingPanel);
        prepareList();
    }

    private void prepareList() {

        RankingRepository rankingRepository = new RankingRepository();
        DefaultListModel<Player> rankingModel = new DefaultListModel<>();
        rankingModel.addAll(rankingRepository.getSortedRanking());
        rankingList = new JList<>(rankingModel);
        JScrollPane jScrollPane = new JScrollPane(rankingList);
        jScrollPane.setPreferredSize(new Dimension(200,300));
        rankingPanel.add(jScrollPane);
        pack();
        rankingList.setVisibleRowCount(2);

    }
}

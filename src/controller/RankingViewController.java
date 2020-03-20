package controller;

import view.RankingView;
import view.ViewFactory;

public class RankingViewController extends Controller{

    private RankingView rankingView;

    public RankingViewController( RankingView rankingView, ViewFactory viewFactory) {
        super(viewFactory);
        this.rankingView = rankingView;
    }
}

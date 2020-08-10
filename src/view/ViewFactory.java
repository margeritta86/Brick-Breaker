package view;

import controller.MenuController;
import controller.RankingViewController;
import game.engine.GameViewController;

public class ViewFactory {

    public void showView(ViewType viewType) {

        switch (viewType) {

            case MENU:
                showMenu();
                break;
            case RANKING:
                showRanking();
                break;
            case GAME:
                showGame();
                break;
        }
    }

    private void showMenu() {
        MenuView menu = new MenuView();
        MenuController menuController = new MenuController(menu,this);
    }

    private void showRanking() {
        RankingView rankingView = new RankingView();
        RankingViewController rankingViewController = new RankingViewController(rankingView,this);
    }

    private  void showGame(){
        GameView gameView = new GameView();
        GameViewController gameViewController = new GameViewController(gameView,this);


    }

}

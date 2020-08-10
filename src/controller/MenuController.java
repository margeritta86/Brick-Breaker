package controller;

import view.MenuView;
import view.ViewFactory;
import view.ViewType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController extends Controller{

    private MenuView menuView;

    public MenuController(MenuView menuView, ViewFactory viewFactory) {
        super( viewFactory);
        this.menuView = menuView;
        addButtonActions();
    }

    private void addButtonActions(){

        menuView.addStartAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAction();
            }
        });

        menuView.addRankingAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rankingAction();
            }
        });

        menuView.addExitAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitAction();
            }
        });

    }
    
    private void startAction() {
        viewFactory.showView(ViewType.GAME);

    }

    private void exitAction(){
        System.exit(0);

    }

    private void rankingAction(){
        viewFactory.showView(ViewType.RANKING);

    }
}

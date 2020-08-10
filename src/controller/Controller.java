package controller;

import view.View;
import view.ViewFactory;

public abstract class Controller {

    ViewFactory viewFactory;

    public Controller(ViewFactory viewFactory) {

        this.viewFactory = viewFactory;
    }
}

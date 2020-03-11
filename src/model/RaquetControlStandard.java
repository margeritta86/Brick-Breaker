package model;

public class RaquetControlStandard implements RaquetControlExecution {

    private Raquet raquet;

    public RaquetControlStandard(Raquet raquet) {
        this.raquet = raquet;
    }

    @Override
    public void leftAction() {
        raquet.x -= raquet.speedX;
    }

    @Override
    public void rightAction() {
        raquet.x += raquet.speedX;
    }

    @Override
    public void spaceAction() {

    }
}

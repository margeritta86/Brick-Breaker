package game.model.raquet;

public class RaquetControlStandard implements RaquetControlExecution {

    private Raquet raquet;

    public RaquetControlStandard(Raquet raquet) {
        this.raquet = raquet;
    }

    @Override
    public void leftAction() {
        raquet.setX((int) (raquet.getX() - raquet.getSpeedX()));
    } 

    @Override
    public void rightAction() {
        raquet.setX((int) (raquet.getX() + raquet.getSpeedX()));
    }

    @Override
    public void spaceAction() {

    }
}

package game.model.raquet;

public class RaquetControlStandard implements RaquetControlExecution {

    private Raquet raquet;

    public RaquetControlStandard(Raquet raquet) {
        this.raquet = raquet;
    }

    @Override
    public void horizontalAction() {
        raquet.setX((int) (raquet.getX() + raquet.getSpeedX()));
    }

    @Override
    public void spaceAction() {

    }
}

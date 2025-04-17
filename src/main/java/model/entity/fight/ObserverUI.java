package model.entity.fight;

public class ObserverUI extends Observer{

    private State state;

    @Override
    public void update(State state) {
        state=state;
    }

    @Override
    public State getState() {
        return state;
    }
}

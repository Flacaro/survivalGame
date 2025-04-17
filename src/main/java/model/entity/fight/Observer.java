package model.entity.fight;

public abstract class Observer {
    public abstract void update(State state);
    public abstract State getState();
}

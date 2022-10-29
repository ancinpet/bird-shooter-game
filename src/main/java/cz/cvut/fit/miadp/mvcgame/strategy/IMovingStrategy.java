package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;

public interface IMovingStrategy {
    public abstract int getAngle();
    public abstract void setAngle(int angle);

    void updatePosition(AbsMissile mis);
    IMovingStrategy makeCopy();
}

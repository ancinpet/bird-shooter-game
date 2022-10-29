package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsMissile extends GameObject {
    public abstract IMovingStrategy getMoveStrat();    
    public abstract AbsMissile makeCopy();

    public void accept(IVisitor visitor) {
        visitor.visitMissile(this);
    }
    public abstract void move();
}
package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject {
    public abstract AbsEnemy makeCopy();

    public abstract int getType();
    public void accept(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
    public abstract void move();
}
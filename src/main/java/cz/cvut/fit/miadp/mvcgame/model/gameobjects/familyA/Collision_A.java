package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCollision;;

public class Collision_A extends AbsCollision {
    public Collision_A(Position pos) {
        pos.copyInto(this.pos);
    }

    @Override
    public AbsCollision makeCopy() {
        return new Collision_A(this.pos);
    }
}
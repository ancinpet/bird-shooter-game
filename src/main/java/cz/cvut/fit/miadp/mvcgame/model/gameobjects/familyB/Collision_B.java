package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCollision;;

public class Collision_B extends AbsCollision {
    public Collision_B(Position pos) {
        pos.copyInto(this.pos);
    }

    @Override
    public AbsCollision makeCopy() {
        return new Collision_B(this.pos);
    }
}
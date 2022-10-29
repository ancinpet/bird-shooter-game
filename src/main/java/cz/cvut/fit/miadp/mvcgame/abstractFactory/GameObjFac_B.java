package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB.*;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMoveStrategy;

public class GameObjFac_B implements IGameObjFac {

    @Override
    public AbsCannon createCannon(AbsModelInfo modelInfo) {
        return new Cannon_B(this, modelInfo);
    }

    @Override
    public AbsCollision createCollision(Position pos) {
        return new Collision_B(pos);
    }

    @Override
    public AbsEnemy createEnemy(AbsModelInfo modelInfo) {
        return new Enemy_B(modelInfo);
    }

    @Override
    public AbsMissile createMissile(AbsModelInfo modelInfo) {
        return new Missile_B(new RealisticMoveStrategy(modelInfo));
    }

    @Override
    public AbsModelInfo createModelInfo() {
        return new ModelInfo_B();
    }
}
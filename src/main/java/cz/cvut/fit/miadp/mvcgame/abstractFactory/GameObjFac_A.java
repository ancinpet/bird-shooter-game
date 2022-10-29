package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.*;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMoveStrategy;

public class GameObjFac_A implements IGameObjFac {

    @Override
    public AbsCannon createCannon(AbsModelInfo modelInfo) {
        return new Cannon_A(this, modelInfo);
    }

    @Override
    public AbsCollision createCollision(Position pos) {
        return new Collision_A(pos);
    }

    @Override
    public AbsEnemy createEnemy(AbsModelInfo modelInfo) {
        return new Enemy_A(modelInfo);
    }

    @Override
    public AbsMissile createMissile(AbsModelInfo modelInfo) {
        return new Missile_A(new SimpleMoveStrategy(modelInfo));
    }

    @Override
    public AbsModelInfo createModelInfo() {
        return new ModelInfo_A();
    }
}
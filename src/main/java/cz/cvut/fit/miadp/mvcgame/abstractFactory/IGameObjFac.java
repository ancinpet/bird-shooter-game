package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public interface IGameObjFac {
    AbsCannon createCannon(AbsModelInfo modelInfo);
    AbsCollision createCollision(Position pos);
    AbsEnemy createEnemy(AbsModelInfo modelInfo);
    AbsMissile createMissile(AbsModelInfo modelInfo);
    AbsModelInfo createModelInfo();
}
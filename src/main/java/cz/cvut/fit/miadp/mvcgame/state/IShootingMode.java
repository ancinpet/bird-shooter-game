package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public interface IShootingMode {
    void shoot(AbsCannon cannon);
    void toggle(AbsCannon cannon, AbsModelInfo modelInfo);
}
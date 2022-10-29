package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public class DoubleShootingMode implements IShootingMode {
    @Override
    public void shoot(AbsCannon cannon) {
        cannon.doubleShoot();
    }

    @Override
    public void toggle(AbsCannon cannon, AbsModelInfo modelInfo) {
        cannon.setSingleShootingMode();
    }
}
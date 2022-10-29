package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public class SingleShootingMode implements IShootingMode {
    @Override
    public void shoot(AbsCannon cannon) {
        cannon.singleShoot();
    }

    @Override
    public void toggle(AbsCannon cannon, AbsModelInfo modelInfo) {
        cannon.setDoubleShootingMode();
    }
}
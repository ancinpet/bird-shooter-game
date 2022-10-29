package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public class Missile_A extends AbsMissile {
    public IMovingStrategy moveStrat;

    public Missile_A(IMovingStrategy moveStrat) {
        this.setWidth(MvcGameConfig.MISSILE_WIDTH);
        this.setHeight(MvcGameConfig.MISSILE_HEIGHT);
        this.moveStrat = moveStrat.makeCopy();
    }

    @Override
    public AbsMissile makeCopy() {
        Missile_A tmp = new Missile_A(this.moveStrat);
        this.copyPosInto(tmp);
        return tmp;
    }

    @Override
    public void move() {
        this.moveStrat.updatePosition(this);
    }

    @Override
    public IMovingStrategy getMoveStrat() {
        return this.moveStrat;
    }
}
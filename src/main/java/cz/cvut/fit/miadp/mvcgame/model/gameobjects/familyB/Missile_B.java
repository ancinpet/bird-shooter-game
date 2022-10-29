package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public class Missile_B extends AbsMissile {
    public IMovingStrategy moveStrat;

    public Missile_B(IMovingStrategy moveStrat) {
        this.setWidth(MvcGameConfig.MISSILE_WIDTH);
        this.setHeight(MvcGameConfig.MISSILE_HEIGHT);
        this.moveStrat = moveStrat.makeCopy();
    }

    @Override
    public AbsMissile makeCopy() {
        Missile_B tmp = new Missile_B(this.moveStrat);
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
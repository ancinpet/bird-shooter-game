package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsModelInfo;

public class SimpleMoveStrategy implements IMovingStrategy {
    public int angle;

    public SimpleMoveStrategy(AbsModelInfo modelInfo) {
        this.angle = modelInfo.getAngleCalc();
    }

    public SimpleMoveStrategy(SimpleMoveStrategy strat) {
        this.angle = strat.angle;
    }

	@Override
    public void updatePosition(AbsMissile mis) {
        double dx = MvcGameConfig.MOVE_STEP * Math.cos(degreeToRad(angle));
        double dy = MvcGameConfig.MOVE_STEP * Math.sin(degreeToRad(angle));
        //Move in a straight line at angle
        mis.move((int)dx, (int)dy);
    }

    @Override
    public SimpleMoveStrategy makeCopy() {
        return new SimpleMoveStrategy(this);
    }

    public double degreeToRad(int angle) {
        return angle * Math.PI / 180.0;
    }

    @Override
    public int getAngle() {
        return this.angle;
    }

    @Override
    public void setAngle(int angle) {
        this.angle = angle;
    }
}
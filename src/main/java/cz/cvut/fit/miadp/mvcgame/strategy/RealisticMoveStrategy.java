package cz.cvut.fit.miadp.mvcgame.strategy;

import java.util.Random;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsModelInfo;

public class RealisticMoveStrategy implements IMovingStrategy {
    private int force;
    private int gravity;
    private int angle;
    private double time;
    private int spreadX;
    private int spreadY;

    public RealisticMoveStrategy(AbsModelInfo modelInfo) {
        this.force = modelInfo.getForceCalc();
        this.gravity = modelInfo.getGravityCalc();
        this.angle = modelInfo.getAngleCalc();
        this.time = 0;
        this.spreadX = new Random().nextInt(2);
        this.spreadY = new Random().nextInt(2);
    }

    public RealisticMoveStrategy(RealisticMoveStrategy strat) {
        this.force = strat.force;
        this.gravity = strat.gravity;
        this.angle = strat.angle;
        this.time = strat.time;
        this.spreadX = strat.spreadX;
        this.spreadY = strat.spreadY;
    }

    @Override
    public void updatePosition(AbsMissile mis) {
        double dx = MvcGameConfig.MOVE_STEP * Math.cos(degreeToRad(angle));
        double dy = MvcGameConfig.MOVE_STEP * Math.sin(degreeToRad(angle));
        //Move in a straight line at angle
        mis.move((int)dx, (int)dy);
        //Very mathematically correct way of adding gravity and force, at least it is original... original game mechanics are the way to succeed
        mis.move((int)(dx * (force / 100.0)), (int)(Math.abs(MvcGameConfig.MOVE_STEP * 1.1 + dy) * (Math.log10(120 - force)) * (gravity / 300.0) * getTime()));
        //"Spread" is a common game mechanic
        mis.move(spreadX, spreadY);
    }

    @Override
    public RealisticMoveStrategy makeCopy() {
        return new RealisticMoveStrategy(this);
    }

    public double degreeToRad(int angle) {
        return angle * Math.PI / 180.0;
    }

    public double getTime() {
        time += 0.3;
        return time;
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
package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjFac;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsModelInfo;
import cz.cvut.fit.miadp.mvcgame.state.*;

public class Cannon_B extends AbsCannon {
    private long lastShot;
    private long lastModeSwitch;

    private IGameObjFac goFact;
    private IShootingMode SINGLE_SHOOTING_MODE;
    private IShootingMode DOUBLE_SHOOTING_MODE;
    private List<AbsMissile> shootBatch;
    private IShootingMode mode;
    private AbsModelInfo modelInfo;

    public Cannon_B(IGameObjFac goFact, AbsModelInfo modelInfo) {
        this.modelInfo = modelInfo;

        this.goFact = goFact;
        this.SINGLE_SHOOTING_MODE = new SingleShootingMode();
        this.DOUBLE_SHOOTING_MODE = new DoubleShootingMode();
        //this.REALISTIC_SHOOTING_MODE = new RealisticShootingMode();
        this.setLastShot();
        this.setLastSwitch();
        this.setSingleShootingMode();
        this.setX(MvcGameConfig.CANNON_INIT_X);
        this.setY((int) (MvcGameConfig.MAX_Y / 2));
        this.setWidth(MvcGameConfig.CANNON_WIDTH);
        this.setHeight(MvcGameConfig.CANNON_HEIGHT);
    }

    @Override
    public AbsCannon makeCopy(AbsModelInfo infoReference) {
        Cannon_B tmp = new Cannon_B(this.getGOFact(), infoReference);
        //Shallow
        tmp.mode = this.getShootingMode();
        //Deep
        this.copyPosInto(tmp);

        return tmp;
    }

    @Override
    public IGameObjFac getGOFact() {
        return this.goFact;
    }

    @Override
    public IShootingMode getShootingMode() {
        return this.mode;
    }

    public void aimUp() {
        this.modelInfo.setAngle(this.modelInfo.getAngle() + 1);
    }

    public void aimDown() {
        this.modelInfo.setAngle(this.modelInfo.getAngle() - 1);
    }

    public void incPower() {
        this.modelInfo.setForce(this.modelInfo.getForce() + 1);
    }

    public void decPower() {
        this.modelInfo.setForce(this.modelInfo.getForce() - 1);
    }

    @Override
    public void moveUp() {
        this.move(0, -1 * MvcGameConfig.CANNON_MOVE_STEP);
    }

    @Override
    public void moveDown() {
        this.move(0, MvcGameConfig.CANNON_MOVE_STEP);
    }

    @Override
    public void moveLeft() {
        this.move(-1 * MvcGameConfig.CANNON_MOVE_STEP, 0);
    }

    @Override
    public void moveRight() {
        this.move(1 * MvcGameConfig.CANNON_MOVE_STEP, 0);
    }

    @Override
    public List<AbsMissile> shoot() {
        this.shootBatch = new ArrayList<AbsMissile>();
        if (getLastShot() > MvcGameConfig.CANNON_SHOOT_DELAY) {
            this.mode.shoot(this);
            this.setLastShot();
        }
        return this.shootBatch;
    }

    public AbsMissile singleShoot() {
        AbsMissile m = this.goFact.createMissile(this.modelInfo);
        m.setX(this.getX());
        m.setY(this.getY());

        this.shootBatch.add(m);
        return m;
    }

    @Override
    public AbsMissile doubleShoot() {
        AbsMissile a = this.goFact.createMissile(this.modelInfo);
        a.setX(this.getX());
        a.setY(this.getY());
        this.shootBatch.add(a);

        AbsMissile b = this.goFact.createMissile(this.modelInfo);
        b.setX(this.getX());
        b.setY(this.getY());
        this.shootBatch.add(b);
        b.getMoveStrat().setAngle(b.getMoveStrat().getAngle() - MvcGameConfig.CANNON_DOUBLE_REALISTIC_ANGLE);

        return b;
    }

    @Override
    public void toggleShootingMode() {
        if (getLastSwitch() > MvcGameConfig.CANNON_SWITCH_DELAY) {
            this.mode.toggle(this, modelInfo);
            this.setLastSwitch();
        }
    }

    @Override
    public void setDoubleShootingMode() {
        this.mode = this.DOUBLE_SHOOTING_MODE;
        this.modelInfo.setShootingMode("Double");
    }

    @Override
    public void setSingleShootingMode() {
        this.mode = this.SINGLE_SHOOTING_MODE;
        this.modelInfo.setShootingMode("Single");
    }

    public long getLastSwitch() {
        return System.currentTimeMillis() - this.lastModeSwitch;
    }

    public void setLastSwitch() {
        this.lastModeSwitch = System.currentTimeMillis();
    }

    public long getLastShot() {
        return System.currentTimeMillis() - this.lastShot;
    }

    public void setLastShot() {
        this.lastShot = System.currentTimeMillis();
    }
    
    // Uses movement clamping to make sure cannon never leaves the window.
    @Override
    public void move(int dx, int dy) {
        int newDx = dx + (this.modelInfo.getLevel() * (dx > 0 ? 1 : -1));
        int newDy = dy + (this.modelInfo.getLevel() * (dy > 0 ? 1 : -1));
        if (dx != 0 && this.getX() + newDx > MvcGameConfig.MIN_X && this.getX() + this.getWidth() + newDx < MvcGameConfig.MAX_X) {
            this.pos.move(newDx, 0);
        }
        if (dy != 0 && this.getY() + newDy > MvcGameConfig.MIN_Y && this.getY() + this.getHeight() + newDy < MvcGameConfig.MAX_Y) {
            this.pos.move(0, newDy);
        }
    }
}
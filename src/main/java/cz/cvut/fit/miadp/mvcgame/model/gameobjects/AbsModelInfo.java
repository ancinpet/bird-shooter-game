package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsModelInfo extends GameObject {
    public abstract AbsModelInfo makeCopy();
    
    public abstract int getForce();
    public abstract int getForceCalc();
    public abstract int getAngle();
    public abstract int getAngleCalc();
    public abstract int getGravity();
    public abstract int getGravityCalc();
    public abstract int getScore();
    public abstract void incScore();
    public abstract void setForce(int force);
    public abstract void setAngle(int angle);
    public abstract void setGravity(int gravity);
    public abstract void setScore(int gravity);
    public abstract void setShootingMode(String mode);
    public abstract String getText();
    public abstract int getLevel();
    public abstract void incLevel();
    public abstract void decHealth();
    public abstract int getHealth();
    
    public void accept(IVisitor visitor) {
        visitor.visitGameInfo(this);
    }
}
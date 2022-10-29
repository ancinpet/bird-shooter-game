package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import java.util.List;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjFac;
import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsCannon extends GameObject {	
	public abstract AbsCannon makeCopy(AbsModelInfo infoReference);
	public abstract IGameObjFac getGOFact();
	public abstract IShootingMode getShootingMode();

    public abstract void aimUp();
	public abstract void aimDown();
    public abstract void incPower();
    public abstract void decPower();

    public void accept(IVisitor visitor) {
        visitor.visitCannon(this);
    }
    
	public abstract void moveUp();
	public abstract void moveDown();
	public abstract void moveLeft();
	public abstract void moveRight();
    public abstract List<AbsMissile> shoot();
    
    public abstract void toggleShootingMode();
	public abstract void setDoubleShootingMode();
	public abstract void setSingleShootingMode();
	public abstract AbsMissile singleShoot();
	public abstract AbsMissile doubleShoot();
	public abstract void setLastShot();
}
package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class GameObject {
    protected Position pos = new Position(0, 0);
    protected Position area = new Position(20, 20);

    public Position getPosition() {
        return new Position(this.pos);
    }

    public int getX() {
        return this.pos.getX();
    }

    public int getY() {
        return this.pos.getY();
    }

    public void setX(int x) {
        this.pos.setX(x);
    }

    public void setY(int y) {
        this.pos.setY(y);
    }

    public int getWidth() {
        return this.area.getX();
    }

    public int getHeight() {
        return this.area.getY();
    }

    public void setWidth(int x) {
        this.area.setX(x);
    }

    public void setHeight(int y) {
        this.area.setY(y);
    }
    
    public void move(int dx, int dy) {
        this.pos.move(dx, dy);
    }
    
    public void absMove(int dx, int dy) {
        this.pos.absMove(dx, dy);
    }

    public boolean isOutOfBounds() {
        return this.getX() - this.getWidth() > MvcGameConfig.MAX_X ||
               this.getX() + this.getWidth() < MvcGameConfig.MIN_X || 
               this.getY() - this.getHeight() > MvcGameConfig.MAX_Y ||
               this.getY() + this.getHeight() + MvcGameConfig.SKYBOX < MvcGameConfig.MIN_Y;
    }

    public boolean collidesWith(GameObject go) {
        if (this.getX() > (go.getX() + go.getWidth()) || go.getX() > (this.getX() + this.getWidth())) {
            return false; 
        }
        if (this.getY() > (go.getY() + go.getHeight()) || go.getY() > (this.getY() + this.getHeight())) {
            return false; 
        }
        return true; 
    }

    public void copyPosInto(GameObject go) {
        this.pos.copyInto(go.pos);
        this.area.copyInto(go.area);
    }    

    public abstract void accept(IVisitor visitor);
}

package cz.cvut.fit.miadp.mvcgame.proxy;

import cz.cvut.fit.miadp.mvcgame.command.AbsCommand;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class GameModelProxy implements IGameModel {
    private IGameModel subject;

    public GameModelProxy(IGameModel subject) {
        this.subject = subject;
    }

    @Override
    public void moveCannonUp() {
        this.subject.moveCannonUp();

    }

    @Override
    public void moveCannonDown() {
        this.subject.moveCannonDown();

    }

    @Override
    public void moveCannonLeft() {
        this.subject.moveCannonLeft();
    }

    @Override
    public void moveCannonRight() {
        this.subject.moveCannonRight();
    }

    @Override
    public void cannonShoot() {
        this.subject.cannonShoot();

    }

    @Override
    public void toggleShootingMode() {
        this.subject.toggleShootingMode();

    }

    @Override
    public void registerCommand(AbsCommand cmd) {
        this.subject.registerCommand(cmd);

    }

    @Override
    public Object createMemento() {
        return this.subject.createMemento();
    }

    @Override
    public void setMemento(Object memento) {
        this.subject.setMemento(memento);

    }

    @Override
    public void undoLastCmd() {
        this.subject.undoLastCmd();
    }

    @Override
    public void increaseAngle() {
        this.subject.increaseAngle();
    }

    @Override
    public void decreaseAngle() {
        this.subject.decreaseAngle();
    }

    @Override
    public void increaseGravity() {
        this.subject.increaseGravity();
    }

    @Override
    public void decreaseGravity() {
        this.subject.decreaseGravity();
    }

    @Override
    public void increasePower() {
        this.subject.increasePower();
    }

    @Override
    public void decreasePower() {
        this.subject.decreasePower();
    }

}
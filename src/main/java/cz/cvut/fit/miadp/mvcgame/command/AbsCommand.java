package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public abstract class AbsCommand {
    private Object memento;
    protected IGameModel receiver;

    public AbsCommand(IGameModel receiver) {
        this.receiver = receiver;
    }

    //Template method pattern
    public void doExecute() {
        this.memento = this.receiver.createMemento();
        this.execute();
    }

    protected abstract void execute();

    public void unexecute() {
        this.receiver.setMemento(this.memento);
    }
}
package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

//Necessary because command queue is blocking -> calling undo from other thread blocks the undo command -> lags application
public class UndoCommand extends AbsCommand {
    public UndoCommand(IGameModel receiver) {
        super(receiver);
    }

    //Undoes itself -> unexecute gets called
    @Override
    public void execute() {
        this.receiver.undoLastCmd();
    }
    
    //Undoes the previous command
    @Override
    public void unexecute() {
        this.receiver.undoLastCmd();
    }
}
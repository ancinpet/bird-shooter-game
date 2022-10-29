package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class MementoCopyCommand extends AbsCommand {
    public MementoCopyCommand(IGameModel receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        //Do nothing, only for saving memento, when used, undo needs to run twice
        //to undo this (time) and then undo actual commands
        //Experimental feature set through config UNDO_TIME_BASED
    }
}
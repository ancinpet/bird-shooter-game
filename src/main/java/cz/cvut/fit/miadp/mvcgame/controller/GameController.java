package cz.cvut.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.command.*;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class GameController {
    private IGameModel model;

    public GameController(IGameModel model) {
        this.model = model;
    }

    //Uses commands to communicate with model
    public void handleKeyCode(String keyCode) {
        switch (keyCode) {
            case MvcGameConfig.CONTROLS_FORWARD:
                this.model.registerCommand(new MoveCannonUpCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_BACKWARD:
                this.model.registerCommand(new MoveCannonDownCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_LEFT:
                this.model.registerCommand(new MoveCannonLeftCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_RIGHT:
                this.model.registerCommand(new MoveCannonRightCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_SHOOT:
                this.model.registerCommand(new ShootCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_MODE:
                this.model.registerCommand(new ModeToggleCommand(this.model));
                break;
            //Undo is also a command because commandQueue is blocking -> makes undo smooth
            case MvcGameConfig.CONTROLS_UNDO:
                //Needs to run twice if time based undo is used to undo the time then undo the action
                if (MvcGameConfig.UNDO_TIME_BASED) {
                    this.model.registerCommand(new UndoCommand(this.model));
                }
                this.model.registerCommand(new UndoCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_ANGLE_UP:
                this.model.registerCommand(new IncreaseAngleCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_ANGLE_DOWN:
                this.model.registerCommand(new DecreaseAngleCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_GRAVITY_UP:
                this.model.registerCommand(new IncreaseGravityCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_GRAVITY_DOWN:
                this.model.registerCommand(new DecreaseGravityCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_POWER_UP:
                this.model.registerCommand(new IncreasePowerCommand(this.model));
                break;
            case MvcGameConfig.CONTROLS_POWER_DOWN:
                this.model.registerCommand(new DecreasePowerCommand(this.model));
                break;
            default:
                //nothing
        }
    }
}

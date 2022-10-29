package cz.cvut.fit.miadp;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cz.cvut.fit.miadp.mvcgame.command.*;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public class CommandTestSuite {
    @Test
    public void angleCommand() {
        GameModel model = new GameModel();
        AbsModelInfo minfo = null;
        List<GameObject> gos = model.getGameObjects();
        for (GameObject go: gos) {
            if (go instanceof AbsModelInfo) {
                minfo = (AbsModelInfo)go;
            }
        }
        Assert.assertTrue(minfo.getAngle() == 20);
        new DecreaseAngleCommand(model).doExecute();
        Assert.assertTrue(minfo.getAngle() == 19);
        for (int i = 0; i < 360; ++i) {
            new DecreaseAngleCommand(model).doExecute();
        }
        Assert.assertTrue(minfo.getAngle() == 19);
        new IncreaseAngleCommand(model).doExecute();
        Assert.assertTrue(minfo.getAngle() == 20);
        new IncreaseAngleCommand(model).doExecute();
        Assert.assertTrue(minfo.getAngle() == 21);
    }

    @Test
    public void powerCommand() {
        GameModel model = new GameModel();
        AbsModelInfo minfo = null;
        List<GameObject> gos = model.getGameObjects();
        for (GameObject go: gos) {
            if (go instanceof AbsModelInfo) {
                minfo = (AbsModelInfo)go;
            }
        }
        Assert.assertTrue(minfo.getForce() == 100);
        new IncreasePowerCommand(model).doExecute();
        Assert.assertTrue(minfo.getForce() == 100);
        new IncreasePowerCommand(model).doExecute();
        Assert.assertTrue(minfo.getForce() == 100);
        new DecreasePowerCommand(model).doExecute();
        Assert.assertTrue(minfo.getForce() == 99);
        for (int i = 0; i < 50; ++i) {
            new DecreasePowerCommand(model).doExecute();
        }
        Assert.assertTrue(minfo.getForce() == 99-50);
        for (int i = 0; i < 20; ++i) {
            new IncreasePowerCommand(model).doExecute();
        }
        Assert.assertTrue(minfo.getForce() == 99-50+20);
    }

    @Test
    public void gravityCommand() {
        GameModel model = new GameModel();
        AbsModelInfo minfo = null;
        List<GameObject> gos = model.getGameObjects();
        for (GameObject go: gos) {
            if (go instanceof AbsModelInfo) {
                minfo = (AbsModelInfo)go;
            }
        }
        Assert.assertTrue(minfo.getGravity() == 50);
        new IncreaseGravityCommand(model).doExecute();
        Assert.assertTrue(minfo.getGravity() == 51);
        new IncreaseGravityCommand(model).doExecute();
        Assert.assertTrue(minfo.getGravity() == 52);
        new DecreaseGravityCommand(model).doExecute();
        Assert.assertTrue(minfo.getGravity() == 51);
        for (int i = 0; i < 50; ++i) {
            new DecreaseGravityCommand(model).doExecute();
        }
        Assert.assertTrue(minfo.getGravity() == 51-50);
        for (int i = 0; i < 20; ++i) {
            new IncreaseGravityCommand(model).doExecute();
        }
        Assert.assertTrue(minfo.getGravity() == 51-50+20);
    }

    @Test
    public void commandQueue() {
        GameModel model = new GameModel();
        int extraCmd = MvcGameConfig.UNDO_TIME_BASED ? 1 : 0;

        Assert.assertTrue(model.unexecutedCmds.size() == 0);
        Assert.assertTrue(model.executedCmds.size() == 0);
        model.registerCommand(new DecreaseGravityCommand(model));
        Assert.assertTrue(model.executedCmds.size() == 0);
        Assert.assertTrue(model.unexecutedCmds.size() == 1);
        model.registerCommand(new DecreaseGravityCommand(model));
        Assert.assertTrue(model.executedCmds.size() == 0);
        Assert.assertTrue(model.unexecutedCmds.size() == 2);
        model.timeTick();
        Assert.assertTrue(model.executedCmds.size() == 2);
        Assert.assertTrue(model.unexecutedCmds.size() == 0 + extraCmd);
        model.timeTick();
        Assert.assertTrue(model.executedCmds.size() == 2 + extraCmd);
        Assert.assertTrue(model.unexecutedCmds.size() == 0 + extraCmd);
        model.undoLastCmd();
        Assert.assertTrue(model.executedCmds.size() == 1 + extraCmd);
        Assert.assertTrue(model.unexecutedCmds.size() == 0 + extraCmd);
        model.undoLastCmd();
        Assert.assertTrue(model.executedCmds.size() == 0 + extraCmd);
        Assert.assertTrue(model.unexecutedCmds.size() == 0 + extraCmd);
        model.undoLastCmd();
        Assert.assertTrue(model.executedCmds.size() == 0);
        Assert.assertTrue(model.unexecutedCmds.size() == 0 + extraCmd);
    }

    @Test
    public void undoCommand() {
        GameModel model = new GameModel();
        int extraCmd = MvcGameConfig.UNDO_TIME_BASED ? 1 : 0;
        int extraCmdCompl = MvcGameConfig.UNDO_TIME_BASED ? 0 : 1;

        Assert.assertTrue(model.unexecutedCmds.size() == 0);
        Assert.assertTrue(model.executedCmds.size() == 0);
        model.registerCommand(new DecreaseGravityCommand(model));
        Assert.assertTrue(model.executedCmds.size() == 0);
        Assert.assertTrue(model.unexecutedCmds.size() == 1);
        model.registerCommand(new DecreaseGravityCommand(model));
        Assert.assertTrue(model.executedCmds.size() == 0);
        Assert.assertTrue(model.unexecutedCmds.size() == 2);
        model.timeTick();
        Assert.assertTrue(model.executedCmds.size() == 2);
        Assert.assertTrue(model.unexecutedCmds.size() == 0 + extraCmd);
        model.timeTick();
        Assert.assertTrue(model.executedCmds.size() == 2 + extraCmd);
        Assert.assertTrue(model.unexecutedCmds.size() == 0 + extraCmd);
        model.registerCommand(new UndoCommand(model));
        Assert.assertTrue(model.executedCmds.size() == 2 + extraCmd);
        Assert.assertTrue(model.unexecutedCmds.size() == 1 + extraCmd);
        model.registerCommand(new UndoCommand(model));
        Assert.assertTrue(model.executedCmds.size() == 2 + extraCmd);
        Assert.assertTrue(model.unexecutedCmds.size() == 2 + extraCmd);
        model.timeTick();
        Assert.assertTrue(model.executedCmds.size() == 2 - extraCmdCompl + extraCmd);
        Assert.assertTrue(model.unexecutedCmds.size() == 0 + extraCmd);
    }
}
package cz.cvut.fit.miadp.mvcgame;

import java.util.List;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.view.GameView;

public class MvcGame
{
    GameController controller;
    GameModel model;
    GameView view;

    public void init()
    {
        this.model = new GameModel();
        this.view = new GameView(this.model);
        this.controller = this.view.makeController();
    }

    public void processPressedKeys(List<String> pressedKeysCodes)
    {
        for (String code: pressedKeysCodes) {
            this.controller.handleKeyCode(code);
        }
    }

    public void update()
    {
        this.model.timeTick();
    }

    public void render(IGameGraphics gr)
    {
        this.view.setGraphics(gr);
        this.view.render();
    }

    public String getWindowTitle()
    {
        return "THE GAME";
    }

    public int getWindowWidth()
    {
        return MvcGameConfig.MAX_X;
    }

    public int getWindowHeight()
    {
        return  MvcGameConfig.MAX_Y;
    }
}
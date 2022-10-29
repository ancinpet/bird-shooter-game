package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.observer.*;
import cz.cvut.fit.miadp.mvcgame.proxy.GameModelProxy;
import cz.cvut.fit.miadp.mvcgame.model.*;
import cz.cvut.fit.miadp.mvcgame.visitor.*;

public class GameView implements IObserver {
    private GameModel model;
    private IVisitor renderingVisitor;
    private int updateCnt = 1;
    private IGameGraphics gr;

    public GameView(GameModel model) {
        this.model = model;

        this.model.registerObserver(this);

        this.renderingVisitor = new RenderingVisitor();
    }

    public void setGraphics(IGameGraphics gr) {
        this.gr = gr;
        this.renderingVisitor.setGraphics(gr);
    }

    public GameController makeController() {
        return new GameController(new GameModelProxy(model));
    }

	public void update() {
        this.updateCnt++;
    }

    public void render() {
        if (this.updateCnt > 0) {
            this.gr.clear();

            for (GameObject go: this.model.getGameObjects()) {
                go.accept(this.renderingVisitor);
            }
        }
        
        this.updateCnt = 0; 
    }
}

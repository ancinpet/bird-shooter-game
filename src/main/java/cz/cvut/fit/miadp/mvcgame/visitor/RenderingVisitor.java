package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public class RenderingVisitor implements IVisitor {
    private IGameGraphics gr;

    public RenderingVisitor() {}

    @Override
    public void setGraphics(IGameGraphics gr) {
        this.gr = gr;
    }

    @Override
    public void visitCannon(AbsCannon go) {
        this.drawCannon(go);
    }

    private void drawCannon(AbsCannon go) {
        if (this.gr == null) return;
        
        this.gr.drawImage("images/cannon.png", go.getPosition());
    }

    @Override
    public void visitEnemy(AbsEnemy go) {
        if (this.gr == null) return;
        
        this.gr.drawImage("images/enemy" + go.getType() + ".png", go.getPosition());
    }

    @Override
    public void visitMissile(AbsMissile go) {
        if (this.gr == null) return;
        
        this.gr.drawImage("images/missile.png", go.getPosition());
    }

    @Override
    public void visitGameInfo(AbsModelInfo go) {
        if (this.gr == null) return;

        this.gr.drawText(go.getText(), go.getPosition());
    }

    @Override
    public void visitCollisiton(AbsCollision go) {
        if (this.gr == null) return;
        
        this.gr.drawImage("images/collision.png", go.getPosition());
    }
}

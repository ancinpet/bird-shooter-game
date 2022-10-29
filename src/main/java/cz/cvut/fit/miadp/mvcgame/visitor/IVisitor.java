package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;

public interface IVisitor {
    void visitCannon(AbsCannon go);
    void visitEnemy(AbsEnemy go);
    void visitMissile(AbsMissile go);
    void visitGameInfo(AbsModelInfo go);
    void visitCollisiton(AbsCollision go);
    
	void setGraphics(IGameGraphics gr);
}

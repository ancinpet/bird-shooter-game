package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB;

import java.util.Random;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsModelInfo;

public class Enemy_B extends AbsEnemy {
    private AbsModelInfo modelInfo;
    private int type;
    private int moveCount;

    public Enemy_B(AbsModelInfo modelInfo) {
        this.modelInfo = modelInfo;
        this.moveCount = 0;
        this.setX(MvcGameConfig.MAX_X - 50);
        this.setY(new Random().nextInt(MvcGameConfig.MAX_Y - 100) + 50);
        this.setWidth(MvcGameConfig.ENEMY_WIDTH);
        this.setHeight(MvcGameConfig.ENEMY_HEIGHT);
        this.type = new Random().nextInt(2) + 1;
    }

    @Override
    public AbsEnemy makeCopy() {
        Enemy_B tmp = new Enemy_B(this.modelInfo);
        this.copyPosInto(tmp);
        tmp.type = this.type;
        
        return tmp;
    }

    @Override
    public void move() {
        ++this.moveCount;
        this.moveCount %= MvcGameConfig.ENEMY_REALISTIC_MOVE_ONE_DIR_TIME;
        int dy = new Random().nextInt(5) * (this.moveCount >= (MvcGameConfig.ENEMY_REALISTIC_MOVE_ONE_DIR_TIME / 2) ? 1 : -1);
        if (this.getY() + dy > MvcGameConfig.MIN_Y && this.getY() + this.getHeight() + dy < MvcGameConfig.MAX_Y) {
            this.move(this.modelInfo.getLevel() * -2, dy);
        }
    }

    @Override
    public int getType() {
        return type;
    }
}
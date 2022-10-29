package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA;

import java.util.Random;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsModelInfo;

public class Enemy_A extends AbsEnemy {
    private int type;
    private AbsModelInfo modelInfo;

    public Enemy_A(AbsModelInfo modelInfo) {
        this.modelInfo = modelInfo;
        this.setX(new Random().nextInt(MvcGameConfig.MAX_X - 100) + 50);
        this.setY(new Random().nextInt(MvcGameConfig.MAX_Y - 100) + 50);
        this.setWidth(MvcGameConfig.ENEMY_WIDTH);
        this.setHeight(MvcGameConfig.ENEMY_HEIGHT);
        this.type = new Random().nextInt(2) + 1;
    }

    @Override
    public AbsEnemy makeCopy() {
        Enemy_A tmp = new Enemy_A(this.modelInfo);
        this.copyPosInto(tmp);
        tmp.type = this.type;
        
        return tmp;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void move() {
        //Do not move
    }
}
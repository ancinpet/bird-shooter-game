package cz.cvut.fit.miadp;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.*;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyA.*;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB.*;
import cz.cvut.fit.miadp.mvcgame.state.*;
import cz.cvut.fit.miadp.mvcgame.strategy.*;

public class CannonTestSuite {
    @Test
    public void moveTest() {
        AbsModelInfo modelInfo = mock(ModelInfo_A.class);
        AbsCannon cannon = new Cannon_B(mock(GameObjFac_A.class), modelInfo);
        Position pos = new Position(MvcGameConfig.CANNON_INIT_X, MvcGameConfig.MAX_Y / 2);
        Assert.assertEquals(cannon.getPosition(), pos);
        cannon.move(10, 15);
        pos.setX(pos.getX()+10);
        pos.setY(pos.getY()+15);
        Assert.assertEquals(cannon.getPosition(), pos);
        //Move near game border
        cannon.absMove(MvcGameConfig.MIN_X + 35, MvcGameConfig.MAX_Y - 100);
        pos.setX(MvcGameConfig.MIN_X + 35);
        pos.setY(MvcGameConfig.MAX_Y - 100);
        Assert.assertEquals(cannon.getPosition(), pos);
        Position border = new Position(pos);
        //Keep moving cannon towards border, hits the border at i = 6
        for (int i = 0; i < 20; ++i) {
            cannon.move(-5, 5);
            pos.setX(pos.getX()-5);
            pos.setY(pos.getY()+5);

            if (i == 5) {
                border = new Position(pos);
            }
            if (i >= 6) {
                Assert.assertEquals(cannon.getPosition(), border);
            } else {
                Assert.assertEquals(cannon.getPosition(), pos);
            }
        }
    }

    @Test
    public void cannonAimTestA() {
        AbsModelInfo modelInfo = new ModelInfo_A();
        AbsCannon cannon = new Cannon_B(mock(GameObjFac_A.class), modelInfo);
        int angle = modelInfo.getAngle();
        cannon.aimUp();
        Assert.assertTrue(modelInfo.getAngle() == angle + 1);
        cannon.aimUp();
        Assert.assertTrue(modelInfo.getAngle() == angle + 2);
        cannon.aimUp();
        Assert.assertTrue(modelInfo.getAngle() == angle + 3);
        cannon.aimDown();
        Assert.assertTrue(modelInfo.getAngle() == angle + 2);
        cannon.aimDown();
        Assert.assertTrue(modelInfo.getAngle() == angle + 1);
        cannon.aimDown();
        Assert.assertTrue(modelInfo.getAngle() == angle);

        for (int i = 0; i < 360; ++i) {
            cannon.aimUp();
        }
        Assert.assertTrue(modelInfo.getAngle() == angle);
        for (int i = 0; i < 360; ++i) {
            cannon.aimDown();
        }
        Assert.assertTrue(modelInfo.getAngle() == angle);
    }

    @Test
    public void modeSwitchTestA() throws InterruptedException {
        AbsModelInfo modelInfo = mock(ModelInfo_A.class);
        AbsCannon cannon = new Cannon_B(mock(GameObjFac_A.class), modelInfo);
        Assert.assertTrue(cannon.getShootingMode() instanceof SingleShootingMode);
        cannon.toggleShootingMode();
        Assert.assertTrue(cannon.getShootingMode() instanceof SingleShootingMode);
        Thread.sleep(MvcGameConfig.CANNON_SWITCH_DELAY + 100);
        cannon.toggleShootingMode();
        Assert.assertTrue(cannon.getShootingMode() instanceof DoubleShootingMode);
        cannon.toggleShootingMode();
        Assert.assertTrue(cannon.getShootingMode() instanceof DoubleShootingMode);
        Thread.sleep(MvcGameConfig.CANNON_SWITCH_DELAY + 100);
        cannon.toggleShootingMode();
        Assert.assertTrue(cannon.getShootingMode() instanceof SingleShootingMode);
    }

    @Test
    public void shootingTestA() throws InterruptedException {
        AbsModelInfo modelInfo = mock(ModelInfo_A.class);
        IGameObjFac factory = mock(GameObjFac_A.class);
        IMovingStrategy strat = mock(SimpleMoveStrategy.class);
        when(strat.makeCopy()).thenReturn(strat);
        AbsMissile m = new Missile_A(strat);
        when(factory.createMissile(modelInfo)).thenReturn(m);
        AbsCannon cannon = new Cannon_A(factory, modelInfo);
        List<AbsMissile> missilesA = cannon.shoot();
        Assert.assertTrue(missilesA.size() == 0);
        Thread.sleep(MvcGameConfig.CANNON_SHOOT_DELAY + 100);
        List<AbsMissile> missilesB = cannon.shoot();
        Assert.assertTrue(missilesB.size() == 1);
        cannon.toggleShootingMode();
        Thread.sleep(MvcGameConfig.CANNON_SHOOT_DELAY + 100);
        List<AbsMissile> missilesC = cannon.shoot();
        Assert.assertTrue(missilesC.size() == 2);
        List<AbsMissile> missilesD = cannon.shoot();
        Assert.assertTrue(missilesD.size() == 0);
        
    }

    @Test
    public void cannonAimTestB() {
        AbsModelInfo modelInfo = new ModelInfo_B();
        AbsCannon cannon = new Cannon_B(mock(GameObjFac_B.class), modelInfo);
        int angle = modelInfo.getAngle();
        cannon.aimUp();
        Assert.assertTrue(modelInfo.getAngle() == angle + 1);
        cannon.aimUp();
        Assert.assertTrue(modelInfo.getAngle() == angle + 2);
        cannon.aimUp();
        Assert.assertTrue(modelInfo.getAngle() == angle + 3);
        cannon.aimDown();
        Assert.assertTrue(modelInfo.getAngle() == angle + 2);
        cannon.aimDown();
        Assert.assertTrue(modelInfo.getAngle() == angle + 1);
        cannon.aimDown();
        Assert.assertTrue(modelInfo.getAngle() == angle);

        for (int i = 0; i < 360; ++i) {
            cannon.aimUp();
        }
        Assert.assertTrue(modelInfo.getAngle() == angle);
        for (int i = 0; i < 360; ++i) {
            cannon.aimDown();
        }
        Assert.assertTrue(modelInfo.getAngle() == angle);
    }

    @Test
    public void modeSwitchTestB() throws InterruptedException {
        AbsModelInfo modelInfo = mock(ModelInfo_B.class);
        AbsCannon cannon = new Cannon_B(mock(GameObjFac_B.class), modelInfo);
        Assert.assertTrue(cannon.getShootingMode() instanceof SingleShootingMode);
        cannon.toggleShootingMode();
        Assert.assertTrue(cannon.getShootingMode() instanceof SingleShootingMode);
        Thread.sleep(MvcGameConfig.CANNON_SWITCH_DELAY + 100);
        cannon.toggleShootingMode();
        Assert.assertTrue(cannon.getShootingMode() instanceof DoubleShootingMode);
        cannon.toggleShootingMode();
        Assert.assertTrue(cannon.getShootingMode() instanceof DoubleShootingMode);
        Thread.sleep(MvcGameConfig.CANNON_SWITCH_DELAY + 100);
        cannon.toggleShootingMode();
        Assert.assertTrue(cannon.getShootingMode() instanceof SingleShootingMode);
    }

    @Test
    public void shootingTestB() throws InterruptedException {
        AbsModelInfo modelInfo = mock(ModelInfo_B.class);
        IGameObjFac factory = mock(GameObjFac_B.class);
        IMovingStrategy strat = mock(SimpleMoveStrategy.class);
        when(strat.makeCopy()).thenReturn(strat);
        AbsMissile m = new Missile_A(strat);
        when(factory.createMissile(modelInfo)).thenReturn(m);
        AbsCannon cannon = new Cannon_B(factory, modelInfo);
        List<AbsMissile> missilesA = cannon.shoot();
        Assert.assertTrue(missilesA.size() == 0);
        Thread.sleep(MvcGameConfig.CANNON_SHOOT_DELAY + 100);
        List<AbsMissile> missilesB = cannon.shoot();
        Assert.assertTrue(missilesB.size() == 1);
        cannon.toggleShootingMode();
        Thread.sleep(MvcGameConfig.CANNON_SHOOT_DELAY + 100);
        List<AbsMissile> missilesC = cannon.shoot();
        Assert.assertTrue(missilesC.size() == 2);
        List<AbsMissile> missilesD = cannon.shoot();
        Assert.assertTrue(missilesD.size() == 0);
        
    }
}
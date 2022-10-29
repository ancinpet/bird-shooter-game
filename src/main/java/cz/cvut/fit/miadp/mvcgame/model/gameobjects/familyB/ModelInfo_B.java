package cz.cvut.fit.miadp.mvcgame.model.gameobjects.familyB;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.gameOver.GameOver;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsModelInfo;

public class ModelInfo_B extends AbsModelInfo {
    private int health;
    private int level;
    private int force;
    private int gravity;
    private int angle;
    private int score;
    public String shootingMode;

    public ModelInfo_B() {
        this.health = MvcGameConfig.GAME_HEALTH;
        this.level = 1;
        this.pos = new Position(MvcGameConfig.INFO_INIT_X, MvcGameConfig.INFO_INIT_Y);
        this.force = 100;
        this.gravity = 50;
        this.angle = 20;
        this.score = 0;
        this.shootingMode = "Single";
    }

    @Override
    public AbsModelInfo makeCopy() {
        ModelInfo_B tmp = new ModelInfo_B();
        tmp.health = this.health;
        tmp.level = this.level;
        tmp.force = this.force;
        tmp.gravity = this.gravity;
        tmp.angle = this.angle;
        tmp.score = this.score;
        tmp.shootingMode = this.shootingMode;

        return tmp;
    };

    //Display for realistic shooting shows force, angle and gravity. Angle is displayed as <-180, 180> for better user experience
    public String getText() {        
        if (this.health <= 0) {
            return new GameOver().gameOver(this.level * MvcGameConfig.SCORE_TO_LEVEL + this.score);
        }

        int angleDisp = angle > 180 ? angle - 360 : angle;
        String begining = "SCORE: " + score + ", LEVEL: " + this.level + ", HEALTH: " + this.health + ", Shooting mode: " + this.shootingMode + ", Angle: " + angleDisp + "°";
        if (this.shootingMode.equals("Double")) {
            begining += " <> " + (angleDisp + MvcGameConfig.CANNON_DOUBLE_REALISTIC_ANGLE) + "°";
        }
        String controls = "Forward: " + MvcGameConfig.CONTROLS_FORWARD + ", Backward: " + MvcGameConfig.CONTROLS_BACKWARD
                      + ", Left: " + MvcGameConfig.CONTROLS_LEFT + ", Right: " + MvcGameConfig.CONTROLS_RIGHT
                      + ", Shoot: " + MvcGameConfig.CONTROLS_SHOOT + ", Switch mode: " + MvcGameConfig.CONTROLS_MODE
                      + ", Step back: " + MvcGameConfig.CONTROLS_UNDO + ", Aim up: " + MvcGameConfig.CONTROLS_ANGLE_UP
                      + ", Aim down: " + MvcGameConfig.CONTROLS_ANGLE_DOWN + ", Power up: " + MvcGameConfig.CONTROLS_POWER_UP
                      + ", Power down: " + MvcGameConfig.CONTROLS_POWER_DOWN + ", Gravity up: " + MvcGameConfig.CONTROLS_GRAVITY_UP
                      + ", Gravity down: " + MvcGameConfig.CONTROLS_GRAVITY_DOWN;
        
        return controls + "\n" + begining + ", Force: " + force + "%, Gravity: " + gravity + "%";
    }

    @Override
    public int getForce() {
        return force;
    }

    @Override
    public int getForceCalc() {
        return force;
    }

    @Override
    public int getAngle() {
        return angle;
    }

    @Override
    public int getAngleCalc() {
        return 360 - angle;
    }

    @Override
    public int getGravity() {
        return gravity;
    }

    @Override
    public int getGravityCalc() {
        return gravity;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setForce(int force) {
        if (force >= 0 && force <= 100) {
            this.force = force;
        }
    }

    //Angle clamping
    @Override
    public void setAngle(int angle) {
        while (angle > 360) {
            angle -= 360;
        }
        while (angle < 0) {
            angle += 360;
        }
        this.angle = angle;
    }

    @Override
    public void setGravity(int gravity) {
        if (gravity >= 0 && gravity <= 100) {
            this.gravity = gravity;
        }
    };

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void setShootingMode(String mode) {
        this.shootingMode = mode;
    }

    @Override
    public void incScore() {
        ++this.score;
        if (this.score % MvcGameConfig.SCORE_TO_LEVEL == 0) {
            this.incLevel();
            this.score = 0;
            this.health = MvcGameConfig.GAME_HEALTH;
        }
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void incLevel() {
        ++this.level;
    }

    @Override
    public void decHealth() {
        --this.health;
    }

    @Override
    public int getHealth() {
        return this.health;
    }
}
package cz.cvut.fit.miadp.mvcgame.model;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.*;
import cz.cvut.fit.miadp.mvcgame.command.*;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.observer.*;

public class GameModel implements IGameModel, IObservable {
    private List<IObserver> myObs;

    public Queue<AbsCommand> unexecutedCmds = new LinkedBlockingQueue<AbsCommand>();
    public Stack<AbsCommand> executedCmds = new Stack<AbsCommand>();

    private IGameObjFac goFact;

    private AbsCannon cannon;
    private AbsModelInfo gameInfo;
    private List<AbsEnemy> enemies;
    private List<AbsMissile> missiles;
    private List<AbsCollision> collisions;

    public GameModel() {
        if (MvcGameConfig.USE_REALISTIC_MODE) {
            goFact = new GameObjFac_B();
        } else {
            goFact = new GameObjFac_A();
        }
        this.myObs = new ArrayList<IObserver>();

        this.gameInfo = this.goFact.createModelInfo();
        this.cannon = this.goFact.createCannon(this.gameInfo);

        this.enemies = new ArrayList<AbsEnemy>();
        this.missiles = new ArrayList<AbsMissile>();
        this.collisions = new ArrayList<AbsCollision>();
    }

    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyObservers();
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObservers();
    }

    public void moveCannonLeft() {
        this.cannon.moveLeft();
        this.notifyObservers();
    }

    public void moveCannonRight() {
        this.cannon.moveRight();
        this.notifyObservers();
    }

    public void cannonShoot() {
        this.missiles.addAll(this.cannon.shoot());
        this.notifyObservers();
    }

    @Override
    public void increaseAngle() {
        this.cannon.aimUp();
        this.notifyObservers();
    }

    @Override
    public void decreaseAngle() {
        this.cannon.aimDown();
        this.notifyObservers();
    }

    @Override
    public void increaseGravity() {
        this.gameInfo.setGravity(this.gameInfo.getGravity() + 1);
        this.notifyObservers();
    }

    @Override
    public void decreaseGravity() {
        this.gameInfo.setGravity(this.gameInfo.getGravity() - 1);
        this.notifyObservers();
    }

    @Override
    public void increasePower() {
        this.cannon.incPower();
        this.notifyObservers();
    }

    @Override
    public void decreasePower() {
        this.cannon.decPower();
        this.notifyObservers();
    }

    @Override
    public void registerObserver(IObserver obs) {
        this.myObs.add(obs);
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        this.myObs.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (IObserver obs : this.myObs) {
            obs.update();
        }
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> go = new ArrayList<GameObject>();

        go.addAll(this.missiles);
        go.addAll(this.enemies);
        go.addAll(this.collisions);
        go.add(this.cannon);
        go.add(this.gameInfo);

        return go;
    }

    public void timeTick() {
        this.checkForGameEnd();
        this.updateHealth();
        this.destroyOutOfBoundsGameObjects();
        this.destroyExpiredObjects();
        this.checkCollisions();
        if (!this.executeCmds()) {
            this.spawnEnemies();
        }
        this.moveMissiles();
        this.moveEnemies();
        if (MvcGameConfig.UNDO_TIME_BASED) {
            this.registerCommand(new MementoCopyCommand(this));
        }
    }

    private void updateHealth() {
        for (AbsEnemy e: this.enemies) {
            if (e.isOutOfBounds()) {
                this.gameInfo.decHealth();
            }
        }
    }

    private void checkForGameEnd() {
        if (this.gameInfo.getHealth() <= 0) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                //If sleep fails just exit rightaway
            } finally {
                System.exit(0);
            }
        }
    }

    private void checkCollisions() {
        for (AbsEnemy enemy: this.enemies) {
            for (AbsMissile missile: this.missiles) {
                if (missile.collidesWith(enemy)) {
                    this.collisions.add(goFact.createCollision(enemy.getPosition()));
                    this.removeObject(enemy);
                    this.removeObject(missile);
                    this.gameInfo.incScore();
                }
            }
        }
    }

    private void spawnEnemies() {
        if (new Random().nextInt(100) == 0) {
            AbsEnemy en;
            do {
                en = goFact.createEnemy(this.gameInfo);
            } while (this.collidesWith(en, this.enemies));

            this.enemies.add(en);
        }
    }

    public boolean collidesWith(GameObject gameObject, List<AbsEnemy> gos) {
        for (GameObject g: gos) {
            if (gameObject.collidesWith(g)) {
                return true;
            }
        }
        return false;
    }

    private void destroyExpiredObjects() {
        for (AbsCollision col: this.collisions) {
            if (col.getLifetime() > MvcGameConfig.COLLISION_DISPLAY_TIME) {
                this.removeObject(col);
            }
        }
    }

    public boolean executeCmds() {
        boolean executedUndo = false;
        while (!this.unexecutedCmds.isEmpty()) {
            AbsCommand cmd = this.unexecutedCmds.poll();
            if (cmd instanceof UndoCommand) {
                executedUndo = true;
            }
            cmd.doExecute();

            this.executedCmds.add(cmd);
        }
        return executedUndo;
    }

    public void undoLastCmd() {
        if (this.executedCmds.isEmpty()) {
            return;
        }

        AbsCommand cmd = this.executedCmds.pop();
        cmd.unexecute();

        this.notifyObservers();
    }

    private void destroyOutOfBoundsGameObjects() {
        for (GameObject go: getGameObjects()) {
            if (go.isOutOfBounds()) {
                this.removeObject(go);
            }
        }
    }

    private void removeObject(GameObject go) {
        this.missiles.remove(go);
        this.enemies.remove(go);
        this.collisions.remove(go);
    }

    private void moveMissiles() {
        for(AbsMissile m: this.missiles) {
            m.move();
        }
        this.notifyObservers();
    }

    private void moveEnemies() {
        for(AbsEnemy m: this.enemies) {
            m.move();
        }
        this.notifyObservers();
    }

	public void toggleShootingMode() {
        this.cannon.toggleShootingMode();
        this.notifyObservers();
    }
    
    private class Memento {
        public Memento() {
            this.enemies = new ArrayList<AbsEnemy>();
            this.missiles = new ArrayList<AbsMissile>();
            this.collisions = new ArrayList<AbsCollision>();
        }
        private AbsModelInfo gameInfo;
        private AbsCannon cannon;
        private List<AbsEnemy> enemies;
        private List<AbsMissile> missiles;
        private List<AbsCollision> collisions;
    }

    public Object createMemento() {
        Memento m = new Memento();
        m.gameInfo = this.gameInfo.makeCopy();
        m.cannon = this.cannon.makeCopy(m.gameInfo);
        for (AbsEnemy en: this.enemies) {
            m.enemies.add(en.makeCopy());
        }
        for (AbsMissile mi: this.missiles) {
            m.missiles.add(mi.makeCopy());
        }
        for (AbsCollision co: this.collisions) {
            m.collisions.add(co.makeCopy());
        }
        return m;
    }

    public void setMemento(Object memento) {
        Memento m = (Memento)memento;
        this.gameInfo = m.gameInfo;
        this.cannon = m.cannon;
        this.cannon.setLastShot();
        this.enemies = m.enemies;
        this.missiles = m.missiles;
        this.collisions = m.collisions;
    }

    @Override
    public void registerCommand(AbsCommand cmd) {
        this.unexecutedCmds.add(cmd);
    }
}

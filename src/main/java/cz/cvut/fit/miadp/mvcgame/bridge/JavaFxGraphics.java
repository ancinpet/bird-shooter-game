package cz.cvut.fit.miadp.mvcgame.bridge;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.imageCache.ImageStringCache;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import javafx.scene.canvas.GraphicsContext;

public class JavaFxGraphics implements IGameGraphicsImplementor {
    private ImageStringCache cache = new ImageStringCache();
    private GraphicsContext gc;

    public JavaFxGraphics(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void drawImage(String path, Position pos) {
        //Uses image caching
        this.gc.drawImage(cache.getImage(path), pos.getX(), pos.getY());
    }

    @Override
    public void drawText(String text, Position pos) {
        this.gc.fillText(text, pos.getX(), pos.getY());
    }

    @Override
    public void drawLine(Position beginPos, Position endPos) {
        this.gc.strokeLine(beginPos.getX(), beginPos.getY(), endPos.getX(), endPos.getY());
    }

    @Override
    public void clear() {
        this.gc.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
    }
}
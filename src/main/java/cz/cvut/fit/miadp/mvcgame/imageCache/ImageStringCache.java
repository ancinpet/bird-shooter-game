package cz.cvut.fit.miadp.mvcgame.imageCache;

import java.util.Hashtable;
import javafx.scene.image.Image;

public class ImageStringCache {
    private Hashtable<String, ImagePair> cache = new Hashtable<String, ImagePair>();

    //If Image doesn't exist, create it and put it into cache
    //If Image exists, look it up and return it
    public Image getImage(String path) {
        ImagePair cached = cache.get(path);
        if (cached != null) {
            return cached.image;
        }

        ImagePair notCached = new ImagePair(path);
        cache.put(path, notCached);
        return notCached.image;
    }
}

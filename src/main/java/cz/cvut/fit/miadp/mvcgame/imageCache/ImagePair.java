package cz.cvut.fit.miadp.mvcgame.imageCache;

import javafx.scene.image.Image;

//Pair <String, Image>
//Used for caching Images
public class ImagePair {
    public String path;
    public Image image;

    public ImagePair(String path) {
        this.path = path;
        this.image = new Image(path);
    }
}
package game.sprite;

import game.movement.Location;
import game.character.Character;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.ImageObserver;

public class SpriteImage extends Sprite {

    private Image[] images = null;
    private int frame = 0;
    private Character owner = null;
    private ImageObserver imageObserver = null;

    public Image[] getImages() {
        return images;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public Character getOwner() {
        return owner;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public int getFrame() {
        return frame;
    }

    public Image getDisplayRepresentation() {
        return this.images[frame];
    }

    public void setImageObserver(ImageObserver observer) {
        this.imageObserver = observer;

    }
    boolean squeezeImageIntoTransformedArea = false;

    @Override
    public Graphics paint(Graphics g, Location loc) {
        Graphics2D gg = (Graphics2D) g;

        Image img;
        img = getImages()[getFrame()];
        gg.setColor(new Color(255, 0, 0, 100));
        //gg.fill(this.getTransformedArea());
        Rectangle2D b = getTransformedArea().getBounds2D();
        if (squeezeImageIntoTransformedArea) {
            gg.drawImage(
                    img,
                    (int) b.getX(),
                    (int) b.getY(),
                    (int) b.getWidth(),
                    (int) b.getHeight(),
                    getImageObserver());
        } else {
            gg.drawImage(img, getTransform(), getImageObserver());
        }

        return (Graphics) gg;
    }

    public ImageObserver getImageObserver() {
        return imageObserver;
    }

    public void setTransformation(int x, int y, Area a) {

        this.setUntransformedArea(a);
        this.setSqueezeImageIntoTransformedArea(false);
        AffineTransform t = new AffineTransform();
        t.setToTranslation(x, y);

        this.setTransform(t);
        this.setUntransformedArea(a);
        this.setTransformedArea(a.createTransformedArea(t));

        this.setShowSprite(true);
        this.setImageObserver(game.Renderer.getInstance());
    }

    public SpriteImage(Image[] images, Character owner) {
        this.owner = owner;
        this.images = images;
        frame = 0;

        this.setUntransformedArea(new Area(
                new Rectangle(
                0, 0, images[0].getWidth(game.Renderer.getInstance()), images[0].getHeight(game.Renderer.getInstance()))));
    }

    public boolean isSqueezeImageIntoTransformedArea() {
        return squeezeImageIntoTransformedArea;
    }

    public void setSqueezeImageIntoTransformedArea(boolean squeezeImageIntoTransformedArea) {
        this.squeezeImageIntoTransformedArea = squeezeImageIntoTransformedArea;
    }
}

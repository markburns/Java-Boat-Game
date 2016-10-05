package game.character;

import game.InputController;

import game.movement.Location;
import game.movement.Movement;
import game.sprite.Sprite;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public abstract class Character {
	static Logger logging = Logger.getLogger(Character.class); //basic log system
    private Location myLocation = null; //location X Y of the character main boat
    private Movement moveBehaviour = null; // behaviour of the movement
    private Sprite sprite = null; // sets the hitbox of the character boat
    private InputController controller = InputController.getInstance();

    public abstract void collide();

    public Location getLocation() {
        return myLocation;
    }

    public double getX() {
        return myLocation.getX();
    }

    public double getY() {
        return myLocation.getY();
    }

    public double centreY() {
        return getHeight() / 2;
    }

    public double centreX() {
        return getWidth() / 2;
    }

    public double getHeight() {
        return getBounds().getHeight();
    }

    public double getWidth() {
        return getBounds().getWidth();
    }

    public void setTransform(Location rotateCentre) {
        double centreHeight;
        double centreWidth;

        AffineTransform temp = AffineTransform.getTranslateInstance(
                getLocation().getX(), getLocation().getY());

        if (rotateCentre == null) {
            centreWidth = centreX();
            centreHeight = centreY();
        } else {
            centreWidth = rotateCentre.getX();
            centreHeight = rotateCentre.getY();
        }

        temp.rotate(getMoveBehaviour().getAngle(),
                centreWidth,
                centreHeight);


        sprite.setTransform(temp);
        sprite.setTransformedArea(sprite.getUntransformedArea().createTransformedArea(temp));

    }

    public abstract void update();

    public Rectangle getBounds() {
        return sprite.getBounds();
    }
    
    public double x(){
        return getBounds().getCenterX();

    }
        
    public double y(){
        return getBounds().getCenterY();

    }
        
    public boolean collides(Character character) {
        if (character.equals(this)) {
            return false;
        }

        Area intersectArea = new Area(getTransformedArea());
        Area b = character.getTransformedArea();

        intersectArea.intersect(b);

        return !intersectArea.isEmpty();
    }

    public Area getTransformedArea() {
        return sprite.getTransformedArea();
    }

    public void collide(Character character) {
    }

    /**
     *
     * @param data an ArrayList<CharacterBase> used to check for collisions
     * @return true if this Character collided with one of characters
     */
    public boolean detectCollision(ArrayList<Character> data) {
        ArrayList<Character> moving = data;
        boolean collision = false;

        int length = moving.size();
        for (int i = 0; i < length; i++) {
            Character character = moving.get(i);

            if (collision = collides(character)) {
                character.collide();
            }
        }

        return collision;
    }

    public InputController getController() {
        return controller;
    }

    public Area getUntransformedArea() {
        return sprite.getUntransformedArea();
    }

    public void setUntransformedArea(Area area) {
        sprite.setUntransformedArea(area);
    }

    public void setLocation(Location location) {
    	//set to info to avoid loop (location is re-created once a second)
    	logging.setLevel(Level.INFO);
    	logging.debug("location set " + location.getX() + " " + location.getY());
        myLocation = location;

    }

    /**
     * Creates a new instance of Character
     */
    public Character() {
        myLocation = new Location(0.0, 0.0);
    }

    public Movement getMoveBehaviour() {
        return moveBehaviour;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setMoveBehaviour(Movement moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    void setLocation(double x, double y) {
        if (myLocation == null) {
            myLocation = new Location(x, y);
        } else {
            this.myLocation.setLocation(x, y);
        }
    }
}

/*
 * Character.java
 *
 * Created on 01 November 2007, 17:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game.character;

import game.InputController;
import game.GameEngine;
import game.GameWindow;
import game.movement.Location;
import game.movement.Movement;
import game.sprite.Sprite;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author m2burns
 */
public abstract class Character {

    private Location myLocation;
    private Movement moveBehaviour;
    private Sprite sprite;
    private InputController controller = InputController.getInstance();

    public abstract void collision();

    public Location getLocation() {
        return myLocation;
    }

    public double getX() {
        return myLocation.getX();
    }

    public double getY() {
        return myLocation.getY();
    }

    public void setTransform(Location rotateCentre) {
        double centreWidth;
        double centreHeight;
        AffineTransform temp = AffineTransform.getTranslateInstance(
                getLocation().getX(), getLocation().getY());

        if (rotateCentre == null) {
            centreWidth = getSprite().getUntransformedArea().getBounds().getWidth() / 2;
            centreHeight = getSprite().getUntransformedArea().getBounds().getHeight() / 2;
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

    /**
     *
     * @param data an ArrayList<CharacterBase> used to check for collisions
     * @return true if this Character collided with one of characters
     */
    public boolean detectCollision(ArrayList<Character> data) {
        Rectangle characterBounds;
        ArrayList<Character> moving = data;
        boolean collision = false;

        int length = moving.size();
        for (int i = 0 ; i < length; i ++) {
            Character c = moving.get(i);
            if (!c.equals(this)) {

                characterBounds = c.getSprite().getTransformedArea().getBounds();
                if (characterBounds.intersects(getSprite().getTransformedArea().getBounds())) {
                    Area intersectArea = new Area(getSprite().getTransformedArea());
                    Area b = c.getSprite().getTransformedArea();
                    intersectArea.intersect(b);
                    collision = !intersectArea.isEmpty();
                    if (collision) {
                        Character boat = GameEngine.getInstance().getCharacters().get("Boat");

                        if (c.equals(boat)) {
                            int e = ((Boat) boat).getEnergy();
                            e--;
                            ((Boat) boat).setEnergy(e);

                            if (e <= 0) {

                                GameEngine.getInstance().gameOver();
                            } else {

                                GameWindow.getInstance().setEnergyBarLevel(e);
                            }

                        }
                        c.collision();

                    }
                }
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

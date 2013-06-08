/*
 * CharacterHarbour.java
 *
 * Created on 04 December 2007, 10:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game.character;

import game.character.Character;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author Mark
 */
public class CharacterHarbour extends CharacterSolidStationary {

    /**
     * Creates a new instance of CharacterHarbour
     */
    public CharacterHarbour() {
    }

    public boolean detectCollision(ArrayList<Character> data) {
        Rectangle characterBounds = null;
        ArrayList<Character> moving = data;
        boolean collision = false;

        for (Iterator i = moving.iterator(); i.hasNext();) {
            Character c = (Character) i.next();
            if (!c.equals(this)) {

                characterBounds = c.getSprite().getTransformedArea().getBounds();
                if (characterBounds.intersects(getSprite().getTransformedArea().getBounds())) {
                    Area intersectArea = new Area(getSprite().getTransformedArea());
                    Area b = c.getSprite().getTransformedArea();
                    intersectArea.intersect(b);
                    collision = !intersectArea.isEmpty();
                    if (collision) {
                    }
                }
            }
        }


        return collision;
    }
}

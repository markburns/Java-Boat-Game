package game.character;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Harbour extends Stationary {
    public Harbour() {
    }

    @Override
    public boolean detectCollision(ArrayList<Character> data) {
        Rectangle characterBounds;
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

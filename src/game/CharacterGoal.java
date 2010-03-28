/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.util.*;
import java.awt.geom.*;
import java.awt.*;

/**
 *
 * @author mrk
 */
class CharacterGoal extends CharacterSolidStationary
{

    public boolean detectCollision(ArrayList<CharacterBase> data)
    {
	Rectangle characterBounds = null;
	ArrayList<CharacterBase> moving = data;
	boolean collision =false;
	try
	{
	    for (Iterator i = moving.iterator(); i.hasNext();)
	    {
		CharacterBase c = (CharacterBase) i.next();
		if(!c.equals(this))
		{
		    
		    characterBounds = c.getSprite().getTransformedArea().getBounds();
		    if (characterBounds.intersects(getSprite().getTransformedArea().getBounds()))
		    {
			Area intersectArea = new Area(getSprite().getTransformedArea());
			Area b = c.getSprite().getTransformedArea();
			intersectArea.intersect(b);
			collision = !intersectArea.isEmpty();
			if (collision)
			{
			    if (c.equals(GameEngine.getInstance().getCharacters().get("Boat")))
			    {
				GameEngine.getInstance().win();
				
			    }
			    else
			    {
				c.collision();
			    }
			    
			}
		    }
		}
	    }
	}
	catch (Exception ex)
	{
	    ex.printStackTrace();
	}
	return collision;
    }
}

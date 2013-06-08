/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.character;

import game.GameEngine;
import java.util.*;
import java.awt.geom.*;
import java.awt.*;

/**
 *
 * @author mrk
 */
class Goal extends Stationary
{

    @Override
    public boolean detectCollision(ArrayList<Character> data)
    {
	Rectangle characterBounds = null;
	ArrayList<Character> moving = data;
	boolean collision =false;
	try
	{
	    for (Iterator i = moving.iterator(); i.hasNext();)
	    {
		Character c = (Character) i.next();
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

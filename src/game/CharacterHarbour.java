/*
 * CharacterHarbour.java
 *
 * Created on 04 December 2007, 10:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author Mark
 */
public class CharacterHarbour extends CharacterSolidStationary
{
    
    /** Creates a new instance of CharacterHarbour */
    public CharacterHarbour()
    {
    }
    
    
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

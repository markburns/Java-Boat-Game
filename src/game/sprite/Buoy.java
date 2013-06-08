/*
 * SpriteBuoy.java
 *
 * Created on 15 November 2007, 10:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package game.sprite;

import game.movement.Location;
import game.character.Character;
import java.awt.*;
/**
 *
 * @author Mark
 */
public class Buoy extends Vector
{
    Color mainColor= new Color(20,50,24);
    

    /**
	 * Creates a new instance of SpriteBuoy
	 */
    public Buoy(Character owner)
    {
	super(owner);
	

	
    }
    
    public Graphics paint(Graphics g, Location loc)
    {
	
	int x = this.getTransformedArea().getBounds().x;
	int y = this.getTransformedArea().getBounds().y;
	Graphics2D gg = (Graphics2D) g;
	if (isSpriteShown())
	{
	    gg.setColor(Color.BLUE);
	    //loc.setLocation(x,y);
	    gg.setColor(Color.BLACK);
	    gg.fillOval(x-1,y-2,20+2,20+1);
	    gg.setColor(new Color(255,50,34));
	    gg.fillOval(x,y,20,20);
	    gg.setColor(new Color(250,250,250));
	    gg.fillOval(x+5,y+5,20-8,20-8);
	    gg.setColor(new Color(255,50,34));
	    gg.fillOval(x+7,y+7,20-15,20-15);
	    
	}
	
	return (Graphics)gg;
    }
    
    public Color getMainColor()
    {
	return mainColor;
    }
    
    public void setMainColor(Color obstacleMainColor)
    {
	this.mainColor = obstacleMainColor;
    }
    
}

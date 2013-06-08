/*
 * SpriteEnemyBoat.java
 *
 * Created on 20 November 2007, 08:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package game.sprite;

import game.movement.Location;
import game.character.Character;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Mark
 */
public class EnemyBoat extends Vector
{
    
    /** Creates a new instance of SpriteEnemyBoat */
    public EnemyBoat(Character owner)
    {
	super(owner);
    }
    public Graphics paint(Graphics g,Location loc)
    {
	
	g.setColor(Color.BLACK);
	g.fillOval((int)loc.getX(),(int)loc.getY(),20,20);
	return g;
    }

}

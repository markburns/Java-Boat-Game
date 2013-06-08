package game.sprite;

import game.movement.Location;
import game.character.Character;
import java.awt.Color;
import java.awt.Graphics;

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

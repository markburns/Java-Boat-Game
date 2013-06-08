/*
 * CharacterObstacle.java
 *
 * Created on 16 November 2007, 15:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package game.character;


import java.awt.geom.*;


/**
 *
 * @author Mark
 */
public class Obstacle extends Character
{
    private double angMomentum=0.06;
    private double xAngMomentum;
    private double phase=0.0;
    private double yOffset = 0.0;
    private double xOffset = 0.0;
    private double randomMomentum;
    private double randomPhase;
    /** Creates a new instance of CharacterObstacle */
    public Obstacle()
    {
	xAngMomentum = getLocation().getX();
	phase+=randomPhase;
    }
    @Override
    public void update()
    {
	
	setLocation(getMoveBehaviour().go(getLocation()));
	AffineTransform t = new AffineTransform();
	t.setToTranslation(getX(),getY());
	Area a = new Area(getSprite().getUntransformedArea());
	a.transform(t);
	getSprite().setTransformedArea(a);
	
    }
    
    @Override
    public void collision()
    {
    }
    
}


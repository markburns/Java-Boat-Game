/*
 * CharacterObstacle.java
 *
 * Created on 16 November 2007, 15:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package game.character;


import game.character.Character;
import java.awt.Rectangle;
import java.awt.geom.*;

import java.util.*;


/**
 *
 * @author Mark
 */
public class CharacterObstacle extends Character
{
    private double angMomentum=0.06;
    private double xAngMomentum;
    private double phase=0.0;
    private double yOffset = 0.0;
    private double xOffset = 0.0;
    private double randomMomentum;
    private double randomPhase;
    /** Creates a new instance of CharacterObstacle */
    public CharacterObstacle()
    {
	xAngMomentum = getLocation().getX();
	phase+=randomPhase;
    }
    public void update()
    {
	
	setLocation(getMoveBehaviour().go(getLocation()));
	AffineTransform t = new AffineTransform();
	t.setToTranslation(getX(),getY());
	Area a = new Area(getSprite().getUntransformedArea());
	a.transform(t);
	getSprite().setTransformedArea(a);
	
    }
    
    public void collision()
    {
    }
    
}


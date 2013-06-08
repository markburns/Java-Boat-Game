/*
 * Sprite2d.java
 *
 * Created on 15 November 2007, 11:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package game.sprite;
import game.character.Character;
import java.awt.*;

/**
 *
 * @author Mark
 */
public abstract class Vector extends Sprite
{
    
 
    private Character owner;

 
  
     
    
    public Character getOwner()
    {
	return owner;
    }
    
    /** Creates a new instance of Sprite2d */
    public Vector(Character owner)
    {
	this.owner = owner;//Used by the GameEngine to determine which character owns this sprite
	
    }
    
}

/*
 * Sprite2d.java
 *
 * Created on 15 November 2007, 11:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package game;
import java.awt.*;

/**
 *
 * @author Mark
 */
public abstract class SpriteVector extends Sprite
{
    
 
    private CharacterBase owner;

 
  
     
    
    public CharacterBase getOwner()
    {
	return owner;
    }
    
    /** Creates a new instance of Sprite2d */
    public SpriteVector(CharacterBase owner)
    {
	this.owner = owner;//Used by the GameEngine to determine which character owns this sprite
	
    }
    
}

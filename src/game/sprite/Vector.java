package game.sprite;
import game.character.Character;

public abstract class Vector extends Sprite
{
    
 
    private Character owner;

 
  
     
    
    public Character getOwner()
    {
	return owner;
    }
    
    public Vector(Character owner)
    {
	this.owner = owner;//Used by the GameEngine to determine which character owns this sprite
	
    }
    
}

/*
 * CharacterFactoryBase.java
 *
 * Created on 05 December 2007, 10:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package game;

/**
 *
 * @author Mark
 */
public abstract class CharacterFactoryBase
{
    
    /**
	 * Creates a new instance of CharacterFactoryBase
	 */
    public CharacterFactoryBase()
    {
    }
    
    public abstract CharacterBase createCharacter(Util.CharacterType characterType);
}

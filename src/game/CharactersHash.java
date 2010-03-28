/*
 * Characters.java
 *
 * Created on 04 December 2007, 15:06
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game;

import java.util.*;

/**
 *
 * @author Mark
 */
public class CharactersHash extends HashMap<String, CharacterBase>
{
    
    private static CharactersHash charactersSingleton;
    
    public static CharactersHash getInstance()
    {
	if (charactersSingleton != null)
	{
	    return charactersSingleton;
	    
	}
	else
	{
	    synchronized (CharactersHash.class)
	    {
		if (charactersSingleton == null)
		{
		    charactersSingleton = new CharactersHash();
		}
	    }
	    
	}
	return charactersSingleton;
    }
    
    public ArrayList<CharacterBase> getMovingCharacters()
    {
	
	ArrayList<CharacterBase> charactersMoving = new ArrayList<CharacterBase>();
	
	charactersMoving.add(charactersSingleton.get("Boat"));
	CharacterBase c;
	int x = 0;
	
	boolean finished = false;
	
	while (!finished)
	{
	    
	    if (charactersSingleton.containsKey("ComputerBoat" + String.valueOf(x)))
	    {
		charactersMoving.add(charactersSingleton.get("ComputerBoat" + String.valueOf(x)));
		x++;
	    }
	    else
	    {
		finished = true;
	    }
	    
	}
	
	return charactersMoving;
    }
    

    public static void reset(){
	charactersSingleton=null;
    }    
    public ArrayList<CharacterBase> getAllCharacters()
    {
	
	
	ArrayList<CharacterBase> all = new ArrayList<CharacterBase>();
	
	int x = 0;
	boolean finished = false;
	while (!finished)
	{
	    
	    if (charactersSingleton.containsKey("Obstacle" + String.valueOf(x)))
	    {
		all.add(charactersSingleton.get("Obstacle" + String.valueOf(x)));
		x++;
	    }
	    else
	    {
		finished = true;
	    }
	}
	String[] objects = {"Harbour","Island" ,"Boat"};
	for(int y=0;y<3;y++)
	{
	    if (charactersSingleton.containsKey(objects + String.valueOf(x)))
	    {
		all.add(charactersSingleton.get(objects + String.valueOf(x)));
		
	    }
	}
	
	x = 0;
	finished = false;
	
	while (!finished)
	{
	    
	    if (charactersSingleton.containsKey("ComputerBoat" + String.valueOf(x)))
	    {
		all.add(charactersSingleton.get("ComputerBoat" + String.valueOf(x)));
		x++;
	    }
	    else
	    {
		finished = true;
	    }
	    
	}
	if (charactersSingleton.containsKey("Goal"))
	    {
		all.add(charactersSingleton.get("Goal"));
		
	    }
	return all;
    }
    
    public ArrayList<CharacterBase> getObstacles()
    {
	
	ArrayList<CharacterBase> obstacles = new ArrayList<CharacterBase>();
	int x = 0;
	boolean finished = false;
	while (!finished)
	{
	    
	    if (charactersSingleton.containsKey("Obstacle" + String.valueOf(x)))
	    {
		obstacles.add(charactersSingleton.get("Obstacle" + String.valueOf(x)));
		x++;
	    }
	    else
	    {
		finished = true;
	    }
	    
	}
	obstacles.add(charactersSingleton.get("Harbour"));
	obstacles.add(charactersSingleton.get("Island"));
	if (charactersSingleton.containsKey("Goal"))
	    {
		obstacles.add(charactersSingleton.get("Goal"));
		
	    }
	
	return obstacles;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.sprite;

import game.movement.Location;
import game.Renderer;
import game.character.Character;
import java.awt.*;


/**
 *
 * @author mrk
 */
public class Goal extends Vector{
	Renderer renderer = Renderer.getInstance();
	@Override
	public Graphics paint(Graphics g, Location loc)
	{
	
		
		Graphics2D gg = (Graphics2D)g;

		gg.setColor(new Color(100,50,100,175));
		gg.fillRoundRect(renderer.getWidth()-100, renderer.getHeight()-50,100,50,10,10);
		gg.setColor(Color.RED);
		Font font =Font.getFont("Impact");
		gg.setFont(font);
		
		
		gg.drawString("Goal", renderer.getWidth()-50, renderer.getHeight()-20);
		return gg;
	}
 /** Creates a new instance of Sprite2d */
    public Goal(Character owner)
    {
			super(owner);//Used by the GameEngine to determine which character owns this sprite
	
    }
   
}

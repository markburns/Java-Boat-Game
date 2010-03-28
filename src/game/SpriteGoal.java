/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.*;


/**
 *
 * @author mrk
 */
public class SpriteGoal extends SpriteVector{
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
    public SpriteGoal(CharacterBase owner)
    {
			super(owner);//Used by the GameEngine to determine which character owns this sprite
	
    }
   
}

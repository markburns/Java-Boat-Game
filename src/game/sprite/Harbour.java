
package game.sprite;

import game.movement.Location;
import game.character.Character;
import java.awt.*;

/**
 *
 * @author Mark
 */
public class Harbour extends Vector
{

	/**
	 * Creates a new instance of Sprite2dObstacle
	 */
	public Harbour(Character owner)
	{
		super(owner);
	}

	@Override
	public Graphics paint(Graphics g, Location loc)
	{

		int x = this.getTransformedArea().getBounds().x;
		int y = this.getTransformedArea().getBounds().y;
		Graphics2D gg = (Graphics2D) g;
		if (isSpriteShown())
		{

			gg.setColor(new Color(25,60,240,50));
			//gg.fill(getTransformedArea());
			

		}

		return (Graphics) gg;
	}

}

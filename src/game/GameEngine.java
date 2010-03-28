/*
 * GameEngine.java
 *
 * Created on 13 November 2007, 17:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game;

import java.awt.geom.*;

import java.util.*;
import javax.swing.JOptionPane;

/**S
 *
 * @author Mark
 */
public class GameEngine implements Runnable
{

    private static CharactersHash characters = game.CharactersHash.getInstance();
    public static Renderer renderer;
    private static Controller controller = Controller.getInstance();
    private static int minObstacles;
    private static int maxObstacles;
    private static int obstacleSize;
    public static final int SLEEP_LENGTH = 16;//16 ms equates to ~60 frames per second
    private static GameEngine singleton;
    boolean alive = false;
    
    /** Creates a new instance of GameEngine */
    private GameEngine()
    {
	initialize();
    }

    private static void initialize()
    {
	characters = game.CharactersHash.getInstance();
	renderer = Renderer.getInstance();
	controller = Controller.getInstance();
	minObstacles = 10;
	maxObstacles = minObstacles + 5;
	obstacleSize = 20;


    }

    public static void reset()
    {
	singleton = null;
	
    }

    public static GameEngine getInstance()
    {
	if (singleton != null)
	{
	    return singleton;

	}
	else
	{
	    synchronized (GameEngine.class)
	    {
		if (singleton == null)
		{
		    singleton = new GameEngine();
		}
	    }

	}

	return singleton;
    }

    private void setupBoat()
    {
	CharacterFactoryBase factory = new CharacterFactoryConcrete();
	CharacterBase boat = factory.createCharacter(Util.CharacterType.BOAT);
	boat.setLocation(Util.getBoatLocation());
	characters.put("Boat", boat);
	GameWindow.getInstance().initializeControlPanel(boat);


    }

    private void setupHarbour()
    {
	CharacterFactoryBase factory = new CharacterFactoryConcrete();
	CharacterBase harbour = factory.createCharacter(Util.CharacterType.HARBOUR);

	characters.put("Harbour", harbour);

    }

    private void setupIsland()
    {
	CharacterFactoryBase factory = new CharacterFactoryConcrete();
	CharacterBase island = factory.createCharacter(Util.CharacterType.ISLAND);

	characters.put("Island", island);

    }

    private void setupGoal()
    {
	CharacterFactoryBase factory = new CharacterFactoryConcrete();
	CharacterBase goal = factory.createCharacter(Util.CharacterType.GOAL);

	characters.put("Goal", goal);

    }

    public void setupComputerBoat()
    {

	CharacterFactoryBase factory = new CharacterFactoryConcrete();
	for (int x = 0; x <
	      2; x++)
	{
	    CharacterBase computerBoat = factory.createCharacter(Util.CharacterType.COMPUTER_BOAT);
	    computerBoat.setLocation(Math.random() * renderer.getWidth(), Math.random() * renderer.getHeight());
	    characters.put("ComputerBoat" + String.valueOf(x), computerBoat);
	}

    }

    private void setupObstacles()
    {
	CharacterFactoryBase factory = new CharacterFactoryConcrete();
	CharacterBase obstacle;

	int min = Util.getMinimumNumberOfObstacles();
	int max = Util.getMamiumNumberOfObstacles();
	int numberOfObstacles = (int) (Math.random() * (max - min));
	for (int x = 0; x <
	      numberOfObstacles + 1; x++)
	{
	    if (Math.random() > 0.5)
	    {
		obstacle = factory.createCharacter(Util.CharacterType.BUOY);
	    }
	    else
	    {
		obstacle = factory.createCharacter(Util.CharacterType.OCTOPUS);

	    }

	    Location l = new Location(Math.random() * renderer.getWidth(), Math.random() * renderer.getHeight());
	    //do
	    //{
	    obstacle.setLocation(l);
	    Sprite s = obstacle.getSprite();
	    AffineTransform t = new AffineTransform();
	    obstacle.getSprite().getTransform().setToTranslation(l.getX(), l.getY());
	    s.setUntransformedArea(s.getUntransformedArea().createTransformedArea(obstacle.getSprite().getTransform()));

	    //}
	    //while(obstacle.detectCollision(characters.getAllCharacters()));


	    characters.put("Obstacle" + String.valueOf(x), obstacle);
	}

    }

    public void initialize(Renderer renderer)
    {
	this.renderer = renderer;
	if (Util.loadImages())
	{
	    setupIsland();
	    setupHarbour();
	    setupGoal();
	    setupBoat();
	    setupComputerBoat();
	    setupObstacles();


	    //draw the sea
	    renderer.setBackgroundImage(Util.imageResources.get("SEA"));

	}
	else
	{
	    JOptionPane.showMessageDialog(null, "Problem loading images");
	    System.exit(0);

	}

    }
    boolean storm = false;

    public void storm()
    {
	ArrayList<CharacterBase> chars = this.characters.getObstacles();
	for (Iterator i = chars.iterator(); i.hasNext();)
	{
	    CharacterBase c = (CharacterBase) i.next();
	    c.getSprite().setShowSprite(!c.getSprite().isSpriteShown());
	}

	storm = !storm;
	if (storm)
	{
	    renderer.setBackgroundImage(Util.imageResources.get("NIGHT"));

	}
	else
	{
	    renderer.setBackgroundImage(Util.imageResources.get("SEA"));
	}

    }
    

    public void gameOver()
    {
	if (((CharacterBoat) characters.get("Boat")).immune == false)
	{
	    javax.swing.JOptionPane.showMessageDialog(null, "GAME OVER!");
	    ((CharacterBoat) characters.get("Boat")).immune = true;
	}

	Controller.reset();
	controller =     null;
    }

    public void win()
    {
	if (((CharacterBoat) characters.get("Boat")).immune == false)
	{
	    javax.swing.JOptionPane.showMessageDialog(null, "YOU WIN!");
	    Controller.reset();
	    ((CharacterBoat) characters.get("Boat")).immune = true;
	    controller =
		  null;
	}

    }

    @Override
    public void run()
    {
	while (alive)
	{

	    ArrayList<CharacterBase> moving = characters.getMovingCharacters();

	    for (Iterator i = characters.getObstacles().iterator(); i.hasNext();)
	    {
		CharacterBase c = (CharacterBase) i.next();
		c.update();
		c.detectCollision(moving);

	    }

	    for (Iterator i = moving.iterator(); i.hasNext();)
	    {
		CharacterBase c = (CharacterBase) i.next();
		c.update();
		c.detectCollision(moving);

	    }

	    renderer.repaint();
	    try
	    {
		Thread.sleep(SLEEP_LENGTH);
	    }
	    catch (Exception ex)
	    {
		ex.printStackTrace();
	    }

	}

    }

    public HashMap<String, CharacterBase> getCharacters()
    {
	return characters;
    }

    public void setAlive(boolean alive)
    {
	this.alive = alive;
    }
}

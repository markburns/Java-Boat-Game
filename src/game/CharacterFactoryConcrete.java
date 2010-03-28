/*
 * CharacterFactoryConcrete.java
 *
 * Created on 05 December 2007, 10:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game;

import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author Mark
 */
public class CharacterFactoryConcrete extends CharacterFactoryBase
{
    
    /**
     * Creates a new instance of CharacterFactoryConcrete
     */
    public CharacterFactoryConcrete()
    {
    }
    
    private Area createAreaFromLocations(int[] locations, GeneralPath generalPath)
    {
	int count = 0;
	int x;
	int y;
	x = locations[count];
	y = locations[count + 1];
	generalPath.moveTo(x, y);
	count += 2;
	while (count < locations.length)
	{
	    
	    x = locations[count];
	    y = locations[count + 1];
	    count += 2;
	    generalPath.lineTo(x, y);
	}
	generalPath.closePath();
	Area area = new Area(generalPath);
	
	
	
	return area;
    }
    
    private CharacterBoat createBoat()
    {
	CharacterBoat boat = new CharacterBoat();
	Renderer renderer = Renderer.getInstance();
	
	
	
	Image[] boatImages = new Image[2];
	boatImages[0] = Util.imageResources.get("BOAT");
	boatImages[1] = Util.imageResources.get("BOAT_EXPLODE");
	SpriteImage boatSprite = new SpriteImage(boatImages, boat);
	
	//boat is approximately a triangle + a rectangle
	int boatWidth = boatImages[0].getWidth(renderer);
	int boatHeight = boatImages[0].getHeight(renderer);
	Area a = Util.getBoatArea(boatImages[0]);
	boat.setLocation(new Location(30, renderer.getHeight()));
	
	
	boatSprite.setUntransformedArea(a);
	boatSprite.setSqueezeImageIntoTransformedArea(false);
	AffineTransform t = new AffineTransform();
	t.setToTranslation(boat.getLocation().getX(), boat.getLocation().getY());
	boatSprite.setTransform(t);
	boatSprite.setUntransformedArea(a);
	boatSprite.setTransformedArea(a.createTransformedArea(t));
	
	boatSprite.setShowSprite(true);
	boatSprite.setImageObserver(renderer);
	
	boat.setSprite(boatSprite);
	
	boat.setControlMethod(CharacterBoat.ControlMethod.ROTATING);
	Move move = Util.getBoatMovePresets();
	
	//Add a swaying motion to the boat
	Move m = new MoveSwaying((MoveAngledAccelerate) move, 0, 0, Math.random(), boat, 0.2, 0.3);
	boat.setMoveBehaviour(m);
	return boat;
    }
    
    private CharacterComputerBoat createComputerBoat()
    {
	
	CharacterComputerBoat computerBoat = new CharacterComputerBoat();
	
	
	Renderer renderer = Renderer.getInstance();
	
	
	
	
	Image[] boatImages = new Image[2];
	boatImages[0] = Util.imageResources.get("BOAT2");
	boatImages[1] = Util.imageResources.get("BOAT_EXPLODE");
	
	
	
	//boat is approximately a triangle + a rectangle
	int boatWidth = boatImages[0].getWidth(renderer);
	int boatHeight = boatImages[0].getHeight(renderer);
	Area a = Util.getBoatArea(boatImages[0]);
	
	SpriteImage computerBoatSprite = new SpriteImage(boatImages, computerBoat);
	
	
	computerBoatSprite.setShowSprite(true);
	computerBoatSprite.setImageObserver(renderer);
	computerBoat.setLocation(new Location(450, 400));
	AffineTransform t = new AffineTransform();
	t.setToTranslation(computerBoat.getLocation().getX(), computerBoat.getLocation().getY());
	computerBoatSprite.setTransform(t);
	computerBoatSprite.setUntransformedArea(a);
	computerBoatSprite.setTransformedArea(a.createTransformedArea(t));
	
	
	
	computerBoatSprite.setShowSprite(true);
	computerBoatSprite.setImageObserver(renderer);
	
	computerBoat.setSprite(computerBoatSprite);
	
	
	Move computerBoatMove = Util.getComputerBoatMovePresets();
	
	
	computerBoat.setMoveBehaviour(computerBoatMove);
	
	
	
	
	
	return computerBoat;
    }
    
    private CharacterHarbour createHarbour()
    {
	
	
	Renderer renderer = Renderer.getInstance();
	
	
	CharacterHarbour harbour = new CharacterHarbour();
	
	
	SpriteHarbour harbourSprite = new game.SpriteHarbour(harbour);
	
	GeneralPath generalPath = new GeneralPath();
	
	
	int[] locations = Util.getHarbourData();
	Area area = createAreaFromLocations(locations, generalPath);
	
	
	harbour.setLocation(new Location(0, 0));
	
	harbourSprite.setUntransformedArea(area);
	AffineTransform t = new AffineTransform();
	t.setToTranslation(0, 0);
	harbourSprite.setTransform(t);
	harbourSprite.setTransformedArea(area.createTransformedArea(t));
	
	harbourSprite.setShowSprite(false);
	
	
	harbour.setSprite(harbourSprite);
	
	return harbour;
    }
    
    private CharacterIsland createIsland()
    {
	
	GameEngine ge = GameEngine.getInstance();
	Renderer renderer = Renderer.getInstance();
	
	
	CharacterIsland island = new CharacterIsland();
	
	
	SpriteIsland islandSprite = new game.SpriteIsland(island);
	
	GeneralPath generalPath = new GeneralPath();
	island.setLocation(new Location(0, 0));
	int[] locations = Util.getIslandData();
	Area area = createAreaFromLocations(locations, generalPath);
	
	islandSprite.setUntransformedArea(area);
	AffineTransform t = new AffineTransform();
	t.setToIdentity();
	islandSprite.setTransform(t);
	
	islandSprite.setTransformedArea(area.createTransformedArea(t));
	
	islandSprite.setShowSprite(false);
	
	
	island.setSprite(islandSprite);
	
	return island;
	
    }
    
    private CharacterBase createOctopus()
    {
	
	
	Renderer renderer = Renderer.getInstance();
	
	CharacterBase octopus = new CharacterObstacle();
	
	Image[] images = new Image[1];
	images[0] = Util.imageResources.get("OCTOPUS");
	SpriteImage octopusSprite = new SpriteImage(images, octopus);
	
	Ellipse2D boundingEllipse = new Ellipse2D.Double(0, 0,
		images[0].getWidth(renderer),
		images[0].getHeight(renderer));
	
	Area area = new Area(boundingEllipse);
	
	//MoveAngledAccelerate move, double x, double y, double pRandomPhase, CharacterBase owner,
	//double swayH, double swayV)
	MoveSwaying move = new MoveSwaying(null, 0,0, Math.random(),octopus, 100.0,1.0);
	octopus.setMoveBehaviour(move);
	
	octopusSprite.setUntransformedArea(area);
	AffineTransform t = new AffineTransform();
	
	t.setToIdentity();
	
	octopusSprite.setTransform(t);
	octopusSprite.setTransformedArea(area.createTransformedArea(t));
	octopusSprite.setSqueezeImageIntoTransformedArea(true);
	octopusSprite.setShowSprite(true);
	octopus.setSprite(octopusSprite);
	
	return octopus;
	
    }
    
    private CharacterGoal createGoal()
    {
	
	
	Renderer renderer = Renderer.getInstance();
	
	CharacterGoal goal = new CharacterGoal();
	
	
	SpriteGoal goalSprite = new SpriteGoal(goal);
	
	Area area = new Area(new Rectangle(0, 0, 100, 50));
	
	goalSprite.setUntransformedArea(area);
	AffineTransform t = new AffineTransform();
	t.setToTranslation(renderer.getWidth()-100, renderer.getHeight()-50);
	goalSprite.setTransform(t);
	
	goalSprite.setTransformedArea(area.createTransformedArea(t));
	
	goalSprite.setShowSprite(true);
	
	
	goal.setSprite(goalSprite);
	
	return goal;
	
    }
    
    public CharacterObstacle createBuoy()
    {
	Renderer renderer = Renderer.getInstance();
	CharacterObstacle buoy = new CharacterObstacle();
	Sprite sprite = new SpriteBuoy(buoy);
	double randomX = (Math.random() * renderer.getWidth());
	double randomY = (Math.random() * renderer.getHeight());
	
	buoy.setLocation(randomX, randomY);
	
	Move sway = new game.MoveSwaying(null, randomX, randomY, randomY, buoy, 1, 2);
	buoy.setMoveBehaviour(sway);
	int size= Util.getObstacleSize();
	Area a = new Area(new java.awt.geom.Ellipse2D.Double(randomX - size / 2, randomY - size / 2, size, size));
	sprite.setUntransformedArea(a);
	AffineTransform t = new AffineTransform();
	t.setToTranslation(randomX,randomY);
	
	sprite.setTransformedArea(a);
	sprite.setShowSprite(true);
	sprite.setHeight(size);
	sprite.setWidth(size);
	
	buoy.setSprite(sprite);
	
	return buoy;
	
    }
    public CharacterBase createCharacter(Util.CharacterType characterType)
    {
	CharacterBase retCharacter = null;
	switch (characterType)
	{
	    case BOAT:
		retCharacter = createBoat();
		break;
	    case COMPUTER_BOAT:
		retCharacter = createComputerBoat();
		break;
	    case HARBOUR:
		retCharacter = createHarbour();
		break;
	    case BUOY:
		retCharacter = createBuoy();
		break;
	    case ISLAND:
		retCharacter = createIsland();
		break;
	    case OCTOPUS:
		retCharacter = createOctopus();
		break;
	    case GOAL:
		retCharacter = createGoal();
		break;
		
	}
	
	return retCharacter;
    }
}

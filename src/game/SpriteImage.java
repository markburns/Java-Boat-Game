/*
 * Sprite2dImageRotatable.java
 *
 * Created on 18 November 2007, 20:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package game;



import java.awt.*;
import java.awt.geom.*;
import java.awt.image.ImageObserver;
import java.util.*;

/**
 *
 * @author Mark
 */
public class SpriteImage extends Sprite
{
    
    private Image[]       images;
    
    private int           frame;
    private CharacterBase owner;
    
    private ImageObserver imageObserver;
    
    public Image[] getImages()
    {
	return images;
    }
    
    
    public void setFrame(int frame)
    {
	this.frame = frame;
    }
    
    
    
    public CharacterBase getOwner()
    {
	return owner;
    }
    
    public void setImages(Image[] images)
    {
	this.images = images;
    }
    
    public int getFrame()
    {
	return frame;
    }
    
    public Image getDisplayRepresentation()
    {
	return this.images[frame];
    }
    
    public void setImageObserver(ImageObserver observer)
    {
	this.imageObserver = observer;
	
    }
    boolean squeezeImageIntoTransformedArea=false;
    
    public Graphics paint(Graphics g, Location loc)
    {
	Graphics2D gg = (Graphics2D)g;
	
	Image img;
	img = getImages()[getFrame()];
	int x,y;
	x = (int)loc.getX();
	y = (int)loc.getY();
	
	
	try
	{
	    gg.setColor(new Color(255,0,0,100));
	    //gg.fill(this.getTransformedArea());
	    Rectangle2D b = getTransformedArea().getBounds2D();
	    if(squeezeImageIntoTransformedArea){
	    gg.drawImage(
		    img,
		    (int)b.getX(),
		    (int)b.getY(),
		    (int)b.getWidth(), 
		    (int)b.getHeight(),
		    getImageObserver());
	    }
	    else{
		gg.drawImage(img,getTransform(),getImageObserver());
	    }
	    
	}
	catch(NullPointerException ex)
	{
	    ex.printStackTrace();
	    setShowSprite(false);
	}
	
	return (Graphics)gg;
	
    }
    
    
    public ImageObserver getImageObserver()
    {
	return imageObserver;
    }
    
    
    
    
    /**
     * Creates a new instance of SpriteImage
     *
     *
     *
     *
     * @param Image img takes a single image to act as the only frame
     *     of an animation
     */
    public SpriteImage(Image[] images, CharacterBase owner)
    {
	this.owner = owner;
	this.images = images;
	frame = 0;
	
	this.setUntransformedArea(new Area(
		new Rectangle(
		0
		,0
		,images[0].getWidth(game.Renderer.getInstance())
		,images[0].getHeight(game.Renderer.getInstance())
		)
		));
    }

    public boolean isSqueezeImageIntoTransformedArea()
    {
	    return squeezeImageIntoTransformedArea;
    }

    public void setSqueezeImageIntoTransformedArea(boolean squeezeImageIntoTransformedArea)
    {
	    this.squeezeImageIntoTransformedArea = squeezeImageIntoTransformedArea;
    }
    
    
}

package game.sprite;

import game.movement.Location;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public abstract class Sprite
{
    
    private boolean showSprite = false;
    
    public abstract Graphics paint(Graphics g, Location loc);
    private Area unTransformedArea;
    private java.awt.geom.AffineTransform transform = new java.awt.geom.AffineTransform();
    private Area transformedArea;
    
    /** Creates a new instance of Sprite */
    public Sprite()
    {
	
    }
    
    public double getHeight()
    {
	return unTransformedArea.getBounds().getHeight();
    }
    
    public double getWidth()
    {
	return unTransformedArea.getBounds().getWidth();
    }
    
    public void setHeight(double height)
    {
	AffineTransform t = new AffineTransform();
	t.setToScale(1, height / this.getHeight());
	
	this.getUntransformedArea().transform(t);
    }
    
    public void setWidth(double width)
    {
	
	AffineTransform t = new AffineTransform();
	t.setToScale(width / this.getWidth(), 1);
	
	this.getUntransformedArea().transform(t);
    }
    
    public Area getUntransformedArea()
    {
	return unTransformedArea;
    }
    
    public void setUntransformedArea(Area area)
    {
	this.unTransformedArea = area;
    }
    
    public void setTransform(AffineTransform transform)
    {
	this.transform = transform;
    }
    
    public AffineTransform getTransform()
    {
	return transform;
    }
    
    public Area getTransformedArea()
    {
	return transformedArea;
    }
    
    public Rectangle getBounds(){
        return transformedArea.getBounds();
    }
    
    public void setTransformedArea(Area area)
    {
	this.transformedArea = area;
    }
    
    public void setShowSprite(boolean showSprite)
    {
	this.showSprite = showSprite;
    }
    
    public boolean isSpriteShown()
    {
	return showSprite;
    }
}

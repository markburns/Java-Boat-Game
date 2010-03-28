/*
 * Move.java
 *
 * Created on 18 November 2007, 17:54
 *
 */

package game;

/**
 *
 * @author Mark
 */
public class Move
{
    
    /**
     * Creates a new instance of Move
     */
    public Move()
    {
    }
    
    
    private double acceleration;
    private double velocity;
    private double maxVelocity;
    private double angle;
    private double angularVelocity;
    private double angularMaxVelocity;
    private double angularAcceleration;
    private double xVelocity;
    private double yVelocity;
    
    public Location go(Location location)
    {
	double x = location.getX();
	double y = location.getY();
	
	
	if (xVelocity>0.0)
	{
	    if (xVelocity>maxVelocity)
		xVelocity=maxVelocity;
	}
	else
	{
	    if (xVelocity<-maxVelocity)
	    {
		xVelocity=-maxVelocity;
	    }
	}
	
	
	if (yVelocity>0.0)
	{
	    if (yVelocity>maxVelocity)
		yVelocity=maxVelocity;
	}
	else
	{
	    if (yVelocity<-maxVelocity)
	    {
		yVelocity=-maxVelocity;
	    }
	}
	
	x+= xVelocity;
	y+= yVelocity;
	location.setLocation(x,y);
	return location;
    }
    
    public Location brake(Location location)
    {
	double x = location.getX();
	double y = location.getY();
	
	x+= xVelocity;
	y+= yVelocity;
	location.setLocation(x,y);
	return location;
    }
    
    
    
    
    
    
    /**Set the maximum velocity of this game character in the x axis
     
     */
    public void setMaxVelocity(double x)
    {
	this.maxVelocity= x;
	
    }
    
    /**Performs a move representing 'right'
     @param location a Location representing the current position
     of the physical object
     @return Returns an updated Location object
     */
    public Location goRight(Location location)
    {
	
	
	double x=location.getX();
	velocity+=acceleration;
	if (velocity>maxVelocity)
	{
	    velocity = maxVelocity;
	}
	x= x + velocity;
	location.setX(x);
	
	return location;
	
    }
    
    /**Performs a move representing 'Left'
     @param location a Location representing the current position
     of the physical object
     @return Returns an updated Location object
     */
    public Location goLeft(Location location)
    {
	double x=location.getX();
	velocity-=acceleration;
	if ((-velocity)>(-maxVelocity))
	{
	    velocity = -maxVelocity;
	}
	x= x - velocity;
	location.setX(x);
	
	return location;
    }
    
    
    /**Performs a move representing 'Down'
     @param location a Location representing the current position
     of the physical object
     @return Returns an updated Location object
     */
    public Location goDown(Location location)
    {
	double y=location.getY();
	velocity+=acceleration;
	if ((velocity)>(maxVelocity))
	{
	    velocity = maxVelocity;
	}
	y= y + velocity;
	location.setY(y);
	return location;
    }
    
    /**Performs a move representing 'up'
     @param location a Location representing the current position
     of the physical object
     @return Returns an updated Location object
     */
    public Location goUp(Location location)
    {
	double y = location.getY();
	velocity-=acceleration;
	if ((-velocity)>(-maxVelocity))
	{
	    velocity = -maxVelocity;
	}
	y= y + velocity;
	location.setY(y);
	return location;
    }
    
    public double getMaxVelocity()
    {
	return maxVelocity;
    }
    
    
    public void setAcceleration(double acceleration)
    {
	this.acceleration =acceleration;
    }
    
    public void setVelocity(double velocity)
    {
	this.velocity = velocity;
    }
    
    public double getVelocity()
    {
	return velocity;
    }
    
    public double getAcceleration()
    {
	return acceleration;
    }
    
    public double getAngle()
    {
	return angle;
    }
    
    public void setAngle(double angle)
    {
	this.angle = angle;
    }
    
    public double getAngularVelocity()
    {
	return angularVelocity;
    }
    
    public void setAngularVelocity(double angularVelocity)
    {
	this.angularVelocity = angularVelocity;
    }
    
    public double getAngularMaxVelocity()
    {
	return angularMaxVelocity;
    }
    
    public void setAngularMaxVelocity(double angularMaxVelocity)
    {
	this.angularMaxVelocity = angularMaxVelocity;
    }
    
    public void setAngularAcceleration(double angularAcceleration)
    {
	this.angularAcceleration = angularAcceleration;
    }
    
    public double getAngularAcceleration()
    {
	return angularAcceleration;
    }
    
    public double getXVelocity()
    {
	return xVelocity;
    }
    
    public double getYVelocity()
    {
	return yVelocity;
    }
    
    public void setXVelocity(double xVelocity)
    {
	this.xVelocity = xVelocity;
    }
    
    public void setYVelocity(double yVelocity)
    {
	this.yVelocity = yVelocity;
    }
    
    
    
}

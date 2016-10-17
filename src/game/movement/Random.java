
package game.movement;
	/*
	 * This class determines the randomness of the AI objects movement
	 */
public class Random extends AngledAcceleration
{
    
    public Random(AngledAcceleration move)
    {
	this.move = move;
    }
    AngledAcceleration move;
    int framesBetweenTurns=200;
    int frames;

    @Override
    public Location go(Location location)
    {
	double angle = getAngle();
	double velocity = getVelocity();
	double maxVelocity = getMaxVelocity();
	
	
	if (frames>=framesBetweenTurns)
	{
	    frames=0;
	    angle+=Math.random();
	    if (angle>2*Math.PI)
	    {
		angle-=(2*Math.PI);
	    }
	    velocity += (Math.random()-0.5)*maxVelocity;
	    if (velocity>maxVelocity){
		velocity = maxVelocity;
	    }
	    if (velocity<-maxVelocity){
		velocity = -maxVelocity;
	    }
	    
	    frames++;
	}
	setAngle(angle );
	setVelocity(velocity );
	return super.go(location);
    }

    
    @Override
    public Location goDown(Location location)
    {
	
	if (move != null)
	{
	    
	    return move.goDown(location);
	}
	else
	{
	    return super.goDown(location);
	    
	}
    }
    
    @Override
    public Location goLeft(Location location)
    {
	
	if (move != null)
	{
	    
	    return move.goLeft(location);
	}
	else
	{
	    return super.goLeft(location);
	    
	}
	
    }
    
    @Override
    public Location goRight(Location location)
    {
	if (move != null)
	{
	    
	    return move.goRight(location);
	}
	else
	{
	    return super.goRight(location);
	    
	}
	
    }
    
    
    @Override
    public void setYVelocity(double yVelocity)
    {
	move.setYVelocity(yVelocity);
    }
    
    @Override
    public void setXVelocity(double xVelocity)
    {
	move.setXVelocity(xVelocity);
    }
    
    @Override
    public void setVelocity(double velocity)
    {
	move.setVelocity(velocity);
    }
    
    @Override
    public void setMaxVelocity(double x)
    {
	move.setMaxVelocity(x);
    }
    
    @Override
    public void setFriction(double friction)
    {
	move.setFriction(friction);
    }
    
    @Override
    public void setBrake(double brake)
    {
	move.setBrake(brake);
    }
    
    @Override
    public void setAngularVelocity(double angularVelocity)
    {
	move.setAngularVelocity(angularVelocity);
    }
    
    @Override
    public void setAngularMaxVelocity(double angularMaxVelocity)
    {
	move.setAngularMaxVelocity(angularMaxVelocity);
    }
    
    @Override
    public void setAngularFriction(double angularFriction)
    {
	move.setAngularFriction(angularFriction);
    }
    
    @Override
    public void setAngularAcceleration(double angularAcceleration)
    {
	move.setAngularAcceleration(angularAcceleration);
    }
    
    @Override
    public void setAngle(double angle)
    {
	move.setAngle(angle);
    }
    
    
    @Override
    public void setAcceleration(double acceleration)
    {
	move.setAcceleration(acceleration);
    }
    
    @Override
    public double getYVelocity()
    {
	return move.getYVelocity();
    }
    
    @Override
    public double getXVelocity()
    {
	return move.getXVelocity();
    }
    
    @Override
    public Location turn(Location location)
    {
	return move.turn(location);
    }
    
    @Override
    public double getVelocity()
    {
	return move.getVelocity();
    }
    
    @Override
    public double getMaxVelocity()
    {
	return move.getMaxVelocity();
    }
    
    @Override
    public double getFriction()
    {
	return move.getFriction();
    }
    
    @Override
    public double getBrake()
    {
	return move.getBrake();
    }
    
    @Override
    public double getAngularVelocity()
    {
	return move.getAngularVelocity();
    }
    
    @Override
    public double getAngularMaxVelocity()
    {
	return move.getAngularMaxVelocity();
    }

    
    @Override
    public double getAngularAcceleration()
    {
	return move.getAngularAcceleration();
    }
    
    @Override
    public double getAngle()
    {
	return move.getAngle();
    }
    
    
    @Override
    public double getAcceleration()
    {
	return move.getAcceleration();
    }
    @Override
    public Location goUp(Location location)
    {
	if (move != null)
	{
	    
	    return move.goUp(location);
	}
	else
	{
	    return super.goUp(location);
	    
	}
    }
    
}

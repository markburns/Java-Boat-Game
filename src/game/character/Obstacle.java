package game.character;


import java.awt.geom.*;


public class Obstacle extends Character
{
    private double angMomentum= 0;
    private double xAngMomentum;
    private double phase=0.0;
    private double yOffset = 0.0;
    private double xOffset = 0.0;
    private double randomMomentum;
    private double randomPhase;
    /** Creates a new instance of CharacterObstacle */
    public Obstacle()
    {
	setxAngMomentum(getLocation().getX());
	setPhase(getPhase() + randomPhase);
    }
    @Override
    public void update()
    {
	
	setLocation(getMoveBehaviour().go(getLocation()));
	AffineTransform transform = new AffineTransform();
	transform.setToTranslation(getX(),getY());
	Area area = new Area(getSprite().getUntransformedArea());
	area.transform(transform);
	getSprite().setTransformedArea(area);
	
    }
    
    @Override
    public void collide()
    {
    	
    }
	public double getAngMomentum() {
		return angMomentum;
	}
	public void setAngMomentum(double angMomentum) {
		this.angMomentum = angMomentum;
	}
	
	public double getxAngMomentum() {
		return xAngMomentum;
	}
	public void setxAngMomentum(double xAngMomentum) {
		this.xAngMomentum = xAngMomentum;
	}
	
	public double getPhase() {
		return phase;
	}
	public void setPhase(double phase) {
		this.phase = phase;
	}
	public double getyOffset() {
		return yOffset;
	}
	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}
	
	public double getxOffset() {
		return xOffset;
	}
	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}
	
	public double getRandomMomentum() {
		return randomMomentum;
	}
	public void setRandomMomentum(double randomMomentum) {
		this.randomMomentum = randomMomentum;
	}
	
	public double getRandomPhase() {
		return randomPhase;
	}
	public void setRandomPhase(double randomPhase) {
		this.randomPhase = randomPhase;
	}
    
}


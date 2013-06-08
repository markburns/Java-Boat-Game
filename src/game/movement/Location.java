
package game.movement;

public class Location
{

    
    private double x;
    private double y;


    /** Creates a new instance of Location */
    public Location(double x, double y)
    {
	this.x=x;
	this.y=y;

    }
    
    public Location(int x, int y)
    {
	this.x=(double)x;
	this.y=(double)y;
	
    }
    @Override
    public String toString(){
    return (String)("x:"+String.valueOf(x)+" y:"+String.valueOf(y));
}
    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    

    public double getY()
    {
        return y;
    }

    

    public void setY(double y)
    {
	    this.y = y;
    }
    
    public void setLocation(double x, double y){
	this.x=x;
	this.y=y;

    }
    


}

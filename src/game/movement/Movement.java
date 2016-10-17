package game.movement;

public class Movement {

    public Movement() {
    }
    
    private double velocity = 0;
    private double xVelocity = 0;
    private double yVelocity = 0;
    
    private double acceleration = 0;
    
    private double angularVelocity = 0;
    private double angle = 0;
    
    
    
    private double maxVelocity = 0;
    
    
    private double angularMaxVelocity = 0;
    private double angularAcceleration= 0;
    
    

    private double pinValue(double value, double max) {
        if (value > 0.0) {
            if (value > max) {
                value = max;
            }
        } else {
            if (value < -max) {
                value = -max;
            }
        }
        return value;
    }

    public Location go(Location location) {
        double x = location.getX();
        double y = location.getY();

        xVelocity = pinValue(xVelocity, maxVelocity);
        yVelocity = pinValue(yVelocity, maxVelocity);

        x += xVelocity;
        y += yVelocity;
        location.setLocation(x, y);
        return location;
    }

    public Location brake(Location location) {
        double x = location.getX();
        double y = location.getY();

        x += xVelocity;
        y += yVelocity;
        location.setLocation(x, y);
        return location;
    }

    /**
     * Set the maximum velocity of this game character in the x axis
     *
     */
    public void setMaxVelocity(double x) {
        this.maxVelocity = x;

    }

    /**
     * Performs a move representing 'right'
     *
     * @param location a Location representing the current position of the
     * physical object
     * @return Returns an updated Location object
     */
    public Location goRight(Location location) {
        double x = location.getX();
        velocity += acceleration;
        velocity = pinValue(velocity, maxVelocity);

        x = x + velocity;
        location.setX(x);

        return location;
    }

    /**
     * Performs a move representing 'Left'
     *
     * @param location a Location representing the current position of the
     * physical object
     * @return Returns an updated Location object
     */
    public Location goLeft(Location location) {
        double x = location.getX();
        velocity -= acceleration;

        if ((-velocity) > (-maxVelocity)) {
            velocity = -maxVelocity;
        }
        x = x - velocity;
        location.setX(x);

        return location;
    }

    /**
     * Performs a move representing 'Down'
     *
     * @param location a Location representing the current position of the
     * physical object
     * @return Returns an updated Location object
     */
    public Location goDown(Location location) {
        double y = location.getY();
        velocity += acceleration;
        if ((velocity) > (maxVelocity)) {
            velocity = maxVelocity;
        }
        y = y + velocity;
        location.setY(y);
        return location;
    }

    /**
     * Performs a move representing 'up'
     *
     * @param location a Location representing the current position of the
     * physical object
     * @return Returns an updated Location object
     */
    public Location goUp(Location location) {
        double y = location.getY();
        velocity -= acceleration;
        if ((-velocity) > (-maxVelocity)) {
            velocity = -maxVelocity;
        }
        y = y + velocity;
        location.setY(y);
        return location;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public double getAngularMaxVelocity() {
        return angularMaxVelocity;
    }

    public void setAngularMaxVelocity(double angularMaxVelocity) {
        this.angularMaxVelocity = angularMaxVelocity;
    }

    public void setAngularAcceleration(double angularAcceleration) {
        this.angularAcceleration = angularAcceleration;
    }

    public double getAngularAcceleration() {
        return angularAcceleration;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
}

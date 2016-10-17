package game.movement;

public class AngledAcceleration extends Movement {

    private double angularAcceleration = 0;
    private double friction = 0;
    private double brake = 0;
    private double angularFriction = 0;

    @Override
    public Location goUp(Location location) {
    	/*
    	 *get the velocity and the acceleration to determine the 
         *new location of the object (if going forward)
         */
        this.setVelocity(getVelocity() + getAcceleration());
        location = changedAccelerate(location);
        return location;
    }

    private Location changedAccelerate(Location location) {
    	/*
    	 *sets the velocity based on the acceleration to determine 
    	 *the new location of the object (if theres no acceleration) 
    	 */
        setXVelocity(Math.cos(getAngle()) * getVelocity());
        setYVelocity(Math.sin(getAngle()) * getVelocity());

        location = super.go(location);
        return location;
    }

    @Override
    public Location goDown(Location location) {
    	/*
    	 *get the velocity and the acceleration to determine the 
         *new location of the object (if going backwards)
         */
        this.setVelocity(getVelocity() - getAcceleration());
        location = changedAccelerate(location);
        return location;

    }

    public Location turn(Location location) {

        double velocity = getVelocity();
        double xVelocity = 0;
        double yVelocity = 0;

        double angle = getAngle();
        double angularVelocity = getAngularVelocity();
        angle += angularVelocity;
        angle = clampAngle(angle);
        
        /*
         * Changes the angle of the boat (if going left or right)
         */
        xVelocity = Math.cos(angle) * velocity;
        yVelocity = Math.sin(angle) * velocity;

        setAngle(angle);
        setAngularVelocity(angularVelocity);
        setVelocity(velocity);
        setXVelocity(xVelocity);
        setYVelocity(yVelocity);

        return location;
    }

    /**
     * increases angular velocity (accelerates in a clockwise direction)
     */
    @Override
    public Location goRight(Location location) {
        setNewAngularVelocity("+");
        return turn(location);
    }

    /**
     * decreases angular velocity (accelerates in an anti clockwise direction)
     */
    @Override
    public Location goLeft(Location location) {
        setNewAngularVelocity("-");
        return turn(location);
    }

    private void setNewAngularVelocity(String type) {
        double velocity;
        if (type == "+") {
            velocity = getAngularVelocity() + getAngularAcceleration();
        } else {
            velocity = getAngularVelocity() - getAngularAcceleration();
        }
        double newVelocity = pin(velocity, getAngularMaxVelocity());

        setAngularVelocity(newVelocity);
    }

    private double pin(double value, double max) {
        if (value >= max) {
            value = max;
        } else if (value < -max) {
            value = -max;
        }

        return value;
    }

    private double damp(double value, double factor) {
        if (value > 0.0) {
            value -= factor;
            if (value < 0.0) {
                value = 0.0;
            }
        } else {
            value += factor;
            if (value > 0.0) {
                value = 0.0;
            }
        }

        return value;
    }

    @Override
    public Location go(Location location) {
        recalculateAngularVelocity();
        recalculateAngle();

        recalculateVelocity();
        recalculateYVelocity();
        recalculateXVelocity();

        location = super.go(location);
        return location;
    }

    private void recalculateVelocity() {
        double velocity = damp(getVelocity(), this.friction);
        setVelocity(velocity);

    }

    private void recalculateAngularVelocity() {
        double angularVelocity = getAngularVelocity();

        setAngularVelocity(damp(angularVelocity, this.angularFriction));
    }

    private void recalculateAngle() {
        double angle = getAngle();
        angle += getAngularVelocity();
        angle = clampAngle(angle);
        setAngle(angle);
    }

    private double clampAngle(double angle) {
        if (angle > 0) {
            while (angle > Math.PI) {
                angle = angle - (2 * Math.PI);
            }
        }
        if (angle < 0) {
            while (angle < (-Math.PI)) {
                angle = angle + (2 * Math.PI);
            }
        }
        return angle;
    }

    public AngledAcceleration() {
    }

    @Override
    public void setAngularAcceleration(double angularAcceleration) {
        this.angularAcceleration = angularAcceleration;
    }

    @Override
    public double getAngularAcceleration() {
        return angularAcceleration;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }

    public double getFriction() {
        return friction;
    }

    public double getBrake() {
        return brake;
    }

    public void setBrake(double brake) {
        this.brake = brake;
    }

    public double getAngularFriction() {
        return angularFriction;
    }

    public void setAngularFriction(double angularFriction) {
        this.angularFriction = angularFriction;
    }

    private void recalculateXVelocity() {
        double xVelocity;
        xVelocity = Math.cos(getAngle()) * getVelocity();
        setXVelocity(xVelocity);
    }

    private void recalculateYVelocity() {
        double yVelocity;
        yVelocity = Math.sin(getAngle()) * getVelocity();
        setYVelocity(yVelocity);
    }
}

package game.character;

import game.GameEngine;
import game.InputController;
import game.GameWindow;
import game.movement.Location;
import game.movement.AngledAcceleration;
import game.sprite.Sprite;
import game.Util;
import java.awt.geom.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Boat extends Moveable {

    static Logger logging = Logger.getLogger(Boat.class);
    
    final String ASSERTENERGY = "Energy cant be negative";
    final String LOGSETENERGY = "setEnergy value: ";
    final String LOGGETENERGY = "getEnergy value: ";
    final String LOGCOLLISION = "Reducing energy, collision";
    final String MSGERROMOUSE = "Error! Mouse bug";
    final String LOGDXCLICK = "dx click:";
    final String LOGDYCLICK = "dy click:";
    final String LOGMOUSE = "mouse angle:";
    final String ASSERTMOUSE = "Angle cant be more than -4 or 4";
    
    Location pivotPoint = null; //sets the x y point to the boat
    private int energy = 100; //energy of the boat

    /*
     * function that sets the energy of the boat
     * @param energy
     */
    public void setEnergy(int energy) {
        assert(energy >= 0) : ASSERTENERGY;
        logging.debug( LOGSETENERGY + energy);
        this.energy = energy;
    }

    /*
     * function that returns the energy of the boat
     * @return energy   the energy of the boat
     */
    public int getEnergy() {
        logging.debug( LOGGETENERGY + energy);
        return energy;
    }

    /*
     * method that reduce energy of the boat
     */
    private void reduceEnergy() {
        int reduceEnergy = getEnergy(); //auxiliary int for reducing energy
        reduceEnergy--;

        setEnergy(reduceEnergy);

        if (reduceEnergy <= 0) {

            GameEngine.getInstance().gameOver();
        } else {

            GameWindow.getInstance().setEnergyBarLevel(reduceEnergy);
        }

    }

    /*
     * function that reduces energy after collision
     * @param character   boat that is defined as a player
     */
    public void collision(Character character) {
        logging.debug(LOGCOLLISION);
        reduceEnergy();
    }
    /*
     * basic constructor
     */
    public Boat() {
    }

    /*
     * function that sets the angle that the boat is navigating
     * @param value  initial angle of the boat
     * @return value  new angle defined by function
     */
    private double pinAngle(double value) {
        if (Math.abs(value) > Math.PI) {
            while (value > Math.PI) {
                value = value - (2 * Math.PI);
            }
            while (value < -Math.PI) {
                value = value + (2 * Math.PI);
            }
        }
        return value;

    }
    
    
    /*
     * function that processes mouse click
     */
    private void processMouse() {
        logging.setLevel(Level.INFO);
        
        Point2D point = this.getController().getMouseLocation(); //mouse pointing

        Location dest = new Location(point.getX(), point.getY()); //game coordinates

        double dy = dest.getY() - y();
        double dx = dest.getX() - x();
        assert(dx < 1920 && dx > -1920) : MSGERROMOUSE;
        assert(dy < 1080 && dy > -1080) : MSGERROMOUSE;  
        logging.debug(LOGDXCLICK + dx);
        logging.debug(LOGDYCLICK + dy);
        double destinationAngle = Math.atan2(dy, dx);

        AngledAcceleration mouseMove = (AngledAcceleration) getMoveBehaviour();
        double angleDelta = destinationAngle - mouseMove.getAngle();

        angleDelta = pinAngle(angleDelta);
        logging.debug(LOGMOUSE + angleDelta);
        assert(angleDelta > -4 && angleDelta < 4 ) : ASSERTMOUSE;
        
        if (Math.abs(angleDelta) < (Math.PI / 2.0)) {
            if ((angleDelta < Math.PI) && (angleDelta > 0)) {
                setLocation(mouseMove.goRight(getLocation()));
            }

            if ((angleDelta < 0) && (angleDelta > -Math.PI)) {
                setLocation(mouseMove.goLeft(getLocation()));
            }
            //accelerate
            setLocation(mouseMove.goUp(getLocation()));
        } else {
            mouseMove.setVelocity(mouseMove.getVelocity() * 0.95);

            if ((angleDelta > 0)) {

                setLocation(mouseMove.goRight(getLocation()));
            }

            if ((angleDelta < 0)) {
                setLocation(mouseMove.goLeft(getLocation()));
            }

        }



        setLocation(mouseMove.goUp(getLocation()));


    }
    
    @SuppressWarnings("unused")
    private void processKeyPressSquare(InputController.Control keypress) {
        
        logging.debug("keypressed: " + keypress);
        switch (keypress) {
            case UP:
                setLocation(getMoveBehaviour().goUp(getLocation()));

                break;
            case DOWN:
                setLocation(getMoveBehaviour().goDown(getLocation()));
                break;
            case LEFT:
                setLocation(getMoveBehaviour().goLeft(getLocation()));
                break;
            case RIGHT:
                setLocation(getMoveBehaviour().goRight(getLocation()));
                break;
            case BRAKE:
                setLocation(getMoveBehaviour().brake(getLocation()));
                break;
            case STORM:
                break;
            case PAUSE: //don't update
//      GameEngine.getInstance().togglePause();
                break;
            default:

                //do nothing
                break;
        }

    }
    
    /*
     * function that processes the WASD and arrows controllers
     * @param keypress   key pressed by player
     */
    private void processKeyPressRotating(InputController.Control keypress) {
        logging.setLevel(Level.INFO);
        logging.debug("keypressed: " + keypress);
        try{
        switch (keypress) {
            case UP:
                setLocation(getMoveBehaviour().goUp(getLocation()));
                break;
            case DOWN:
                setLocation(getMoveBehaviour().goDown(getLocation()));
                break;
            case LEFT:
                setLocation(getMoveBehaviour().goLeft(getLocation()));
                break;
            case RIGHT:
                setLocation(getMoveBehaviour().goRight(getLocation()));
                break;
            case BRAKE:
                setLocation(getMoveBehaviour().brake(getLocation()));
                break;
            case STORM:
                break;
            case PAUSE: //don't update
//      GameEngine.getInstance().togglePause();
                break;
            default:

                //do nothing
                break;
        }
        }catch(NullPointerException e){
            System.out.println("Erro: " + e);
        }
        
    }
    /*
     * (non-Javadoc)
     * @see game.character.Moveable#update()
     */
    @Override
    public void update() {
        InputController controller = getController();
        if (controller.keyPressEventsPending()) {
            try{
                InputController.Control pressedControl = controller.getPressedControl();
                processKeyPressRotating(pressedControl);
            }catch(NullPointerException|IndexOutOfBoundsException e){
                System.out.println("Erro: " + e);
                
            }
        } else {
            setLocation(getMoveBehaviour().go(getLocation()));
        }

        if (controller.keyHeldEventsPending()) {
            int count = 0;
            while (count <= controller.getNumberOfHeldControls()) {
                InputController.Control control = controller.getHeldControl(count);
                processKeyPressRotating(control);
                count++;
            }
        }

        if (controller.isMouseHeld()) {
            processMouse();
        }

        setTransform(pivotPoint);

        if (checkScreenEdge()) {
            this.getMoveBehaviour().setVelocity(getMoveBehaviour().getVelocity() / 10);
        }

        GameWindow.getInstance()
                .updateControlPanel(this);
    }

    /*
     * (non-Javadoc)
     * @see game.character.Character#setSprite(game.sprite.Sprite)
     */
    @Override
    public void setSprite(Sprite sprite) {
        super.setSprite(sprite);
        pivotPoint = Util.getBoatPivotPoint(sprite);
    }
}

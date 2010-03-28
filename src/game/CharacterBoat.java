/*
 * CharacterBoat.java
 *
 * Created on 16 November 2007, 12:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game;

import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

/**
 *
 * @author Mark
 */
public class CharacterBoat extends CharacterBoatBase {

    private ControlMethod controlMethod = ControlMethod.NULL;
    Location pivotPoint;
    private int energy=100;

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    enum ControlMethod {

        NULL,
        ROTATING,
        SQUARE;
    }

    /**
     * Creates a new instance of CharacterBoat
     */
    public CharacterBoat() {

    }

    private void processMouse() {
        Point2D p = this.getController().getMouseLocation();
        try {

            Location dest = new Location(p.getX(), p.getY());
            double x = this.getSprite().getTransformedArea().getBounds().getCenterX();
            double y = this.getSprite().getTransformedArea().getBounds().getCenterY();
            double dy = dest.getY() - y;
            double dx = dest.getX() - x;

            double destinationAngle = Math.atan2(dy, dx);

            MoveAngledAccelerate m = (MoveAngledAccelerate) getMoveBehaviour();
            double angleDelta = destinationAngle - m.getAngle();

            if (Math.abs(angleDelta) > Math.PI) {
                while (angleDelta > Math.PI) {
                    angleDelta = angleDelta - (2 * Math.PI);
                }
                while (angleDelta < -Math.PI) {
                    angleDelta = angleDelta + (2 * Math.PI);
                }
            }
            if (Math.abs(angleDelta) < (Math.PI / 2.0)) {
                if ((angleDelta < Math.PI) && (angleDelta > 0)) {
                    setLocation(m.goRight(getLocation()));
                }

                if ((angleDelta < 0) && (angleDelta > -Math.PI)) {
                    setLocation(m.goLeft(getLocation()));
                }
                //accelerate
                setLocation(m.goUp(getLocation()));
            } else {
                m.setVelocity(m.getVelocity() * 0.95);

                if ((angleDelta > 0)) {

                    setLocation(m.goRight(getLocation()));
                }

                if ((angleDelta < 0)) {
                    setLocation(m.goLeft(getLocation()));
                }

            }



            setLocation(m.goUp(getLocation()));

        } catch (ClassCastException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }

    private void processKeyPressSquare(Controller.Control keypress) {
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
//		GameEngine.getInstance().togglePause();
                break;
            default:

                //do nothing
                break;
        }

    }

    private void processKeyPressRotating(Controller.Control keypress) {

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
//		GameEngine.getInstance().togglePause();
                break;
            default:

                //do nothing
                break;
        }

    }

    public ControlMethod getControlMethod() {
        return controlMethod;
    }

    public void update() {
        Controller controller = getController();
        if (controller.keyPressEventsPending()) {
            Controller.Control pressedControl = controller.getPressedControl();
            switch (this.controlMethod) {
                case ROTATING:
                    processKeyPressRotating(pressedControl);
                    break;
                case SQUARE:
                    processKeyPressSquare(pressedControl);
                case NULL:
                default:
                    break;
            }
        } else {
            setLocation(getMoveBehaviour().go(getLocation()));
        }

        if (controller.keyHeldEventsPending()) {
            int count = 0;
            while (count <= controller.getNumberOfHeldControls()) {
                Controller.Control c = controller.getHeldControl(count);
                processKeyPressRotating(c);
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

        GameWindow.getInstance().updateControlPanel(this);
    }

    public void setControlMethod(ControlMethod controlMethod) {
        this.controlMethod = controlMethod;
    }

    public void setSprite(Sprite sprite) {
        super.setSprite(sprite);
        pivotPoint = Util.getBoatPivotPoint(sprite);
    }
}

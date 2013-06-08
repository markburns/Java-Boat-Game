/*
 * CharacterBoat.java
 *
 * Created on 16 November 2007, 12:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game.character;

import game.InputController;
import game.GameWindow;
import game.movement.Location;
import game.movement.AngledAcceleration;
import game.sprite.Sprite;
import game.Util;
import java.awt.geom.*;

/**
 *
 * @author Mark
 */
public class Boat extends Moveable {

    Location pivotPoint;
    private int energy = 100;

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    /**
     * Creates a new instance of CharacterBoat
     */
    public Boat() {
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

            AngledAcceleration m = (AngledAcceleration) getMoveBehaviour();
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

    private void processKeyPressSquare(InputController.Control keypress) {
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

    private void processKeyPressRotating(InputController.Control keypress) {

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

    @Override
    public void update() {
        InputController controller = getController();
        if (controller.keyPressEventsPending()) {
            InputController.Control pressedControl = controller.getPressedControl();
            processKeyPressRotating(pressedControl);

        } else {
            setLocation(getMoveBehaviour().go(getLocation()));
        }

        if (controller.keyHeldEventsPending()) {
            int count = 0;
            while (count <= controller.getNumberOfHeldControls()) {
                InputController.Control c = controller.getHeldControl(count);
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

        GameWindow.getInstance()
                .updateControlPanel(this);
    }

    public void setSprite(Sprite sprite) {
        super.setSprite(sprite);
        pivotPoint = Util.getBoatPivotPoint(sprite);
    }
}

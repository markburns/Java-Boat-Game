package game.character;

import game.GameEngine;
import game.InputController;
import game.GameWindow;
import game.movement.Location;
import game.movement.AngledAcceleration;
import game.sprite.Sprite;
import game.Util;
import java.awt.geom.*;

public class Boat extends Moveable {

    Location pivotPoint;
    private int energy = 100;

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

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

    public void collision(Character character) {
        reduceEnergy();
    }

    public Boat() {
    }

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

    private void processMouse() {
        Point2D point = this.getController().getMouseLocation(); //mouse pointing

        Location dest = new Location(point.getX(), point.getY()); //game coordinates

        double dy = dest.getY() - y();
        double dx = dest.getX() - x();

        double destinationAngle = Math.atan2(dy, dx);

        AngledAcceleration mouseMove = (AngledAcceleration) getMoveBehaviour();
        double angleDelta = destinationAngle - mouseMove.getAngle();

        angleDelta = pinAngle(angleDelta);
        
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

    @Override
    public void setSprite(Sprite sprite) {
        super.setSprite(sprite);
        pivotPoint = Util.getBoatPivotPoint(sprite);
    }
}

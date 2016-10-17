package game.character;

import game.movement.Location;
import game.movement.Movement;
import java.awt.geom.*;

/**
 *
 * @author Mark
 */
public class EnemyBoat extends Moveable
{

    /** Creates a new instance of CharacterComputerBoat */
    public EnemyBoat()
    {

    }
    int randomLength = 0;
    int turnDuration = 15;
    boolean changeDirection = false;
    int i = 0;

    @Override
    public void update()
    {
        if (changeDirection)
        {
            turnDuration--;

            switch (i)
            {
                case 0:
                    setLocation(getMoveBehaviour().goRight(getLocation()));
                    break;
                case 1:
                    setLocation(getMoveBehaviour().goLeft(getLocation()));
                    break;
                default:
                    setLocation(getMoveBehaviour().goUp(getLocation()));
                    break;
            }
            if (turnDuration <= 0)
            {
                turnDuration = 15;
                i = (int) (2 * Math.random());
                changeDirection = false;
            }
        }
        else
        {
            randomLength--;
            setLocation(getMoveBehaviour().goUp(getLocation()));

            if (randomLength <= 0)
            {
                randomLength = (int) (Math.random() * 50);
                changeDirection = true;

            }

        }

        setLocation(getMoveBehaviour().go(getLocation()));


        Rectangle2D enemyBoat = this.getSprite().getUntransformedArea().getBounds2D();
        setTransform(new Location(enemyBoat.getCenterX(), enemyBoat.getCenterY()));
        if (checkScreenEdge())
        {
            getMoveBehaviour().setAngle(Math.PI + this.getMoveBehaviour().getAngle());
        }
    }

    


    @Override
    public void collide()
    {
        Movement moveAction = getMoveBehaviour();
//		moveAction.setAngle(Math.random() + moveAction.getAngle());
        moveAction.setVelocity(moveAction.getVelocity() * 0.99);
        double random = Math.random();
        if (random > 0.5)
        {
            for (int x = 1; x < 10; x++)
            {
                setLocation(moveAction.goRight(getLocation()));
            }

        }
        else
        {
            for (int x = 1; x < 10; x++)
            {
                setLocation(moveAction.goLeft(getLocation()));
            }

        }
        for (int x = 1; x < 3; x++)
        {
            setLocation(moveAction.goUp(getLocation()));
        }
    }
}

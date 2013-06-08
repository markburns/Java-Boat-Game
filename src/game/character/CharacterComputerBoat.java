/*
 * CharacterComputerBoat.java
 *
 * Created on 20 November 2007, 07:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game.character;

import game.movement.Location;
import game.movement.Movement;
import java.awt.geom.*;

/**
 *
 * @author Mark
 */
public class CharacterComputerBoat extends CharacterMoveable
{

    /** Creates a new instance of CharacterComputerBoat */
    public CharacterComputerBoat()
    {

    }
    int randomLength = 0;
    int turnDuration = 15;
    boolean changeDirection = false;
    int i;

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


        Rectangle2D b = this.getSprite().getUntransformedArea().getBounds2D();
        setTransform(new Location(b.getCenterX(), b.getCenterY()));
        if (checkScreenEdge())
        {
            getMoveBehaviour().setAngle(Math.PI + this.getMoveBehaviour().getAngle());
        }
    }

    


    @Override
    public void collision()
    {
        Movement m = getMoveBehaviour();
//		m.setAngle(Math.random() + m.getAngle());
        m.setVelocity(m.getVelocity() * 0.99);
        double random = Math.random();
        if (random > 0.5)
        {
            for (int x = 1; x < 10; x++)
            {
                setLocation(m.goRight(getLocation()));
            }

        }
        else
        {
            for (int x = 1; x < 10; x++)
            {
                setLocation(m.goLeft(getLocation()));
            }

        }
        for (int x = 1; x < 3; x++)
        {
            setLocation(m.goUp(getLocation()));
        }
    //	getMoveBehaviour().setVelocity(Math.random()/2);
    }
}

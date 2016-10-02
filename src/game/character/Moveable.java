package game.character;

import game.movement.Movement;
import game.Renderer;
import game.sprite.SpriteImage;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Moveable extends Character
{

    protected boolean immune = false; //boolean that sets player to receive damage
    Renderer renderer = Renderer.getInstance();

    
    public Moveable()
    {
    }

    @Override
    public abstract void update();

    protected boolean checkScreenEdge()
    {
        boolean hitEdge = false;

        double h = (double) renderer.getHeight();
        double w = (double) renderer.getWidth();

        double centreX = getSprite().getTransformedArea().getBounds().getCenterX();
        double centreY = getSprite().getTransformedArea().getBounds().getCenterY();

        double x = getLocation().getX();
        double y = getLocation().getY();

        //if over right side
        if (x > w)
        {
            getLocation().setX(w - this.getUntransformedArea().getBounds2D().getWidth());
            hitEdge = true;
        }
//if gone over bottom
        if (centreY > h)
        {
            getLocation().setY(h - this.getUntransformedArea().getBounds2D().getHeight());
            hitEdge = true;
        }
//if gone over left
        if (x < 0.0)
        {
            getLocation().setX(0);
            hitEdge = true;
        }
//if gone over top
        if (y < 0.0)
        {

            getLocation().setY(0);
            hitEdge = true;
        }
        return hitEdge;
    }

    @Override
    public void collide()
    {


        if (!immune)
        {
            SpriteImage boatImage = (SpriteImage) getSprite();
            boatImage.setFrame(1);
            immune = true;
            Movement moveAction = getMoveBehaviour();
            //getMoveBehaviour().angle+=(Math.random()-0.5);
            moveAction.setAngularVelocity(moveAction.getAngularVelocity() + (Math.random() - 0.5) * 0.4);
            moveAction.setAngle(moveAction.getAngle() + (Math.random() - 0.5) * 0.1);
            moveAction.setVelocity(-moveAction.getVelocity() * (3.8 * Math.random()));

            if (moveAction.getVelocity() > moveAction.getMaxVelocity())
            {
                moveAction.setVelocity(moveAction.getMaxVelocity());
            }

            if (moveAction.getVelocity() < -moveAction.getMaxVelocity())
            {
                moveAction.setVelocity(-moveAction.getMaxVelocity());
            }
            Timer timer = new Timer(); // setting immunity time
            timer.schedule
		  (new TimerTask()
                     {

                         @Override
                         public void run()
                         {
                             SpriteImage boatImage = (SpriteImage) getSprite();
                             boatImage.setFrame(0);
                             immune = false;
                         }
                     }
		, 2000);
        }
    }

}

package game.character;

import game.movement.Movement;
import game.Renderer;
import game.sprite.SpriteImage;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Moveable extends Character
{

    protected boolean immune = false;
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
            SpriteImage s = (SpriteImage) getSprite();
            s.setFrame(1);
            immune = true;
            Movement mb = getMoveBehaviour();
            //getMoveBehaviour().angle+=(Math.random()-0.5);
            mb.setAngularVelocity(mb.getAngularVelocity() + (Math.random() - 0.5) * 0.4);
            mb.setAngle(mb.getAngle() + (Math.random() - 0.5) * 0.1);
            mb.setVelocity(-mb.getVelocity() * (3.8 * Math.random()));

            if (mb.getVelocity() > mb.getMaxVelocity())
            {
                mb.setVelocity(mb.getMaxVelocity());
            }

            if (mb.getVelocity() < -mb.getMaxVelocity())
            {
                mb.setVelocity(-mb.getMaxVelocity());
            }
            Timer timer = new Timer();
            timer.schedule
		  (new TimerTask()
                     {

                         @Override
                         public void run()
                         {
                             SpriteImage s = (SpriteImage) getSprite();
                             s.setFrame(0);
                             immune = false;
                         }
                     }
		, 2000);
        }
    }

}

/*
 * GameEngine.java
 *
 * Created on 13 November 2007, 17:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package game;

import game.character.Cast;
import game.movement.Location;
import game.sprite.Sprite;
import game.character.Factory;
import game.character.Character;

import java.util.*;
import javax.swing.JOptionPane;

/**
 * S
 *
 * @author Mark
 */
public class GameEngine implements Runnable {

    private static Cast cast = game.character.Cast.getInstance();
    public static Renderer renderer;
    private static InputController controller = InputController.getInstance();
    private static int minObstacles;
    private static int maxObstacles;
    private Factory characterFactory;
    private static int obstacleSize;
    public static final int SLEEP_LENGTH = 16;//16 ms equates to ~60 frames per second
    private static GameEngine gameEngine;
    boolean alive = false;

    /**
     * Creates a new instance of GameEngine
     */
    private GameEngine() {
        cast = game.character.Cast.getInstance();
        renderer = Renderer.getInstance();
        controller = InputController.getInstance();
        minObstacles = 10;
        maxObstacles = minObstacles + 5;
        obstacleSize = 20;


    }

    public static GameEngine getInstance() {
        if (gameEngine != null) {
            return gameEngine;

        } else {
            synchronized (GameEngine.class) {
                if (gameEngine == null) {
                    gameEngine = new GameEngine();
                }
            }

        }

        return gameEngine;
    }

    private Factory factory() {
        if (this.characterFactory == null) {
            this.characterFactory = new Factory();
        }

        return this.characterFactory;

    }

    private Character addCharacter(String name, String type) {
        Character c = create(type);
        cast.put(name, c);
        return c;
    }

    private Character create(String type) {
        Character c = factory().createCharacter(type);
        return c;
    }

    private void setupObstacles() {
        Character obstacle;

        int min = Util.getMinimumNumberOfObstacles();
        int max = Util.getMaxiumNumberOfObstacles();
        int numberOfObstacles = (int) (Math.random() * (max - min));
        for (int x = 0; x
                < numberOfObstacles + 1; x++) {
            if (Math.random() > 0.5) {
                obstacle = create("BUOY");
            } else {
                obstacle = create("OCTOPUS");

            }

            Location l = new Location(
                    Math.random() * renderer.getWidth(),
                    Math.random() * renderer.getHeight());

            obstacle.setLocation(l);
            Sprite s = obstacle.getSprite();
            obstacle.getSprite().getTransform().setToTranslation(l.getX(), l.getY());

            s.setUntransformedArea(
                    s.getUntransformedArea().createTransformedArea(obstacle.getSprite().
                    getTransform()));


            cast.put("Obstacle" + String.valueOf(x), obstacle);
        }

    }

    public void initialize() {
        Util.loadImages();

        addCharacter("Harbour", "HARBOUR");
        addCharacter("Goal", "GOAL");
        addCharacter("Island", "ISLAND");

        Character boat = addCharacter("Boat", "BOAT");
        GameWindow.getInstance().initializeControlPanel(boat);

        for (int x = 0; x < 2; x++) {
            cast.put("ComputerBoat" + String.valueOf(x), create("COMPUTER_BOAT"));
        }

        setupObstacles();


        //draw the sea
        renderer.setBackgroundImage(Util.imageResources.get("SEA"));


    }
    boolean storm = false;

    public void storm() {
        ArrayList<Character> chars = this.cast.getObstacles();
        for (Iterator i = chars.iterator(); i.hasNext();) {
            Character c = (Character) i.next();
            c.getSprite().setShowSprite(!c.getSprite().isSpriteShown());
        }

        storm = !storm;
        if (storm) {
            renderer.setBackgroundImage(Util.imageResources.get("NIGHT"));

        } else {
            renderer.setBackgroundImage(Util.imageResources.get("SEA"));
        }

    }

    public void gameOver() {
        endGame("GAME OVER!");
    }

    public void win() {
        endGame("YOU WIN!");

    }

    private void endGame(String message) {
        if (cast.boatIsVulnerable()) {
            javax.swing.JOptionPane.showMessageDialog(null, message);
            cast.setBoatImmune();

        }

    }

    @Override
    public void run() {
        while (alive) {

            ArrayList<Character> moving = cast.getMovingCharacters();

            for (Iterator i = cast.getObstacles().iterator(); i.hasNext();) {
                Character c = (Character) i.next();
                c.update();
                c.detectCollision(moving);

            }

            for (Iterator i = moving.iterator(); i.hasNext();) {
                Character c = (Character) i.next();
                c.update();
                c.detectCollision(moving);

            }

            renderer.repaint();
            try {
                Thread.sleep(SLEEP_LENGTH);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    public HashMap<String, Character> getCharacters() {
        return cast;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

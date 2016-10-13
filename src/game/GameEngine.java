package game;

import game.character.Cast;
import game.movement.Location;
import game.sprite.Sprite;
import game.character.Factory;
import game.character.Character;

import java.util.*;

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

    
    public static Character getCharacter(String type){
        return GameEngine.getInstance().getCharacters().get(type);
    }
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
        Character character = create(type);
        cast.put(name, character);
        return character;
    }

    private Character create(String type) {
        Character character = factory().createCharacter(type);
        return character;
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
        ArrayList<Character> obstacles = GameEngine.cast.getObstacles();
        int x = obstacles.size();
        for (int i = 0; i < x; i++) {
            Character o = obstacles.get(i);
            o.getSprite().setShowSprite(!o.getSprite().isSpriteShown());
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
        if (cast.setBoatVulnerable()) {
            javax.swing.JOptionPane.showMessageDialog(null, message);
            cast.setBoatImmune();

        }

    }

    @Override
    public void run() {
        while (true) {

            ArrayList<Character> moving = cast.getMovingCharacters();
            ArrayList<Character> obstacles = cast.getObstacles();

            int x = obstacles.size();
            for (int i = 0; i < x; i++) {
                Character character = obstacles.get(i);

                character.update();
                character.detectCollision(moving);
            }
            
            x = moving.size();
            
            for (int i = 0; i < x; i++) {
                try{
            		Character character = moving.get(i);
            		character.update();
            		character.detectCollision(moving);
                }catch(NullPointerException|IndexOutOfBoundsException e){
                	assert(true);
                	e.printStackTrace();
                }
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
}

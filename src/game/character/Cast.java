package game.character;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Cast extends HashMap<String, Character> {
	
	static Logger logging = Logger.getLogger(Cast.class);
	private static final long serialVersionUID = 1L;
	private static Cast cast = null;

	/*
	 * function that starts a game instance
	 * @return cast  game environment
	 */
    public static Cast getInstance() {
        if (cast != null) {
        	logging.info("cast returned");
            return cast;

        } else {
            synchronized (Cast.class) {
                if (cast == null) {
                	logging.info("New cast created");
                    cast = new Cast();
                }else{
                	//do nothing
                }
            }

        }
        return cast;
    }

    /*
     * sets the boat to vulnerable mode
     * @return isVulnerable boolean that defines the vulnerability of the boat
     */
    public boolean setBoatVulnerable() {
    	boolean isVulnerable = false;
    	try{
    		isVulnerable = ((Boat) cast.get("Boat")).immune == false;
    					
    	}catch(Exception e){
    		logging.debug("Erro:" + e);
    		isVulnerable = false;
    	}
    	logging.debug("isVulnerable: " + isVulnerable);
        return  isVulnerable;
    }
    
    /*
     *sets the boat to immunity mode
     */
    public void setBoatImmune() {
        ((Boat) cast.get("Boat")).immune = true;
        logging.info("boat is immune");
    }

    /*
     * load the moving characters
     * @return charactersMoving  array of characters that were loaded
     */
    public ArrayList<Character> getMovingCharacters() {

        ArrayList<Character> charactersMoving = new ArrayList<>();
        
        try{
        	charactersMoving.add(cast.get("Boat"));
        }catch(IndexOutOfBoundsException e){
        	logging.debug("Erro:" + e);
        	charactersMoving = new ArrayList<>();
        }
        int x = 0;

        boolean finished = false;

        while (!finished) {

            if (cast.containsKey("ComputerBoat" + String.valueOf(x))) {
                charactersMoving.add(cast.get("ComputerBoat" + String.valueOf(x)));
                x++;
            } else {
                finished = true;
            }

        }

        return charactersMoving;
    }
    
    /*
     * resets the board
     */
    public static void reset() {
        cast = null;
        logging.info("Resetting");
    }

    /*
     * loads all the environment
     * @return all  arraylist that contains all environment of the board
     */
    public ArrayList<Character> getAllCharacters() {


        ArrayList<Character> all = new ArrayList<>();

        int x = 0;
        boolean finished = false;
        while (!finished) {

            if (cast.containsKey("Obstacle" + String.valueOf(x))) {
            	try{
            		all.add(cast.get("Obstacle" + String.valueOf(x)));
            		logging.info("Adding obstacles to all chars");
            	}catch(IndexOutOfBoundsException e){
                	logging.info("Failed to add:" + e);
                	all = new ArrayList<>();
                }
            	x++;
            } else {
                finished = true;
            }
        }
        String[] objects = {"Harbour", "Island", "Boat"};
        for (int y = 0; y < 3; y++) {
            if (cast.containsKey(objects + String.valueOf(x))) {
                try{
                	all.add(cast.get(objects + String.valueOf(x)));
                	logging.info("Adding objects");
                }catch(IndexOutOfBoundsException e){
                	logging.debug("Failed to add:" + e);
                	all = new ArrayList<>();
                }
            }else{
            	//do nothing
            }
        }

        x = 0;
        finished = false;

        while (!finished) {

            if (cast.containsKey("ComputerBoat" + String.valueOf(x))) {
                try{
                	all.add(cast.get("ComputerBoat" + String.valueOf(x)));
                	logging.info("Adding computer boats");
                }catch(IndexOutOfBoundsException e){
                	logging.debug("Failed to add:" + e);
                	all = new ArrayList<>();
                }
                x++;
            } else {
                finished = true;
            }

        }
        if (cast.containsKey("Goal")) {
            try{
            	all.add(cast.get("Goal"));
            	logging.info("adding goal");
            }catch(IndexOutOfBoundsException e){
            	logging.debug("Failed to add:" + e);
            	all = new ArrayList<>();
            }
        }else{
        	//do nothing
        }
        return all;
    }

    /*
     * loads the obstacles
     * @return obstacles   obstacles that were loaded
     */
    public ArrayList<Character> getObstacles() {
    	/* logging set to info to avoid loop of creation
    		(objects are re-created every second)
    	 */
    	logging.setLevel(Level.INFO);
        ArrayList<Character> obstacles = new ArrayList<>();
        int x = 0;
        while (cast.containsKey("Obstacle" + String.valueOf(x))) {
            obstacles.add(cast.get("Obstacle" + String.valueOf(x)));
            logging.debug("adding obstacles");
            x++;

        }
        try{
        	obstacles.add(cast.get("Harbour"));
        	logging.debug("adding harbour");
        	
        	obstacles.add(cast.get("Island"));
        	logging.debug("adding island");
        }catch(IndexOutOfBoundsException e){
        	logging.debug("Erro:" + e);
        	obstacles = new ArrayList<>();
        }
        if (cast.containsKey("Goal")) {
        	try{
        		obstacles.add(cast.get("Goal"));
        		logging.debug("adding goal (obstacle)");
        	}catch(IndexOutOfBoundsException e){
            	logging.debug("Erro:" + e);
            	obstacles = new ArrayList<>();
            }
        }else{
        	//do nothing
        }
        assert(obstacles != null) : "obstacles are null";
        return obstacles;
    }
}

package game.character;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Cast extends HashMap<String, Character> {
	
    /**
	 * 
	 */
	static Logger logging = Logger.getLogger(Cast.class);
	private static final long serialVersionUID = 1L;
	private static Cast cast;

    public static Cast getInstance() {
        if (cast != null) {
        	logging.info("cast returned");
            return cast;

        } else {
            synchronized (Cast.class) {
                if (cast == null) {
                	logging.info("New cast created");
                    cast = new Cast();
                }
            }

        }
        return cast;
    }

    public boolean boatIsVulnerable() {
    	boolean isVulnerable;
    	try{
    		isVulnerable = ((Boat) cast.get("Boat")).immune == false;
    					
    	}catch(Exception e){
    		logging.debug("Erro:" + e);
    		isVulnerable = false;
    	}
    	logging.debug("isVulnerable: " + isVulnerable);
        return  isVulnerable;
    }

    public void setBoatImmune() {
        ((Boat) cast.get("Boat")).immune = true;
        logging.info("boat is immune");
    }

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

    public static void reset() {
        cast = null;
        logging.info("Resetting");
    }

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
        }
        return all;
    }

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
        }

        return obstacles;
    }
}

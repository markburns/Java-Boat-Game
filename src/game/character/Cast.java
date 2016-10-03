package game.character;

import java.util.*;

public class Cast extends HashMap<String, Character> {

    private static Cast cast;

    public static Cast getInstance() {
        if (cast != null) {
            return cast;

        } else {
            synchronized (Cast.class) {
                if (cast == null) {
                    cast = new Cast();
                }
            }

        }
        return cast;
    }

    public boolean boatIsVulnerable() {
        return ((Boat) cast.get("Boat")).immune == false;
    }

    public void setBoatImmune() {
        ((Boat) cast.get("Boat")).immune = true;
    }

    public ArrayList<Character> getMovingCharacters() {

        ArrayList<Character> charactersMoving = new ArrayList<>();

        charactersMoving.add(cast.get("Boat"));
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
    }

    public ArrayList<Character> getAllCharacters() {


        ArrayList<Character> all = new ArrayList<>();

        int x = 0;
        boolean finished = false;
        while (!finished) {

            if (cast.containsKey("Obstacle" + String.valueOf(x))) {
                all.add(cast.get("Obstacle" + String.valueOf(x)));
                x++;
            } else {
                finished = true;
            }
        }
        String[] objects = {"Harbour", "Island", "Boat"};
        for (int y = 0; y < 3; y++) {
            if (cast.containsKey(objects + String.valueOf(x))) {
                all.add(cast.get(objects + String.valueOf(x)));

            }
        }

        x = 0;
        finished = false;

        while (!finished) {

            if (cast.containsKey("ComputerBoat" + String.valueOf(x))) {
                all.add(cast.get("ComputerBoat" + String.valueOf(x)));
                x++;
            } else {
                finished = true;
            }

        }
        if (cast.containsKey("Goal")) {
            all.add(cast.get("Goal"));

        }
        return all;
    }

    public ArrayList<Character> getObstacles() {
        ArrayList<Character> obstacles = new ArrayList<>();
        int x = 0;

        while (cast.containsKey("Obstacle" + String.valueOf(x))) {
            obstacles.add(cast.get("Obstacle" + String.valueOf(x)));
            x++;

        }
        obstacles.add(cast.get("Harbour"));
        obstacles.add(cast.get("Island"));
        if (cast.containsKey("Goal")) {
            obstacles.add(cast.get("Goal"));

        }

        return obstacles;
    }
}

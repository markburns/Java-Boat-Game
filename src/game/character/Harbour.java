package game.character;

import java.util.*;

public class Harbour extends Stationary {

    public Harbour() {
    }

    @Override
    public boolean detectCollision(ArrayList<Character> moving) {
        return false;
    }
}

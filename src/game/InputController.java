package game;

import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.io.PrintStream;

public class InputController {

    static private InputController controller;
    private HashMap<String, Control> keyBoardMappings = new HashMap<>();
    private java.util.List<Control> pressedControls = new java.util.LinkedList<>();
    private java.util.List<Control> heldControls = new java.util.LinkedList<>();
    private Point2D mouseLocation = new Point2D.Double(0.0, 0.0);
    private boolean blnMouseHeld = false;
    private boolean blnHasMouseMoved = false;

    /** Creates a new instance of Controller */
    private InputController() {
        keyBoardMappings.put("w", Control.UP);
        keyBoardMappings.put("W", Control.UP);
        keyBoardMappings.put("s", Control.DOWN);
        keyBoardMappings.put("S", Control.DOWN);
        keyBoardMappings.put("a", Control.LEFT);
        keyBoardMappings.put("A", Control.LEFT);
        keyBoardMappings.put("d", Control.RIGHT);
        keyBoardMappings.put("D", Control.RIGHT);
        keyBoardMappings.put("b", Control.BRAKE);
        keyBoardMappings.put("B", Control.BRAKE);

        keyBoardMappings.put("Up", Control.UP);
        keyBoardMappings.put("Down", Control.DOWN);
        keyBoardMappings.put("Left", Control.LEFT);
        keyBoardMappings.put("Right", Control.RIGHT);

        keyBoardMappings.put("Shift", Control.BRAKE);
        keyBoardMappings.put("Enter", Control.STORM);

    }

    public static void reset() {
        controller = null;
    }

    public static InputController getInstance() {
        if (controller == null) {
            controller = new InputController();
        }
        return controller;
    }

    public enum Control {

        NULL_CONTROL, ANGLE, VELOCITY, UP, DOWN, LEFT, RIGHT, BRAKE, PAUSE, STORM;
    }

    public void handleMouseClick(MouseEvent evt) {
        this.mouseLocation.setLocation(evt.getX(), evt.getY());
        this.blnMouseHeld = true;
    }

    public void handleMouseReleased(MouseEvent evt) {
        this.blnMouseHeld = false;

    }

    /** Handles incoming user input
    @param evt - a KeyEvent describing which Key was pressed
     */
    public void handleMouseMove(MouseEvent evt) {
        this.mouseLocation.setLocation(evt.getX(), evt.getY());
        this.blnHasMouseMoved = true;
    }

    public Control getPressedControl() throws java.lang.NullPointerException {
        if (pressedControls.size() > 0) {
     		try{
     			return pressedControls.remove(0);
     		}catch(NullPointerException|IndexOutOfBoundsException e){
     			System.out.println("Erro: " + e);
     			pressedControls = new java.util.LinkedList<>();
     		}
     	} else {
         //   return Control.NULL_CONTROL;
        }
		return Control.NULL_CONTROL;

    }

    /** Handles incoming user key releases
    @param evt - a KeyEvent describing which Key was released
     */
    public void handleKeyRelease(KeyEvent evt) {
        if (heldControls.size() > 0) {
            Control c = keyBoardMappings.get(KeyEvent.getKeyText(evt.getKeyCode()));
            while (heldControls.contains(c)) {
                heldControls.remove(c);
            }
        }
    }

    public boolean keyPressEventsPending() {
        if (this.pressedControls.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean keyHeldEventsPending() {
        if (this.heldControls.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /** handles a user key presses - if one of our used controls then it is appended to the heldControls ArrayList
    and the pressedControlsArrayList
    @param evt - A KeyEvent representing which key was pressed
     */
    public void handleKeyPress(KeyEvent evt) {
        Control pressed;
        String s = KeyEvent.getKeyText(evt.getKeyCode());
        pressed = keyBoardMappings.get(s);
        if (pressed == Control.STORM) {
            GameEngine.getInstance().storm();
        } else if (pressed != null) {
            //just add to list to process in sequence
            this.pressedControls.add(pressed);
            if (!this.heldControls.contains(pressed)) {//don't add if already on list
                this.heldControls.add(pressed); // any key is 'held' until released
            }
        }
    }
    
    public void triggerStorm(){
        GameEngine.getInstance().storm();
    }

    /**Returns a value if mouse has moved since last time getMouseLocation
    was called
     */
    public boolean hasMouseMoved() {
        return blnHasMouseMoved;
    }

    public Point2D getMouseLocation() {
        return this.mouseLocation;
    }

    public int getNumberOfHeldControls() {
    	int heldControl;
    	try{
    	heldControl = this.heldControls.toArray().length;
    	}catch(IndexOutOfBoundsException e){
    		System.out.println("Erro :" + e);
    		heldControl = 0;
    	}
    	
		return heldControl;
    }

    public Control getHeldControl(int index) {
        if (heldControls.size() > index)//check we are not trying to get an invalid value
        {
            Control ret = heldControls.get(index);
            return ret;
        } else {
            return Control.NULL_CONTROL;
        }

    }

    public boolean isMouseHeld() {
        return blnMouseHeld;
    }
}

2D Boat Game
============

The brief was to make a 2D game in which you had to navigate past
moving obstacles with a boat until you got to a goal.
Also when you press the enter key visibility should be reduced making it harder to navigate.

Run
=======
ant
java -jar "dist/Game.jar"

Controls
========
Mouse  or Cursor keys

It uses friction, acceleration and angular momentum to make a reasonable model of movement. 
Also there is a sine wave super-imposed on the boats' movements to make them look more like they are
on the sea.

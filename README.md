# diepai
DiepAI is a program designed to play the online multiplayer game Diep.io.
This program is currently a work in progress. However, it can control movement and aiming by itself. 
DiepAI is divided into three main parts: the screen reader, the aim controller, and the movement controller.
	
The screen reader reads a constant stream of screenshots directly from the screen. 
For each screenshot, it searches for pixels with certain colors (relating to the colors in the game).
Whenever it finds these colors, it traces around the outside of the shape and stores it as a shape object.
	
The aim controller uses the shape data to decide where to move the mouse while playing the game.
The aim controller is activated by pressing "[" and deactivated by pressing "]". 
	
The movement controller uses the shape data to decide which direction to move. All movement in 
the game is controlled by pressing the keys a, w, s, and d. 
The movement controller is activated by pressing "," and deactivated by pressing ".".
	
When bDisplayShapes is set to true, a JFrame will appear at the start of the program.
The runnable_jar file has bDisplayShapes set to true for testing purposes. 
The JFrame displays the current shape data the program has and updates it's display with each screenshot. 
To test it, put the JFrame in one corner of the screen, and a picture with colors that it is looking for
in another corner of the screen. The JFrame will display the shapes relative to it's size.
The picture Pentagon_Nest.png is provided for this purpose.
Occasionally, the program lags and the JFrame stops updating. If this happens, restart the program.

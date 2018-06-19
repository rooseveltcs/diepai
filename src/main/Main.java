package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import movement.GeneralMovement;
import screenIO.*;
import tankInfo.TankTest;

public class Main 
{	
	//This class is the starting point of the program.
	//It contains a main method and some temporary code.
	
	//DiepAI is a program designed to play the online multiplayer game Diep.io.
	//This program is currently a work in progress. However, it can control movement and aiming by itself. 
	//DiepAI is divided into three main parts: the screen reader, the aim controller, and the movement controller.
	
	//The screen reader reads a constant stream of screenshots directly from the screen. 
	//For each screenshot, it searches for pixels with certain colors (relating to the colors in the game).
	//Whenever it finds these colors, it traces around the outside of the shape and stores it as a shape object.
	
	//The aim controller uses the shape data to decide where to move the mouse while playing the game.
	//The aim controller is activated by pressing "[" and deactivated by pressing "]". 
	
	//The movement controller uses the shape data to decide which direction to move. All movement in 
	//the game is controlled by pressing the keys a, w, s, and d. 
	//The movement controller is activated by pressing "," and deactivated by pressing ".".
	
	//when bDisplayShapes is set to true, a JFrame will appear at the start of the program.
	//This JFrame displays the current shape data the program has and updates it's display with each screenshot. 
	//To test it, put the JFrame in one corner of the screen, and a picture with colors that it is looking for
	//in another corner of the screen. The JFrame will display the shapes relative to it's size.
	//The picture Pentagon_Nest.png is provided for this purpose.
	//Occasionally, the program lags and the JFrame stops updating. If this happens, restart the program.
	
	public static boolean bDisplayShapes = true;
	
	public static void main(String[] args)
	{
		//Start JNativehook to listen for key and mouse events
		methods.ExternalLibraries.registerNativeHook();
		KeyListener.start();
		MouseListener.start();
		
		//Start the screen reader
		ScreenReader.start();
	}
	
	//This temporary static block sets the color of the players team 
	//and uses this color to determine the order of the four movement keys. 
	static
	{
		Color teamColor = ShapeData.PURPLE_COLOR;
		TankTest.teamColor = teamColor;
		
		if (teamColor.equals(ShapeData.GREEN_COLOR))
		{
			GeneralMovement.key1 = KeyEvent.VK_W;
			GeneralMovement.key2 = KeyEvent.VK_D;
			GeneralMovement.key3 = KeyEvent.VK_A;
			GeneralMovement.key4 = KeyEvent.VK_S;
		}
		else if (teamColor.equals(ShapeData.BLUE_COLOR))
		{
			GeneralMovement.key1 = KeyEvent.VK_S;
			GeneralMovement.key2 = KeyEvent.VK_D;
			GeneralMovement.key3 = KeyEvent.VK_A;
			GeneralMovement.key4 = KeyEvent.VK_W;
		}
		else if (teamColor.equals(ShapeData.RED_COLOR))
		{
			GeneralMovement.key1 = KeyEvent.VK_A;
			GeneralMovement.key2 = KeyEvent.VK_W;
			GeneralMovement.key3 = KeyEvent.VK_S;
			GeneralMovement.key4 = KeyEvent.VK_D;
		}
		else if (teamColor.equals(ShapeData.PURPLE_COLOR))
		{
			GeneralMovement.key1 = KeyEvent.VK_S;
			GeneralMovement.key2 = KeyEvent.VK_A;
			GeneralMovement.key3 = KeyEvent.VK_W;
			GeneralMovement.key4 = KeyEvent.VK_D;
		}
	}
}

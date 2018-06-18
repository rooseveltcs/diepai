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
	
	public static void main(String[] args)
	{
		methods.ExternalLibraries.registerNativeHook();
		KeyListener.start();
		MouseListener.start();
		ScreenReader.start();
		//ScreenDataTest.startWithThread();
		//ScreenTimer.start();
		//MoveMouseTest.start();
	}
	
	public static void sleep(int ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

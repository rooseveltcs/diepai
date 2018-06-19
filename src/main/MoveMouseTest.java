package main;

import java.awt.MouseInfo;

import screenIO.RobotManager;
import screenIO.ScreenData;

public class MoveMouseTest 
{
	//THIS CLASS IS FOR DEBUGGING
	//This class is used to test the moveMouse method in the java class Robot.
	//This method does not work properly at higher resolutions on my home computer.
	private static RobotManager robotManager = new RobotManager();
	
	//Start the test
	public static void start()
	{
		int xSize = ScreenData.SCREEN_WIDTH;
		int ySize = ScreenData.SCREEN_HEIGHT;
		System.out.println(xSize + " x " + ySize);
		test(0, 0);
		test(xSize, 0);
		test(0, ySize);
		test(xSize, ySize);
		int iIncrement = 100;
		for (int x = 0; x <= xSize; x += iIncrement)
		{
			for (int y = 0; y <= ySize; y += iIncrement)
			{
				test(x, y);
			}
		}
	}
	
	//get the x position of the mouse
	private static int getMouseX()
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getX();
	}
	
	//get the y position of the mouse
	private static int getMouseY()
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getY();
	}
	
	//Move the mouse to various points on the screen at intervals of 100 milliseconds. 
	//Test to make sure the mouse is actually at those points.
	private static void test(int x, int y)
	{
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robotManager.moveMouseTo(x, y);
		int xResult = getMouseX();
		int yResult = getMouseY();
		
		if (!isCloseTo(x, xResult, 3) || !isCloseTo(y, yResult, 3))
		{
			System.out.println("Target: " + x + ", " + y);
			System.out.println("Result: " + xResult + ", " + yResult);
			System.out.println();
		}
	}
	
	//Test if one number is within a certain margin of another number.
	private static boolean isCloseTo(int iNum, int iNumToTest, int iMargin)
	{
		if (iNumToTest >= iNum - iMargin && iNumToTest <= iNum + iMargin)
		{
			return true;
		}
		return false;
	}
	
}

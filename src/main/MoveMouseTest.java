package main;

import java.awt.MouseInfo;

import screenIO.RobotManager;
import screenIO.ScreenData;

public class MoveMouseTest 
{
	private static RobotManager robotManager = new RobotManager();
	
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
	
	private static int getMouseX()
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getX();
	}
	
	private static int getMouseY()
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getY();
	}
	
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
	private static boolean isCloseTo(int iNum, int iNumToTest, int iMargin)
	{
		if (iNumToTest >= iNum - iMargin && iNumToTest <= iNum + iMargin)
		{
			return true;
		}
		return false;
	}
	
}

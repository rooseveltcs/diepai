package movement;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;

import screenIO.RobotManager;
import screenIO.ScreenData;
import screenIO.Shape;
import screenIO.ShapeData;
import screenIO.ShapeList;
import tankInfo.TankTest;

public class GeneralMovement 
{
	//This class is used to control movement while playing the game
	//by pressing combinations of the keys a, w, s, and d.
	private static RobotManager robotManager = new RobotManager();
	private static String startKey = "comma";
	private static String stopKey = "period";
	private static boolean bIsRunning = false;
	private static Thread runningThread;
	
	public static int key1;
	public static int key2;
	public static int key3;
	public static int key4;
	
	//Start with thread.
	private static void startWithThread()
	{
		runningThread = new Thread(new Runnable()
		{
			public void run()
			{
				start();
			}
		});
		runningThread.start();
	}
	
	//A very simple movement algorithm that is executed continuously.
	//If there are enemy players or bullets on the screen, retreat.
	//Else if there are pentagons on the screen, move toward the closest one
	//if you are more than 200 pixels away from it. 
	//Else move toward the center of the map (opposite direction from retreat).
	private static void start()
	{
		TankTest.setEnemyColors();
		while (true)
		{
			ShapeList shapes = ScreenData.shapes;
			ShapeList enemyColors = shapes.getShapesWithColors(TankTest.enemyColors);
			ShapeList pentagons = shapes.getShapesWithColor(ShapeData.PENTAGON_COLOR);
			
			if (enemyColors.size() > 0)
			{
				robotManager.pressKey(key3);
				robotManager.pressKey(key4);
				robotManager.delay(100);
				robotManager.releaseKey(key3);
				robotManager.releaseKey(key4);
			}
			else if (pentagons.size() > 0)
			{
				Point center = ScreenData.CENTER;
				Shape target = pentagons.getClosestShapeTo(center);
				int iDistance = target.getDistanceFrom(ScreenData.CENTER);
				if (iDistance > 200) 
				{
					moveTowardPoint(target.centerpoint, 100);
				}
			}
			else
			{
				robotManager.pressKey(key1);
				robotManager.pressKey(key2);
				robotManager.delay(100);
				robotManager.releaseKey(key1);
				robotManager.releaseKey(key2);
			}
		}
	}
	
	//Move toward a point in the game.
	public static void moveTowardPoint(Point target, int ms)
	{
		Point center = ScreenData.CENTER;
		if (target.x > center.x) robotManager.pressKey(KeyEvent.VK_D);
		else robotManager.pressKey(KeyEvent.VK_A);
		if (target.y > center.y) robotManager.pressKey(KeyEvent.VK_S);
		else robotManager.pressKey(KeyEvent.VK_W);
		
		robotManager.delay(ms);
		
		if (target.x > center.x) robotManager.releaseKey(KeyEvent.VK_D);
		else robotManager.releaseKey(KeyEvent.VK_A);
		if (target.y > center.y) robotManager.releaseKey(KeyEvent.VK_S);
		else robotManager.releaseKey(KeyEvent.VK_W);
	}
	
	//Move away from a point in the game.
	public static void moveAwayFromPoint(Point target, int ms)
	{
		Point center = ScreenData.CENTER;
		if (target.x > center.x) robotManager.pressKey(KeyEvent.VK_A);
		else robotManager.pressKey(KeyEvent.VK_D);
		if (target.y > center.y) robotManager.pressKey(KeyEvent.VK_W);
		else robotManager.pressKey(KeyEvent.VK_S);
		
		robotManager.delay(ms);
		
		if (target.x > center.x) robotManager.releaseKey(KeyEvent.VK_A);
		else robotManager.releaseKey(KeyEvent.VK_D);
		if (target.y > center.y) robotManager.releaseKey(KeyEvent.VK_W);
		else robotManager.releaseKey(KeyEvent.VK_S);
	}
	
	//When the startKey is pressed, set bIsRunning to true and start with thread.
	//When the stopKey is pressed, set bIsRunning to false and stop the running thread.
	public static void keyPressed(String sKeycode)
	{
		if (sKeycode.equals(startKey) && !bIsRunning)
		{
			startWithThread();
			bIsRunning = true;
		}
		else if (sKeycode.equals(stopKey) && bIsRunning)
		{
			runningThread.stop();
			bIsRunning = false;
		}
	}
	
	//Pause the current thread.
	public static void sleep(int ms)
	{
		try 
		{
			Thread.sleep(ms);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
}

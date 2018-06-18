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
	private static RobotManager robotManager = new RobotManager();
	private static String startKey = "comma";
	private static String stopKey = "period";
	private static boolean bIsRunning = false;
	private static Thread runningThread;
	
	public static int key1;
	public static int key2;
	public static int key3;
	public static int key4;
	
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

	/*private static void start()
	{
		TankTest.setEnemyColors();
		while (true)
		{
			ShapeList shapes = ScreenData.shapes;
			ShapeList enemyColors = shapes.getShapesWithColors(TankTest.enemyColors);
			
			ShapeList enemyBullets = enemyColors.getShapesInsideRadius(ShapeData.MIN_TANK_RADIUS);
			ShapeList enemyTanks = enemyColors.getShapesOutsideRadius(ShapeData.MIN_TANK_RADIUS);
			
			ShapeList pentagons = shapes.getShapesWithColor(ShapeData.PENTAGON_COLOR);
			
			if (enemyTanks.size() > 0)
			{
				Shape tank = enemyTanks.getClosestShapeTo(ScreenData.CENTER);
				moveAwayFromPoint(tank.centerpoint, 100);
			}
			else if (enemyBullets.size() > 0)
			{
				Shape bullet = enemyBullets.getClosestShapeTo(ScreenData.CENTER);
				moveAwayFromPoint(bullet.centerpoint, 100);
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
	}*/
	
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

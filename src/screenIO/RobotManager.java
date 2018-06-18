package screenIO;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class RobotManager 
{
	private Robot robot;
    private Rectangle screenSize = new Rectangle(ScreenData.SCREEN_WIDTH, ScreenData.SCREEN_HEIGHT);
    
	public RobotManager()
	{
		try 
		{
			robot = new Robot();
		} 
		catch (AWTException e) 
		{
			System.out.println("Could not create an instance of Robot.");
			System.exit(0);
		}
	}
	
	public Screenshot takeScreenshot()
	{
		long lScreenshotTime = System.currentTimeMillis();
		BufferedImage image = robot.createScreenCapture(screenSize);
		return new Screenshot(image, lScreenshotTime);
	}
	
	public void moveMouseTo(int x, int y)
	{
		robot.mouseMove(x, y);
	}
	
	public void rightClick()
	{
		robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
	}
	
	public void leftClick()
	{
		robot.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);
	}
	
	public void clickKey(int keyEventCode)
	{
		robot.keyPress(keyEventCode);
		robot.keyRelease(keyEventCode);
	}
	
	public void pressKey(int keyEventCode)
	{
		robot.keyPress(keyEventCode);
	}
	
	public void releaseKey(int keyEventCode)
	{
		robot.keyRelease(keyEventCode);
	}
	
	public void delay(int ms)
	{
		robot.delay(ms);
	}

}

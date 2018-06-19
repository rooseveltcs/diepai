package screenIO;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class RobotManager 
{
	//This class is used to easily create and use instances of robot.
	private Robot robot;
    private Rectangle screenSize = new Rectangle(ScreenData.SCREEN_WIDTH, ScreenData.SCREEN_HEIGHT);
    
    //Construct a new robot manager object. This automatically creates a new instance of robot.
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
	
	//Take a screenshot.
	public Screenshot takeScreenshot()
	{
		long lScreenshotTime = System.currentTimeMillis();
		BufferedImage image = robot.createScreenCapture(screenSize);
		return new Screenshot(image, lScreenshotTime);
	}
	
	//Move the mouse to x, y.
	public void moveMouseTo(int x, int y)
	{
		robot.mouseMove(x, y);
	}
	
	//Right click.
	public void rightClick()
	{
		robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
	}
	
	//Left click.
	public void leftClick()
	{
		robot.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);
	}
	
	//Click the key represented by keyEventCode.
	public void clickKey(int keyEventCode)
	{
		robot.keyPress(keyEventCode);
		robot.keyRelease(keyEventCode);
	}
	
	//Press the key represented by keyEventCode.
	public void pressKey(int keyEventCode)
	{
		robot.keyPress(keyEventCode);
	}
	
	//Release the key represented by keyEventCode.
	public void releaseKey(int keyEventCode)
	{
		robot.keyRelease(keyEventCode);
	}
	
	//Pause the current thread.
	public void delay(int ms)
	{
		robot.delay(ms);
	}

}

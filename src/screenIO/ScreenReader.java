package screenIO;

public class ScreenReader 
{
	//This class reads a constant stream of screenshots from the screen.
	private static RobotManager robotManager = new RobotManager();
	public static Timer timer = new Timer();
	//Start reading screenshots continuously in a new thread.
	//For each screenshot, executes ScreenParser.parseScreenshot(screenshot).
	public static void start()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				while (true) 
				{
					long lStartTime = System.currentTimeMillis();
					Screenshot screenshot = robotManager.takeScreenshot();
					int iRunTime = (int) (System.currentTimeMillis() - lStartTime);
					timer.addTime(iRunTime);
					ScreenParser.parseScreenshot(screenshot);
				}
			}
		}).start();
	}
	
	//Reset the timer data. This data shows how long it is taking to create screenshots.
	public static void resetData()
	{
		timer = new Timer();
	}
}

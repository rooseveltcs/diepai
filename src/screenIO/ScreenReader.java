package screenIO;

public class ScreenReader 
{
	private static RobotManager robotManager = new RobotManager();
	public static Timer timer = new Timer();
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
	
	public static void resetData()
	{
		timer = new Timer();
	}
}

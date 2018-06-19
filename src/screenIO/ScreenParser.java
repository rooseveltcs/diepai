package screenIO;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class ScreenParser 
{
	//This class is used to parse screenshots into ShapeList objects. 
	public static Timer timer = new Timer();
	private static boolean bIsRunning = false;
	public static int iNumDelays = 0;
	
	private static final int SEARCH_MARGIN = 5;
	
	//Parse a screenshot in a new thread.
	public static void parseScreenshot(Screenshot screenshot)
	{
		if (bIsRunning)
		{
			iNumDelays++;
		}
		else
		{
			new Thread(new Runnable()
			{
				public void run()
				{
					bIsRunning = true;
					Long lStartTime = System.currentTimeMillis();
					parseScreenshotWithThread(screenshot);
					int iRunTime = (int) (System.currentTimeMillis() - lStartTime);
					timer.addTime(iRunTime); 
					bIsRunning = false;
				}
			}).start();
		}
	}
	
	//Parse a screenshot. 
	//Search the screenshot for pixels with a color in ShapeData.colors.
	//Whenever these colors are found, outline the shape around them and store it as a shape object.
	//Add all the shapes to a shape list. 
	private static void parseScreenshotWithThread(Screenshot screenshot)
	{
		ShapeList shapes = new ShapeList(screenshot);
 		for (int x = 0; x < ScreenData.SCREEN_WIDTH; x += SEARCH_MARGIN)
		{
			for (int y = 0; y < ScreenData.SCREEN_HEIGHT; y += SEARCH_MARGIN)
			{
				if (shapes.containsPoint(new Point(x, y))) 
				{
					continue;
				}
				Color currentPixel = screenshot.getPixel(x, y);
				COLOR_LOOP:
				for (Color color : ShapeData.colors)
				{
					if (isColor(currentPixel, color))
					{
						ArrayList<Point> sidePoints = getSidePoints(new Point(x, y), screenshot, color);
						Shape shape = getShape(sidePoints, color);
						if (!shapes.containsPoint(shape.centerpoint))
						{
							shapes.add(shape);
						}
						break COLOR_LOOP;
					}
				}
			}
		}
 		ScreenData.updateShapes(shapes);
	}
	
	//Convert an ArrayList of side poitns into a shape object.
	private static Shape getShape(ArrayList<Point> sidePoints, Color color)
	{
		int[] xPoints = new int[sidePoints.size()];
		int[] yPoints = new int[sidePoints.size()];
		for (int i = 0; i < sidePoints.size(); i++)
		{
			Point sidePoint = sidePoints.get(i);
			xPoints[i] = sidePoint.x;
			yPoints[i] = sidePoint.y;
		}
		
		Polygon polygon = new Polygon(xPoints, yPoints, sidePoints.size());
		return new SimplePolygon(polygon, color);
	}
	
	//Get the side points by tracing around the outside of the shape.
	private static ArrayList<Point> getSidePoints(Point point, Screenshot screenshot, Color color)
	{
		Point startingPoint = null;
		for (int x = point.x; x >= 0; x--)
		{
			if (!isColor(color, screenshot.getPixel(x - 1, point.y)))
			{
				startingPoint = new Point(x, point.y);
				break;
			}
		}
		
		ArrayList<Point> sidePoints = new ArrayList<>();
		Point currentPoint = startingPoint;
		
		while (true)
		{	
			sidePoints.add(currentPoint);
			ArrayList<Point> newPoints = new ArrayList<>();
			
			//TOP
			if (isColor(color, screenshot.getPixel(currentPoint.x, currentPoint.y - 1)))
			{
				if (!isColor(color, screenshot.getPixel(currentPoint.x - 1, currentPoint.y - 1)) ||
					!isColor(color, screenshot.getPixel(currentPoint.x + 1, currentPoint.y - 1)))
				{
					newPoints.add(new Point(currentPoint.x, currentPoint.y - 1));
				}
			}
			
			//TOP RIGHT
			if (isColor(color, screenshot.getPixel(currentPoint.x + 1, currentPoint.y - 1)))
			{
				if (!isColor(color, screenshot.getPixel(currentPoint.x, currentPoint.y - 1)) ||
					!isColor(color, screenshot.getPixel(currentPoint.x + 1, currentPoint.y)))
				{
					newPoints.add(new Point(currentPoint.x + 1, currentPoint.y - 1));
				}
			}
			
			//RIGHT
			if (isColor(color, screenshot.getPixel(currentPoint.x + 1, currentPoint.y)))
			{
				if (!isColor(color, screenshot.getPixel(currentPoint.x + 1, currentPoint.y - 1)) ||
					!isColor(color, screenshot.getPixel(currentPoint.x + 1, currentPoint.y + 1)))
				{
					newPoints.add(new Point(currentPoint.x + 1, currentPoint.y));
				}
			}
			
			//BOTTOM RIGHT
			if (isColor(color, screenshot.getPixel(currentPoint.x + 1, currentPoint.y + 1)))
			{
				if (!isColor(color, screenshot.getPixel(currentPoint.x + 1, currentPoint.y)) ||
					!isColor(color, screenshot.getPixel(currentPoint.x, currentPoint.y + 1)))
				{
					newPoints.add(new Point(currentPoint.x + 1, currentPoint.y + 1));
				}
			}
			
			//BOTTOM
			if (isColor(color, screenshot.getPixel(currentPoint.x, currentPoint.y + 1)))
			{
				if (!isColor(color, screenshot.getPixel(currentPoint.x + 1, currentPoint.y + 1)) ||
					!isColor(color, screenshot.getPixel(currentPoint.x - 1, currentPoint.y + 1)))
				{
					newPoints.add(new Point(currentPoint.x, currentPoint.y + 1));
				}
			}
			
			//BOTTOM LEFT
			if (isColor(color, screenshot.getPixel(currentPoint.x - 1, currentPoint.y + 1)))
			{
				if (!isColor(color, screenshot.getPixel(currentPoint.x - 1, currentPoint.y)) ||
					!isColor(color, screenshot.getPixel(currentPoint.x, currentPoint.y + 1)))
				{
					newPoints.add(new Point(currentPoint.x - 1, currentPoint.y + 1));
				}
			}
			
			//LEFT
			if (isColor(color, screenshot.getPixel(currentPoint.x - 1, currentPoint.y)))
			{
				if (!isColor(color, screenshot.getPixel(currentPoint.x - 1, currentPoint.y - 1)) ||
					!isColor(color, screenshot.getPixel(currentPoint.x - 1, currentPoint.y + 1)))
				{
					newPoints.add(new Point(currentPoint.x - 1, currentPoint.y));
				}
			}
			
			//TOP LEFT
			if (isColor(color, screenshot.getPixel(currentPoint.x - 1, currentPoint.y - 1)))
			{
				if (!isColor(color, screenshot.getPixel(currentPoint.x, currentPoint.y - 1)) ||
					!isColor(color, screenshot.getPixel(currentPoint.x - 1, currentPoint.y)))
				{
					newPoints.add(new Point(currentPoint.x - 1, currentPoint.y - 1));
				}
			}
			
			if (newPoints.size() == 0) break;
			if (newPoints.size() == 1) 
			{
				currentPoint = newPoints.get(0);
				if (currentPoint.equals(startingPoint)) break;
				continue;
			}
			
			int[] pointRanks = new int[newPoints.size()];
			FOR_LOOP:
			for (int i = 0; i < newPoints.size(); i++)
			{
				Point newPoint = newPoints.get(i);
				for (int index = 0; index < sidePoints.size(); index++)
				{
					if (sidePoints.get(index).equals(newPoint))
					{
						pointRanks[i] = index + 1;
						continue FOR_LOOP;
					}
				}
				pointRanks[i] = 0;
			}
			int iMinIndex = -1;
			for (int i = 0; i < pointRanks.length; i++)
			{
				if (iMinIndex == -1) iMinIndex = i;
				else if (pointRanks[i] < pointRanks[iMinIndex])
				{
					iMinIndex = i;
				}
			}
			currentPoint = newPoints.get(iMinIndex);
			
			if (currentPoint.equals(startingPoint)) break;
		}
		
		return sidePoints;
	}
	
	//Test if one color is within ShapeData.iColorMargin of another color.
	private static boolean isColor(Color color, Color targetColor)
	{
		if (color == null ^ targetColor == null) return false;
		int iMargin = ShapeData.iColorMargin;
		if (!isCloseTo(color.getRed(), targetColor.getRed(), iMargin)) return false;
		if (!isCloseTo(color.getGreen(), targetColor.getGreen(), iMargin)) return false;
		if (!isCloseTo(color.getBlue(), targetColor.getBlue(), iMargin)) return false;
		return true;
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
	
	//Reset the timer data. This data shows how long it is taking to parse screenshots.
	public static void resetData()
	{
		timer = new Timer();
		iNumDelays = 0;
	}
}

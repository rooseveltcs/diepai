package screenIO;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import main.Main;

import static methods.PrimativeMethods.*;
import static methods.Console.*;

public class ScreenParser 
{
	public static Timer timer = new Timer();
	private static boolean bIsRunning = false;
	public static int iNumDelays = 0;
	
	private static final int SEARCH_MARGIN = 5;
	
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
	
		/*System.out.println("Centerpoint: " + centerPoint);
		System.out.println("Max Radius: " + dMaxRadius);
		System.out.println("Min Radius: " + dMinRadius);
		System.out.println("Difference: " + dRadiusDifference);
		System.out.println("Average Radius: " + dAverageRadius);
		System.out.println();*/
	}
	
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
	
	/*private static Polygon getBasicCorners(Point center, Screenshot screenshot, Color color)
	{
		int[] xPoints = new int[4];
		xPoints[0] = center.x - 20;
		xPoints[1] = center.x + 20;
		xPoints[2] = center.x + 20;
		xPoints[3] = center.x - 20;
		
		int[] yPoints = new int[4];
		yPoints[0] = center.y - 20;
		yPoints[1] = center.y - 20;
		yPoints[2] = center.y + 20;
		yPoints[3] = center.y + 20;
		
		Polygon corners = new Polygon(xPoints, yPoints, 4);
		return corners;
	}*/
	
	private static Point getCenterpoint(int x, int y, Screenshot screenshot, Color targetColor, int iNumCenters)
	{
		for (int i = 0; i < iNumCenters; i++)
		{
			if (i % 2 == 0)
			{
				x = getXCenter(x, y, screenshot, targetColor);
			}
			else
			{
				y = getYCenter(x, y, screenshot, targetColor);
			}
		}
		return new Point(x, y);
	}
	
	private static int getXCenter(int x, int y, Screenshot screenshot, Color targetColor)
	{
		//Find left
		int iLeft;
		for (iLeft = x; iLeft >= 0; iLeft--)
		{
			if (!isColor(screenshot.getPixel(iLeft, y), targetColor))
			{
				break;
			}
		}
		//Find right
		int iRight;
		for (iRight = x; iRight < ScreenData.SCREEN_WIDTH; iRight++)
		{
			if (!isColor(screenshot.getPixel(iRight, y), targetColor))
			{
				break;
			}
		}
		return (iLeft + iRight) / 2;
	}
	
	private static int getYCenter(int x, int y, Screenshot screenshot, Color targetColor)
	{
		//Find top
		int iTop;
		for (iTop = y; iTop >= 0; iTop--)
		{
			if (!isColor(screenshot.getPixel(x, iTop), targetColor))
			{
				break;
			}
		}
		//Find bottom
		int iBottom;
		for (iBottom = y; iBottom < ScreenData.SCREEN_HEIGHT; iBottom++)
		{
			if (!isColor(screenshot.getPixel(x, iBottom), targetColor))
			{
				break;
			}
		}
		return (iTop + iBottom) / 2;
	}
	
	private static boolean isColor(Color color, Color targetColor)
	{
		if (color == null ^ targetColor == null) return false;
		int iMargin = ShapeData.iColorMargin;
		if (!isCloseTo(color.getRed(), targetColor.getRed(), iMargin)) return false;
		if (!isCloseTo(color.getGreen(), targetColor.getGreen(), iMargin)) return false;
		if (!isCloseTo(color.getBlue(), targetColor.getBlue(), iMargin)) return false;
		return true;
	}
	
	public static void resetData()
	{
		timer = new Timer();
		iNumDelays = 0;
	}
}
